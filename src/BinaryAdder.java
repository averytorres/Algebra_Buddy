public class BinaryAdder {

	private int num1;
	private int num2;

	public BinaryAdder(int inNum1, int inNum2) {

		num1 = inNum1;
		num2 = inNum2;
	}

	public int add() {

		String longerBinaryNum = String.valueOf(Integer.max(num1, num2));
		String shorterBinaryNum = String.valueOf(Integer.min(num1, num2));
		StringBuilder result = new StringBuilder();
		final int DIFF = longerBinaryNum.length() - shorterBinaryNum.length();
		final int NUMBER_LENGTH = String.valueOf(longerBinaryNum).length();
		boolean NumberIsNotZero = Integer.valueOf(longerBinaryNum) > 0;
		boolean carry = false;

		shorterBinaryNum = lengthen(shorterBinaryNum, DIFF);

		if (NumberIsNotZero) {
			for (int i = 0; i < NUMBER_LENGTH; i++) {
				boolean currentLongerPositionValueIsOne = longerBinaryNum
						.charAt(longerBinaryNum.length() - (i + 1)) == '0';
				if (currentLongerPositionValueIsOne) {
					carry = compareToZero(longerBinaryNum, shorterBinaryNum,
							result, carry, i);
				} else {
					carry = compareToOne(longerBinaryNum, shorterBinaryNum,
							result, carry, i);
				}
			}
		} else {
			result.append("0");
		}

		return Integer.valueOf(result.toString());
	}

	private boolean compareToOne(String longerBinaryNum,
			String shorterBinaryNum, StringBuilder result, boolean carry, int i) {
		boolean currentShorterPositionValueIsZero = shorterBinaryNum
				.charAt(shorterBinaryNum.length() - (i + 1)) == '0';

		if (currentShorterPositionValueIsZero) {
			carry = longerZeroShorterOne(longerBinaryNum, result, carry, i);
		} else {
			// both are 1
			carry = bothPositionsAreOne(longerBinaryNum, result, carry, i);
		}
		return carry;
	}

	private boolean compareToZero(String longerBinaryNum,
			String shorterBinaryNum, StringBuilder result, boolean carry, int i) {
		boolean currentShorterPositionValueIsZero = shorterBinaryNum.charAt(shorterBinaryNum.length() - (i + 1)) == '0';
		if (currentShorterPositionValueIsZero) {
			carry = bothPositionsAreZero(result, carry);
		} else {
			carry = longerZeroShorterOne(longerBinaryNum, result, carry, i);

		}
		return carry;
	}

	private boolean bothPositionsAreOne(String longerBinaryNum,
			StringBuilder result, boolean carry, int i) {
		if (carry)
			insertOneIntoResult(result);
		else
			insertZeroIntoResult(result);

		carry = true;
		if (i == longerBinaryNum.length() - 1) {
			insertOneIntoResult(result);
		}
		return carry;
	}

	private boolean longerZeroShorterOne(String longerBinaryNum,
			StringBuilder result, boolean carry, int i) {
		// longer is 0 shorter is 1
		if (!carry)
			insertOneIntoResult(result);
		else {
			insertZeroIntoResult(result);
			carry = true;
			if (i == longerBinaryNum.length() - 1) {
				insertOneIntoResult(result);
			}
		}
		return carry;
	}

	private boolean bothPositionsAreZero(StringBuilder result, boolean carry) {
		// bother are 0
		if (!carry)
			insertZeroIntoResult(result);
		else {
			insertOneIntoResult(result);
			carry = false;
		}
		return carry;
	}

	private String lengthen(String shorterBinaryNum, int diff) {
		for (int i = 0; i < diff; i++) {
			shorterBinaryNum = "0" + shorterBinaryNum;
		}
		return shorterBinaryNum;
	}

	private void insertOneIntoResult(StringBuilder result) {
		result.insert(0, "1");
	}

	private void insertZeroIntoResult(StringBuilder result) {
		result.insert(0, "0");
	}

}
