package com.bazl.clims.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

/**
 * @author wanghaiyang
 * @date 2019/2/25.
 */
@Controller
public class DownloadFileController extends BaseController {

    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath) {

        if (StringUtils.isNotBlank(filePath)) {
            try {
                filePath = URLDecoder.decode(filePath, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        File file = new File(filePath);

        String downloadName = file.getName();

        OutputStream out = null;
        FileInputStream in = null;
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + new String(downloadName.getBytes("GBK"),"ISO8859-1"));

            out = response.getOutputStream();
            byte d[] = new byte[3052];
            int count = 0;
            in = new FileInputStream(filePath);
            while ((count = in.read(d)) != -1) {
                out.write(d, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.info("下载失败:" + ex);
            }
        }
    }

}
