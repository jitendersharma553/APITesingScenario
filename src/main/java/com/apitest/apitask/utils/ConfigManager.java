package com.apitest.apitask.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for getting values form property file.
 * @author Jitender Sharma
 *
 */
public class ConfigManager {
	
private static Properties props = null;
	
	private static ConfigManager manager;
	private static final Properties prop = new Properties();
	
	private ConfigManager() throws IOException {
		InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
		prop.load(inputStream);
	}
	
	public static ConfigManager getInstance() {
		if(manager==null) {
			synchronized(ConfigManager.class) {
				try {
					manager = new ConfigManager();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return manager;
	}
	
	/**
	 * Get value from property file
	 * @param key
	 * @return String 
	 */
	public String getString( String key) {
		return System.getProperty(key, prop.getProperty(key));
	}

}
