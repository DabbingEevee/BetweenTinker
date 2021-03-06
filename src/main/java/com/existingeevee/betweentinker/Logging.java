package com.existingeevee.betweentinker;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {
	private static final Logger LOGGER = LogManager.getLogger(VersionInfo.NAME);
	
	public static void Log(Level level, String strMessage) {
		if (level == Level.INFO) {
			LOGGER.info(strMessage);
		}
	}
	
	public static void Log(String str) {
		Log(Level.INFO, str);
	}

}
