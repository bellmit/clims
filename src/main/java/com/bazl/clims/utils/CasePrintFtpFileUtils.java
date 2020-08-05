package com.bazl.clims.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;

public class CasePrintFtpFileUtils {
    public static String uploadFtpFileNew(String ftpIp, String ftpPort, String ftpUser, String ftpPassword, String ftpPersonImg, String fileName, HttpServletRequest request, String page,String fullPrint) {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        InputStream local = null;
        String path = "";
        String ftpFileName = "";
        try {
            //连接ftp服务器
            ftp.connect(ftpIp, Integer.valueOf(ftpPort));
            //登录
            ftp.login(ftpUser, ftpPassword);
            ftp.setControlEncoding("UTF-8");
            //设置上传路径
//            path = "/" + ftpPersonImg;
            path = ftpPersonImg +"/";
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
//             File file = new File("E:\111.docx");
            File file = new File(fileName);

            local = new FileInputStream(file);
            //第一个参数是文件名

            ftpFileName = file.getName();
//            ftp.storeFile(file.getName(), local);
            ftp.storeFile(new String(file.getName().getBytes("gbk"), "iso-8859-1"), local);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流
                local.close();
                //进行打印文件 打印
                if("1".equals(fullPrint)){

                    //下载
                }if("2".equals(fullPrint)){

                }else if (fullPrint ==null){
                    String s = httpLinkNew(request, ftpIp, page, ftpUser, ftpPassword, ftpPersonImg, fileName.split("\\\\")[3]);
                }

                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ftpFileName;
    }


    public static String httpLinkNew(HttpServletRequest request, String ftpIp, String page, String ftpUser, String ftpPassword, String ftpFilePath, String fileName) {
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

            String docpath="ftp://" + ftpIp + "/" + ftpFilePath + "/";
//            String docpath= ftpFilePath;
            StringBuffer strBuf = new StringBuffer();
            strBuf.append("{\"docPath\":\"" + docpath + "\", \"page\":\"" + page+ "\", \"username\":\"" + ftpUser+ "\",\"userpwd\":\"" + ftpPassword + "\",\"fileName\":\"" + fileName + "\"}\r\n");


            String str1= null;
//            String str2= null;
//            try {
//                str1 = new String(strBuf.toString().getBytes("UTF-8"));
//                str2 = URLEncoder.encode(str1, "UTF-8");
//                System.out.println("utf-8 编码：" + str2) ;
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            String str = strBuf.toString();
            str1 = new String(strBuf.toString().getBytes("UTF-8"));
            System.out.println("utf-8 编码：" + str1) ;
            // 将参数输出到连接
            dataout.write(str1.toString().getBytes());
//            dataout.write(strBuf.toString().getBytes());
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            BufferedReader bd = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
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
