package com.limon.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	public static String getConfig(String key) {
		Properties p = new Properties();
		try {
			p.load(PropertiesUtil.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
