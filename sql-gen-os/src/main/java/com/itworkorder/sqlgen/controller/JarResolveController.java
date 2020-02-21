package com.itworkorder.sqlgen.controller;

import com.itworkorder.sqlgen.service.JarAnalyseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author yucheng.liu@ucarinc.com
 * Date: 2020-2-20  21:50
 * @version 1.0
 */
@Controller
@RequestMapping("/api/jar")
@Slf4j
public class JarResolveController {

    @Autowired
    private JarAnalyseService jarAnalyseService;

    @RequestMapping("/resolveJar.do")
    public void resolveJAR(MultipartFile jarFile, HttpServletResponse response) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            jarAnalyseService.generateWorkBook(workbook, response, jarFile);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
