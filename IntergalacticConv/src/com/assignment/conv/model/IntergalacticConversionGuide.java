package com.assignment.conv.model;

import java.util.ArrayList;
import java.util.List;

import com.assignment.conv.util.ConversionConstants;
import com.assignment.conv.util.InputReader;

public class IntergalacticConversionGuide {

	private List<String> metalList = buildMetalList();

	private List<String> buildMetalList() {
		List<String> list = new ArrayList<>();
		// read from property file
		try {
			list = new InputReader(ConversionConstants.METAL_FILE)
					.getAllLineEntries();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> getMetalList() {
		return metalList;
	}

	public void setMetalList(List<String> metalList) {
		this.metalList = metalList;
	}
}
