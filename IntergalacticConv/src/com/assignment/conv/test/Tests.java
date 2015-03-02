package com.assignment.conv.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.assignment.conv.IntergalacticCalculator;

public class Tests extends TestCase {

	@Test
	public void test() {
		IntergalacticCalculator.start();
	}

	/**
	 * MCMXLIV = 1944 1903 = MCMIII XXXIX = 39 MMVI =2006 M = 1000
	 */
	@Test
	public void test2() {
		Integer number = IntergalacticCalculator
				.findInterGalacticSum("MCMXLIV");
		assertEquals(number == 1944, true);
	}

	/**
	 * MCMXLIV = 1944 1903 = MCMIII XXXIX = 39 MMVI =2006 M = 1000
	 */
	@Test
	public void test3() {
		String result = IntergalacticCalculator
				.findInterGalacticRomanNumeral("1903");
		assertEquals(result.equals("MCMIII"), true);
	}

	@Test
	public void test4() {
		boolean isValid1 = IntergalacticCalculator.validateSubRule("XXXIX");
		assertEquals(isValid1 == true, true);
		
		boolean isValid2 = IntergalacticCalculator.validateSubRule("XXXXX");
		assertEquals(isValid2, false);
	}

	@Test
	public void test5() {
		Integer number = IntergalacticCalculator.findInterGalacticSum("M");
		assertEquals(number == 1000, true);
	}

}
