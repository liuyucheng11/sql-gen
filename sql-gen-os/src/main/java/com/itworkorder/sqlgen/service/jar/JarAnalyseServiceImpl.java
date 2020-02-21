package com.itworkorder.sqlgen.service.jar;

import com.itworkorder.sqlgen.service.JarAnalyseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.mapreduce.HashTable;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureRandom;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-20  15:38
 * @version 1.0
 */
@Service
@Slf4j
public class JarAnalyseServiceImpl implements JarAnalyseService {
    /**
     * 基础类不进行解析
     */
    private static final Set<Class<?>> unResolveClass = new HashSet<>();

    private static String[] title = {"对象名称", "字段名称", "字段类型", "描述", "备注"};

    private static final String SHEET_NAME = "接口实体表";

    private static final String TABLE_NAME = "生成表";

    private static final ArrayList<String> RESOLVE = new ArrayList<>();

    static {
        unResolveClass.add(int.class);
        unResolveClass.add(Integer.class);
        unResolveClass.add(byte.class);
        unResolveClass.add(Byte.class);
        unResolveClass.add(double.class);
        unResolveClass.add(Double.class);
        unResolveClass.add(Long.class);
        unResolveClass.add(long.class);
        unResolveClass.add(Date.class);
        unResolveClass.add(BigDecimal.class);
        unResolveClass.add(float.class);
        unResolveClass.add(Float.class);
        unResolveClass.add(short.class);
        unResolveClass.add(Short.class);
        unResolveClass.add(String.class);
        unResolveClass.add(Boolean.class);
        unResolveClass.add(boolean.class);
        unResolveClass.add(Object.class);
        unResolveClass.add(Map.class);
        unResolveClass.add(ArrayList.class);
        unResolveClass.add(HashMap.class);
        unResolveClass.add(HashTable.class);
        unResolveClass.add(SecureRandom.class);
    }

    static {
        RESOLVE.add("dto");
        RESOLVE.add("enum");
        RESOLVE.add("vo");
        RESOLVE.add("req");
        RESOLVE.add("query");
        RESOLVE.add("parameter");
        RESOLVE.add("param");
        RESOLVE.add("view");
        RESOLVE.add("basic");
        RESOLVE.add("model");
    }

    @Override
    public Class<?> loadJarClassFile(JarEntry entry, ClassLoader loader) {
        String name = entry.getName().replaceAll("\\.", "/").replaceAll("/", ".");
        String className = name.substring(0, name.length() - 6);
        Class<?> clazz = null;
        try {
            clazz = loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            log.error("加载类失败");
        }
        return clazz;
    }

    @Override
    public Set<Class<?>> getJarLoader(MultipartFile jarFile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String dir = request.getServletContext().getRealPath("/");
        Set<Class<?>> classCache = new HashSet<>();
        File temp = null;
        try {
            temp = new File(dir, UUID.randomUUID() + ".jar");
            jarFile.transferTo(temp);
            URL url = temp.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
            JarFile jar = new JarFile(temp);
            Enumeration<JarEntry> en = jar.entries();
            while (en.hasMoreElements()) {
                JarEntry jarEntry = en.nextElement();
                String jarFileName = jarEntry.getName();
                if (!jarEntry.isDirectory() && jarFileName.endsWith(".class")) {
                    Class<?> clazz = this.loadJarClassFile(jarEntry, classLoader);
                    if (clazz != null) {
                        classCache.add(clazz);
                    }
                }
            }
        } catch (IOException e) {
            log.error("文件流异常");
        } finally {
            if (temp != null) {
                boolean result = false;
                while (result) {
                    result = temp.delete();
                }
            }
        }
        return classCache;
    }

    @Override
    public void generateWorkBook(Set<Class<?>> classSet, XSSFSheet sheet) {
        if (!classSet.isEmpty()) {
            for (Class<?> clazz : classSet) {
                try {
                    String clazzName = clazz.getName().substring(clazz.getName().lastIndexOf("."));
                    if (canResolve(clazzName)) {
                        pointOut(clazz, sheet, true);
                    }
                } catch (NoSuchFieldException e) {
                    log.error("没有这个变量!");
                }
            }
        }
    }


    private void pointOut(Class<?> clazz, XSSFSheet sheet, boolean out) throws NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();
        if (clazz.isInterface()) return;
        genTitle(sheet);
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        System.out.println(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1));
        Cell cell = row.createCell(0);
        cell.setCellValue(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1));
        //如果是枚举类型
        if (clazz.isEnum()) {
            resolveEnum(clazz, sheet);
            return;
        }
        for (Field field : fields) {
            //存在自关联对象的话不重复设置
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            /**
             * class A { A a } 的情景
             */
            if (field.getType().equals(clazz)) {
                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                Cell cell1 = newRow.createCell(1);
                cell1.setCellValue("自关联" + clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1));
                Cell cell2 = newRow.createCell(2);
                cell2.setCellValue(field.getName());
                continue;
            }
            Class<?> currentType = clazz.getDeclaredField(field.getName()).getType();
            //不为简单类型
            if (!unResolveClass.contains(currentType)) {
                String typeName = currentType.getName();
                //1.判断是否为集合泛型类型
                if (currentType.equals(List.class) || currentType.equals(Set.class)) {
                    Type genericType = field.getGenericType();
                    if (null != genericType) {
                        if (genericType instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) genericType;
                            // 得到泛型里的class类型对象
                            Type type = pt.getActualTypeArguments()[0];
                            if (!type.getTypeName().equals("T") && !type.getTypeName().equals("E")) {
                                Class<?> actualTypeArgument = null;
                                try {
                                    actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                                    String aName = actualTypeArgument.getName();
                                    Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                                    Cell cell1 = newRow.createCell(1);
                                    cell1.setCellValue(field.getName());
                                    Cell cell2 = newRow.createCell(2);
                                    cell2.setCellValue("List<" + aName.substring(aName.lastIndexOf(".") + 1) + ">");
                                    /**
                                     * A {
                                     * List<A> list
                                     *     } 的情景不进行递归解析
                                     */
                                    if (!actualTypeArgument.equals(clazz) && !unResolveClass.contains(actualTypeArgument)) {
                                        pointOut(actualTypeArgument, sheet, false);
                                    }
                                } catch (Exception e) {

                                }
                            } else {
                                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                                Cell cell1 = newRow.createCell(1);
                                cell1.setCellValue(field.getName());
                                Cell cell2 = newRow.createCell(2);
                                cell2.setCellValue("List<" + type.getTypeName() + ">");
                            }
                        }
                    }
                } else {
                    System.out.println(field.getName() + " " + typeName.substring(typeName.lastIndexOf(".") + 1));
                    Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                    Cell cell1 = newRow.createCell(1);
                    cell1.setCellValue(field.getName());
                    Cell cell2 = newRow.createCell(2);
                    cell2.setCellValue(typeName.substring(typeName.lastIndexOf(".") + 1));
                    pointOut(currentType, sheet, false);
                }
            } else {
                //简单类型直接生成文档
                String ClassType = clazz.getDeclaredField(field.getName()).getType().toString();
                Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
                Cell cell1 = newRow.createCell(1);
                cell1.setCellValue(field.getName());
                Cell cell2 = newRow.createCell(2);
                cell2.setCellValue(ClassType.substring(ClassType.lastIndexOf(".") + 1));
            }
        }

        if (out) {
            //到达本次递归终点
            sheet.createRow(sheet.getLastRowNum() + 1);
        }

    }

    private static void genTitle(XSSFSheet sheet) {
        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
    }


    private static XSSFSheet genSheet(XSSFWorkbook workbook, String sheetName) {
        //生成表单
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表单文本居中
        sheet.setHorizontallyCenter(true);
        sheet.setFitToPage(false);
        //打印时在底部右边显示文本页信息
        Footer footer = sheet.getFooter();
        footer.setRight("Page " + HeaderFooter.numPages() + " Of " + HeaderFooter.page());
        //打印时在头部右边显示Excel创建日期信息
        Header header = sheet.getHeader();
        header.setRight("Create Date " + HeaderFooter.date() + " " + HeaderFooter.time());
        //设置打印方式
        XSSFPrintSetup ps = sheet.getPrintSetup();
        ps.setLandscape(true); // true：横向打印，false：竖向打印 ，因为列数较多，推荐在打印时横向打印
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //打印尺寸大小设置为A4纸大小
        return sheet;
    }

    @Override
    public void generateWorkBook(XSSFWorkbook workbook, HttpServletResponse response, MultipartFile file) {
        XSSFSheet sheet = genSheet(workbook, SHEET_NAME);
        Set<Class<?>> classesSet = getJarLoader(file);
        this.generateWorkBook(classesSet, sheet);
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(TABLE_NAME, "UTF-8") + ".xlsx");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 判断是否解析
     *
     * @return
     */
    private static boolean canResolve(String clazzName) {
        for (String val : RESOLVE) {
            if (StringUtils.containsIgnoreCase(clazzName, val)) {
                return true;
            }
        }
        return false;
    }


    public static <T> void resolveEnum(Class<T> clazz, XSSFSheet sheet) {
        for (T t : clazz.getEnumConstants()) {
            Row newRow = sheet.createRow(sheet.getLastRowNum() + 1);
            Cell cell1 = newRow.createCell(1);
            cell1.setCellValue("枚举类型");
            Cell cell2 = newRow.createCell(2);
            cell2.setCellValue(t.toString());
        }
    }
}
