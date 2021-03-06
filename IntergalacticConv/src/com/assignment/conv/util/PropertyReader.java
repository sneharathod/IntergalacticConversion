package com.assignment.conv.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {

	private static final Logger log = Logger.getLogger(PropertyReader.class
			.getName());

	private String propFileName = null;

	private Properties prop = new Properties();

	public PropertyReader(String propFileName) throws IOException {
		this.propFileName = propFileName;
		readFile();
	}

	private void readFile() throws IOException {

		InputStream inputStream = this.getClass().getResourceAsStream(
				propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			log.log(Level.SEVERE, "Configuration File Not Found");
			throw new FileNotFoundException("Configuration file not found!");
		}
	}

	/*
	 * public String getProperty(String propName) { String result =
	 * prop.getProperty(propName);
	 * 
	 * log.log(Level.INFO, propName + " = " + result);
	 * 
	 * return result; }
	 */

	public Properties getAllProperties() {
		return prop;
	}

}
