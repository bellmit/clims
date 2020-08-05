package com.bazl.clims.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

/**
 * Created by Administrator on 2017/1/2.
 */
public class UplodFtpUtils {

    public static String uploadFtpFile(String ftpIp, String ftpPort, String ftpUser, String ftpPassword, String ftpPersonImg, String fileName){
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local=null;
        String path = "";
        try {
            //连接ftp服务器
            ftp.connect(ftpIp, Integer.valueOf(ftpPort));
            //登录
            ftp.login(ftpUser, ftpPassword);
            //设置上传路径
            path="/"+ftpPersonImg;
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if(!flag){
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
        }finally {
            try {
                //关闭文件流
                local.close();
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


}
