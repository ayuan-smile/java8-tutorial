package com.sitech.prom.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**   
 * @Title: PropertiesUtils
 * @Description: KMAN(用一句话描述这个类的作用) 
 * @author 阿袁 yuanxm@si-tech.com.cn
 */
public class PropertiesUtils {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	
	/** 
	 * 缓存
	 */ 
	private static Map<String,Properties> cache = new HashMap<>();
	
	public static Properties loadProps(String name){
		return loadProps(name, PropertiesUtils.class);
	}
	
	public static Properties loadProps(String name,Class<?> clazz){
		InputStream is = null;
		Properties props = null;
		
		//做空判断
		if( name == null || name.equals("")){
			return null;
		}
		try {
			props = cache.get(name);
			if(props == null){
				if(clazz != null){
					is = PropertiesUtils.class.getResourceAsStream(name);
				}else{
					is = ClassLoader.getSystemResourceAsStream(name);
				}
				if(is != null){
					props = new Properties();
					props.load(is);
					cache.put(name, props);
				}else{
					logger.info("{} is invalid path,please check!", name);
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(is !=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	
	public static void main(String[] args) {
		Properties loadProps = PropertiesUtils.loadProps("/conf/conf.properties");
		System.out.println(loadProps.toString());
		System.out.println(PropertiesUtils.loadProps("/conf/conf1.properties",null));
		
	}
}
