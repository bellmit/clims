package com.bazl.clims.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemUtil {

	private static Properties systemConfig = new Properties();

	static {
		try {

           InputStream in = SystemUtil.class.getClassLoader().getResourceAsStream("/SystemConfig.xml");
            if(in ==null){
                in = SystemUtil.class.getClassLoader().getResourceAsStream("SystemConfig.xml");//junit
            }
			systemConfig.loadFromXML(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}

	public static Properties getSystemConfig() {
		return systemConfig;
	}

	public static void setSystemConfig(Properties systemConfig) {
		SystemUtil.systemConfig = systemConfig;
	}

}
