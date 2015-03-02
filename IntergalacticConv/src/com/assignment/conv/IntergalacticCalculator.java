package com.assignment.conv;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment.conv.model.IntergalacticConversionGuide;
import com.assignment.conv.model.IntergalaticNumerals;
import com.assignment.conv.util.ConversionConstants;
import com.assignment.conv.util.InputReader;
import com.assignment.conv.util.NumberUtility;

public class IntergalacticCalculator {

	private static final Logger log = Logger
			.getLogger(IntergalacticCalculator.class.getName());

	private static IntergalacticConversionGuide guide = new IntergalacticConversionGuide();

	public static String INPUTFILE = "/com/assignment/conv/input.txt";

	private static List<String> questions = new ArrayList<>();

	private static List<String> singleComputation = new ArrayList<>();

	private static Map<String, Float> metalValues = new HashMap<>();

	private static Map<String, String> displayConv = new HashMap<>();

	public static void main(String[] args) {
		start();
	}

	public static IntergalacticConversionGuide getGuide() {
		return guide;
	}

	public static void setGuide(IntergalacticConversionGuide guide) {
		IntergalacticCalculator.guide = guide;
	}

	public static Integer sum(String[] arr) {
		int sum = 0;
		int i = 0;
		if (arr != null && arr.length > 0) {

			if (!validateSubRule(arr.toString().replace("[", "")
					.replace("]", ""))) {
				return -1;
			}
			IntergalaticNumerals prevVal = null;

			for (i = 0; i < arr.length; i++) {
				IntergalaticNumerals num = null;// this is evaluated in next
												// loop
				try {
					num = IntergalaticNumerals.valueOf(arr[i]);
				} catch (Exception e) {
					continue;
				}
				if (prevVal != null) {
					if (prevVal.getValue() < num.getValue()) {
						sum -= prevVal.getValue();
					} else {
						sum += prevVal.getValue();
					}
				}

				prevVal = num;

				log.log(Level.INFO, "sum= " + sum);
			}

			sum += prevVal.getValue();
			log.log(Level.INFO, "sum= " + sum);

		}
		return sum;
	}

	/**
	 * "M" highest and "I" lowest "I" can be subtracted from "V" and "X" only
	 * i.e only IV and IX allowed. "X" can be subtracted from "L" and "C" only
	 * i.e only XL and XC allowed. "C" can be subtracted from "D" and "M" only
	 * i.e only CD and CM allowed. "V", "L", and "D" can never be subtracted.
	 * Check The symbols "I", "X", "C", and "M" can be repeated three times in
	 * succession, but no more. (They may appear four times if the third and
	 * fourth are separated by a smaller value, such as XXXIX.) "D", "L", and
	 * "V" can never be repeated. Only one small-value symbol may be subtracted
	 * from any large-value symbol.
	 * 
	 * 
	 * @param str
	 * @return
	 */
	public static boolean validateSubRule(String str) {

		boolean isValid = false;

		Pattern pattern = Pattern
				.compile("(^M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})");

		Matcher m = pattern.matcher(str);

		if (m.find()) {
			isValid = true;
			log.log(Level.INFO, str + " is Valid Intergalactic Unit!");
		} else {
			log.log(Level.SEVERE, str + "is Invalid Intergalactic Unit!");
		}

		return isValid;
	}

	public static String findCredits(String[] dispArr, Float metalVal) {
		log.log(Level.INFO, "Evaluating Display String is " + dispArr);

		String[] originalValues = getIntergalaticNumeralArray(dispArr);

		Integer sum = findInterGalacticSum(originalValues);

		// find metal name and value
		Float totalSum = sum * metalVal;

		return Math.round(totalSum) + "";
	}

	/**
	 * Finds sum or finds RomanNumeral
	 * 
	 * @param str
	 * @return
	 */
	public static Integer findInterGalacticSum(String str) {
		System.out.println("Question: " + str);

		// convert to array of alphabets
		String[] originalValues = str.split("");

		return findInterGalacticSum(originalValues);
	}

	/**
	 * Finds sum or finds RomanNumeral
	 * 
	 * @param str
	 * @return
	 */
	public static Integer findInterGalacticSum(String[] originalValues) {
		// System.out.println("Array  : " + originalValues);
		Integer sum = null;
		// evaluate string
		try {
			sum = sum(originalValues);
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.log(Level.INFO, "Sum: " + sum);
		return sum;
	}

	public static Float findMetalValue(String[] dispArray, String credits) {
		Float result = 0.0f;
		try {
			String[] originalValues = getIntergalaticNumeralArray(dispArray);

			Integer sum = findInterGalacticSum(originalValues);
			log.log(Level.INFO, " Sum = " + sum);

			result = Float.parseFloat(credits) / sum;
		} catch (Exception e) {
			log.log(Level.SEVERE, "findMetalValue", e);
		}
		return result;
	}

	public static String findInterGalacticRomanNumeral(String str) {
		System.out.println("Question: " + str);
		String result = null;
		Number num = null;
		try {
			num = NumberFormat.getInstance().parse(str);
		} catch (ParseException e) {
			// Not a number but string
		}
		if (num != null) {
			// evaluate Number
			int number = num.intValue();
			int len = NumberUtility.lengthOfNumber(number);
			if (len > 4) {
				System.out.println("Answer: "
						+ ConversionConstants.UNKNOWNERROR);
			} else {
				result = IntergalaticNumerals.getRomanNumerals(number, len);

			}
		}
		System.out.println("Answer: " + result);
		return result;
	}

	public static Integer findDisplayConvValue(String arrayStr) {
		String[] originalValues = getIntergalaticNumeralArray(arrayStr
				.split(" "));

		Integer sum = sum(originalValues);
		return sum;
	}

	public static String[] getIntergalaticNumeralArray(String[] dispArray) {
		String[] originalValues = new String[dispArray.length];
		int index = 0;
		for (String dispVal : dispArray) {
			originalValues[index++] = displayConv.get(dispVal);
		}
		return originalValues;
	}

	public static void start() {
		// read input from file
		readInput();

		// show output
		for (String str : questions) {
			evaluate(str);
		}

		// single string computation
		// covered in JUNIT
		/*
		 * for (String str : singleComputation) { Integer number =
		 * findInterGalacticSum(str); if (number == null) {
		 * findInterGalacticRomanNumeral(str); } }
		 */
	}

	public static void readInput() {
		// read data from file

		InputReader reader = null;
		try {
			reader = new InputReader(INPUTFILE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.log(Level.INFO, "Input read is: " + reader.getAllLineEntries());

		// parse Input and process
		Iterator<String> itr = reader.getAllLineEntries().iterator();

		while (itr.hasNext()) {

			String str = itr.next();

			String[] arr = str.split(" ");// space

			if (arr != null) {
				if (arr.length == 1) {
					singleComputation.add(str);

				} else if (arr.length == 3) {
					// for initial display conventions
					if (ConversionConstants.DELIMITER_DISPLAY_CONVENTION
							.equals(arr[1])) {
						// example: glob is I
						log.log(Level.INFO, str + " is a display convention");
						displayConv.put(arr[0], arr[2]);
					}

				} else if (str.contains(ConversionConstants.QUESTION)
						&& (str.toLowerCase().contains(ConversionConstants.STR_HOWMANYCREDIT) 
								|| str.toLowerCase().contains(ConversionConstants.STR_HOWMUCH))) {
					// read questions
					questions.add(str);
					log.log(Level.INFO, str + " is a question");

				} else if (str
						.contains(ConversionConstants.DELIMITER_DISPLAY_CONVENTION)
						&& str.contains(ConversionConstants.CREDITS)) {
					// for metal values
					int mid = -1;
					// find mid - metal name
					for (mid = 0; mid < arr.length; mid++) {
						if (guide.getMetalList().contains(arr[mid])) {

							// calculate value of metal
							// store it
							log.log(Level.INFO, str + " is a metal conversion");
							break;
						}
					}

					try {
						if (mid > -1) {
							// calculate value of metal
							String[] dispArray = Arrays.copyOf(arr, mid);

							Float credits = IntergalacticCalculator
									.findMetalValue(dispArray, arr[mid + 2]);

							metalValues.put(arr[mid], credits);
							log.log(Level.INFO, arr[mid] + " value is "
									+ credits);
						}
					} catch (Exception e) {
						log.log(Level.SEVERE, "readInput():", e);

					}
				}
			}

		}

	}

	public static void evaluate(String str) {

		System.out.println("Question: " + str);

		if (str.toLowerCase().contains(ConversionConstants.STR_HOWMUCH+" is")) {

			String arrayStr = str.replace(ConversionConstants.QUESTION, "")
					.replace(ConversionConstants.STR_HOWMUCH + " is", "")
					.trim();

			log.log(Level.INFO, "Evaluating Display String : " + arrayStr);

			Integer sum = IntergalacticCalculator
					.findDisplayConvValue(arrayStr);

			System.out.println("Answer: " + arrayStr + " is " + sum);

		} else if (str.toLowerCase().contains(
				ConversionConstants.STR_HOWMANYCREDIT)) {

			String arrayStr = str.replace(ConversionConstants.QUESTION, "")
					.replace("how many Credits is", "").trim();

			String[] dispArr = arrayStr.split(" ");

			Float metalVal = metalValues.get(dispArr[dispArr.length - 1]);

			String total = IntergalacticCalculator.findCredits(dispArr,
					metalVal);

			System.out.println("Answer: " + arrayStr + " is " + total
					+ " Credits");
		} else {
			System.out.println("Answer: " + ConversionConstants.UNKNOWNERROR);
		}

	}

}
