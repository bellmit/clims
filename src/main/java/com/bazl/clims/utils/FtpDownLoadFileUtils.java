package com.bazl.clims.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Administrator on 2019/5/28.
 */
public class FtpDownLoadFileUtils {

    public byte[] getImageBinary(String localPath, String filename)
    {
        File f = new File(localPath + "/" + filename);

        byte[] bytes = new byte[1024];
        try {
            BufferedImage bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean downFile(String url, String port, String username, String password, String remotePath, String fileName, String localPath)
    {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try
        {
            if(!new File(localPath).exists())   {
                new File(localPath).mkdirs();
            }

            int prot1 = Integer.valueOf(port);
            ftp.connect(url, prot1);

            ftp.login(username, password);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);
            FTPFile[] fs = ftp.listFiles();

            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();

            if (ftp.isConnected())
                try {
                    ftp.disconnect();
                }
                catch (IOException localIOException2)
                {
                }
        }
        finally
        {
            if (ftp.isConnected())
                try {
                    ftp.disconnect();
                }
                catch (IOException localIOException3) {
                }
        }
        return success;
    }
}
