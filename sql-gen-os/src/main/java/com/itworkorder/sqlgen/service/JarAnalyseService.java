package com.itworkorder.sqlgen.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.jar.JarEntry;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-20  15:33
 * @version 1.0
 */
public interface JarAnalyseService {

    /**
     * 加载jar包中的Class
     *
     * @param entry
     * @param loader
     * @return
     */
    Class<?> loadJarClassFile(JarEntry entry, ClassLoader loader);

    /**
     * 加载类
     *
     * @param jarFile
     * @return
     */
    Set<Class<?>> getJarLoader(MultipartFile jarFile);


    void generateWorkBook(Set<Class<?>> classSet, XSSFSheet sheet);


    void generateWorkBook(XSSFWorkbook workbook, HttpServletResponse response, MultipartFile file) throws UnsupportedEncodingException;







}
