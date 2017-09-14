package com.minxinloan.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;


public final class JixinPropertyUtil {

	/** 日志 */
	private static Logger log = Logger.getLogger(JixinPropertyUtil.class);
	

	/** 资源属性 */
	private static Properties properties;

 
    
	private JixinPropertyUtil() {
	}

	static {
		properties = new Properties();
		try {
			// 读取配置文件
			properties.load(JixinPropertyUtil.class.getClassLoader().getResourceAsStream("jixin.properties"));
		//	if("0".equals(properties.getProperty("config_online"))){
				//properties.load(JixinPropertyUtil.class.getClassLoader().getResourceAsStream("jixin_test.properties"));
			//}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("读取配置文件出错，请确认jixin.properties文件已经放到src目录下。");
		}
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @return 配置信息
	 */
	public static String getMessage(String key) {
		String value = null;
		value = properties.getProperty(key);
		if (null == value || "".equals(value)) {
			log.info("没有获取指定key的值，请确认资源文件中是否存在【" + key + "】");
		}
		return value;
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @param param 参数
	 * @return 配置信息
	 */
	public static String getMessage(String key, Object[] param) {
		String value = getMessage(key);
		return MessageFormat.format(value, param);
	}

}
