package com.bazl.clims.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/2/2.
 */
@Controller
public class DownloadChromeController extends BaseController {

    @RequestMapping("/chromeForXp")
    public void downloadForXp(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/chrome");
        String fileName = "ChromeForXP_setup.exe";
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            output(fileName, file, response);
        }
    }

    @RequestMapping("/chromeForWin7")
    public void downloadForWin7(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/chrome");
        String fileName = "ChromeForWin7-8-10_setup.exe";
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            output(fileName, file, response);
        }
    }

    private void output(String fileName, Path file, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);//类型
        try {
            Files.copy(file, response.getOutputStream());
        } catch (IOException ex) {
            logger.error("下载出现错误！", ex);
        }
    }

    @RequestMapping("/printDna")
    public void printDna(HttpServletRequest request, HttpServletResponse response) {
        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/drive");
        String fileName = "PrintDna.msi";
        Path file = Paths.get(dataDirectory, fileName);
        if (Files.exists(file)) {
            output(fileName, file, response);
        }
    }

}
