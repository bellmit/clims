package com.bazl.clims.utils;

import com.bazl.clims.model.po.LimsCaseInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author wanghaiyang
 * @date 2019/2/21.
 */
public class GeneratePathUtil {

    public static String getGeneratePath(LimsCaseInfo caseInfo, String filename){
        String writeFilePath = SystemUtil.getSystemConfig().getProperty("identifyBookPath");
        writeFilePath = makePathFile(writeFilePath);
        writeFilePath = makePathFile(writeFilePath + DateUtils.dateToString(new Date(), DateUtils.YEAR_MM));
        String rebuildPath = SystemUtil.getSystemConfig().getProperty("rebuildPath");
        writeFilePath = makePathFile(writeFilePath + rebuildPath);

        String writeFile = "";
        if (writeFilePath.endsWith("\\") || writeFilePath.endsWith("/")) {
            writeFile += writeFilePath + filename;
        } else {
            writeFile = writeFilePath + System.getProperty("file.separator") + filename;
        }

        return writeFile;
    }

    /**
     * 验证目录是否存在，如果不存在，则创建对应目录。
     *
     * @param filePathName
     */
    public static String makePathFile(String filePathName) {

        File pathFile = new File(filePathName);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        return pathFile + "\\";
    }

}
