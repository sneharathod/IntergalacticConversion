package com.assignment.conv.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputReader {

	private static final Logger log = Logger.getLogger(PropertyReader.class
			.getName());

	private String propFileName = null;

	private List<String> lineEntries = new ArrayList<String>();

	public InputReader(String propFileName) throws IOException {
		this.propFileName = propFileName;
		readLineFile();
	}

	private void readLineFile() throws IOException {

		InputStream inputStream = this.getClass().getResourceAsStream(
				propFileName);

		if (inputStream != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(inputStream));
				String str = null;
				while ((str = reader.readLine()) != null) {
					lineEntries.add(str);
				}
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		} else {
			log.log(Level.SEVERE, "Input File Not Found");
			throw new FileNotFoundException("Input File not found!");
		}
	}

	public List<String> getAllLineEntries() {
		return lineEntries;
	}
}
