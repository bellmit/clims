package com.bazl.clims.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;

public class PrintFtpFileUtils {
    public static String uploadFtpFile(String ftpIp, String ftpPort, String ftpUser, String ftpPassword, String ftpPersonImg, String fileName, HttpServletRequest request, String page) {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        String path = "";
        try {
            //连接ftp服务器
            ftp.connect(ftpIp, Integer.valueOf(ftpPort));
            //登录
            ftp.login(ftpUser, ftpPassword);
            //设置上传路径
            path = "/" + ftpPersonImg;
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if (!flag) {
                //创建上传的路径  该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型  二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //读取本地文件
            // File file = new File("D:/111.doc");
            File file = new File(fileName);

            local = new FileInputStream(file);
            //第一个参数是文件名

             ftp.storeFile(file.getName(), local);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流
                local.close();
                //进行打印文件
                String s = httpLink(request, ftpIp, page, ftpUser, ftpPassword, ftpPersonImg, fileName.split("\\\\")[3]);
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }


       private static String httpLink(HttpServletRequest request, String ftpIp, String page, String ftpUser, String ftpPassword, String ftpFilePath, String fileName) {
        //获取访问者的ip
        String url = "http://"+IpAddressUtils.getIpAddr(request)+":927/PrintDoc?jquest=2342";
        StringBuilder json = new StringBuilder();
        String result = "";
        try {
            URL u = new URL(url);
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            // 设置请求方式为post
            uc.setRequestMethod("POST");
            //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            uc.setDoOutput(true);
            // post请求缓存设为false
            uc.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            uc.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据
            //application/x-javascript->json对象
            //application/x-www-form-urlencoded->表单数据
            //这是重点^_^
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            uc.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(uc.getOutputStream());

            String docpath="ftp://" + ftpIp + "/" + ftpFilePath + "/" + fileName;
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("{\"docPath\":\"" + docpath + "\", \"page\":\"" + page+ "\", \"username\":\"" + ftpUser+ "\",\"userpwd\":\"" + ftpPassword + "\"}\r\n");

            // 将参数输出到连接
            dataout.write(strBuf.toString().getBytes());
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            BufferedReader bd = new BufferedReader(new InputStreamReader(uc.getInputStream(), "GBK"));
            String s = null;
            while ((s = bd.readLine()) != null) {
                json.append(s);
            }
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = json.toString();
        return result;
    }

}
