package com.assignment.conv.model;

import java.util.Arrays;

public enum IntergalaticNumerals {
	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private int value;

	private IntergalaticNumerals(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static String getRomanNumerals(int number, int length) {
		String[] strArr = new String[length];
		int i = length - 1;
		int num = number;
		int mod = -1;
		int tense = 1;
		for (; num > 0; num /= 10) {
			mod = num % 10;
			switch (tense) {
			case 1:
				break;// unit
			case 2:
				mod = mod * 10;// ten
				break;
			case 3:
				mod = mod * 100;// hundred
				break;
			case 4:
				mod = mod * 1000;// thousand
				break;
			}
			;
			tense++;
			System.out.println("mod = " + mod);
			boolean found = false;
			for (IntergalaticNumerals inum : IntergalaticNumerals.values()) {
				if (inum.getValue() == mod) {
					strArr[i--] = inum.name();
					found = true;
					System.out.println("str found in numeral = "
							+ strArr[i + 1]);
					break;
				}
			}
			if (found) {
				continue;
			}

			if (mod < 1) {
				strArr[i--] = "";

				continue;
			} else {
				if (mod < IntergalaticNumerals.I.getValue()) {
					// 0
					// do nothing
				} else if (mod < IntergalaticNumerals.V.getValue()) {
					// between I and V i.e 1 and 5
					if (mod == (IntergalaticNumerals.V.getValue() - IntergalaticNumerals.I
							.getValue())) {
						strArr[i--] = "IV";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}
					// so multiple of I
					int n = mod / IntergalaticNumerals.I.getValue();
					StringBuffer strBf = new StringBuffer();
					for (int j = 0; j < n; j++) {
						strBf.append(IntergalaticNumerals.I.name());
					}

					strArr[i--] = strBf.toString();
					System.out.println("str = " + strArr[i + 1]);
					continue;

				} else if (mod < IntergalaticNumerals.X.getValue()) {
					// between I and X
					if (mod == (IntergalaticNumerals.X.getValue() - IntergalaticNumerals.I
							.getValue())) {
						strArr[i--] = "IX";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}// "V", "L", and "D" can never be subtracted.

				} else if (mod < IntergalaticNumerals.L.getValue()) {
					// between I and L
					if (mod == (IntergalaticNumerals.L.getValue() - IntergalaticNumerals.X
							.getValue())) {
						strArr[i--] = "XL";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}// "V", "L", and "D" can never be subtracted.

				} else if (mod < IntergalaticNumerals.C.getValue()) {
					// between I and C
					if (mod == (IntergalaticNumerals.C.getValue() - IntergalaticNumerals.X
							.getValue())) {
						strArr[i--] = "XC";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}// "V", "L", and "D" can never be subtracted.

				} else if (mod < IntergalaticNumerals.D.getValue()) {
					// between I and D
					if (mod == (IntergalaticNumerals.D.getValue() - IntergalaticNumerals.C
							.getValue())) {
						strArr[i--] = "CD";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}// "V", "L", and "D" can never be subtracted.

				} else if (mod < IntergalaticNumerals.M.getValue()) {
					// between I and M
					if (mod == (IntergalaticNumerals.M.getValue() - IntergalaticNumerals.C
							.getValue())) {
						strArr[i--] = "CM";
						System.out.println("str = " + strArr[i + 1]);
						continue;
					}// "V", "L", and "D" can never be subtracted.

				}
			}
		}
		String str = Arrays.toString(strArr).replaceAll(", ", "")
				.replace("[", "").replace("]", "");
		System.out.println("str = " + str);
		return str;
	}
}
