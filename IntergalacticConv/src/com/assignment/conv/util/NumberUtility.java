package com.assignment.conv.util;

public class NumberUtility {
	
	public static int lengthOfNumber(int num) {
		int length = 0;
		for (length = 0; num > 0; length++) {
			num /= 10;
		}
		return length;
	}
	
}
