package com.twitter.log;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class GestorDeTraza {
	private static final Logger logger = LogManager.getLogger(GestorDeTraza.class);
	
	
	private static void init(String level){
		Properties log4jProperties = new Properties();
		log4jProperties.setProperty("log4j.rootLogger", level);
		log4jProperties.setProperty("log4j.appender.myConsoleAppender", "org.apache.log4j.ConsoleAppender");
		log4jProperties.setProperty("log4j.appender.myConsoleAppender.layout", "org.apache.log4j.PatternLayout");
		log4jProperties.setProperty("log4j.appender.myConsoleAppender.layout.ConversionPattern", "[%-5p] %d - %m%n");
		PropertyConfigurator.configure(log4jProperties);
	}
	
	public static void debug(String className, String methodName, String message){
		init("DEBUG, myConsoleAppender");
		logger.debug("[ClassName: " + className + "] [methodName: " + methodName + "] [message: " + message + "]");
	} 
	
	public static void info(String className, String methodName, String message){
		init("INFO, myConsoleAppender");
		logger.info("[ClassName: " + className + "] [methodName: " + methodName + "] [message: " + message + "]");
	} 
	
	public static void error(String className, String methodName, String message){
		init("ERROR, myConsoleAppender");
		logger.error("[ClassName: " + className + "] [methodName: " + methodName + "] [message: " + message + "]");
	}
	
	public static void error(String className, String methodName, String message, Exception e){
		error(className, methodName, message +" - "+ e.getMessage());
	}

}
