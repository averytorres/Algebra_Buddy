/*
 * 
 * THIS ENGINE ASSUMES THE NUMBER IS COMPLETELY CLEAN BEFORE BEING ENTERED
 * ENSURE THAT THERE ARE NO LEADING 0's IN THE ORIGINAL PASSED IN INTS AS
 * JAVA WILL TREAT THEM AS OCTAL NUMBERS, ALSO ENSURE THAT THEY ARE ACTUAL BINARY
 * NUMBERS AND CONTAIN ONLY 1'S AND 0'S
 * 
 */
public class BinarySubtractor {

	private int num1;
	private int num2;

	public BinarySubtractor(int inNum1, int inNum2) {
		num1 = inNum1;
		num2 = inNum2;
	}

	public int subtract() {

		String longerNum = String.valueOf(Integer.max(num1, num2));
		String shorterNum = String.valueOf(Integer.min(num1, num2));
		Boolean firstNumberGreater;
		final int DIFF = longerNum.length() - shorterNum.length();
		BinaryAdder adder;
		shorterNum = lengthen(shorterNum, DIFF);
		int result;
		boolean longerIsNum2 = Integer.valueOf(longerNum) == num2;
		firstNumberGreater = determineIfResultWillBeNegative(longerNum,
				shorterNum);

		if (longerIsNum2) {

			longerNum = computeTwosComplement(num2 + "", shorterNum);
			System.out.println("Twos complement: " + longerNum);
		} else {

			shorterNum = computeTwosComplement(num2 + "", longerNum);
			System.out.println("Twos complement: " + longerNum);
		}

		if (longerIsNum2) {
			result = addValues(shorterNum, longerNum);
			result = removeLeadingNumber(result);
			if (firstNumberGreater)
				result = -1 * result;

		} else {

			result = addValues(longerNum, shorterNum);
			result = removeLeadingNumber(result);
			if (firstNumberGreater)
				result = -1 * result;

		}

		return result;
	}

	private int removeLeadingNumber(int result) {
		String temp = result + "";
		if (temp.length() <= 1) {
			result = 0;
		} else {
			result = Integer.valueOf(temp.substring(1));
		}
		return result;
	}

	private int addValues(String val1, String val2) {
		BinaryAdder adder;
		int result;
		adder = new BinaryAdder(Integer.valueOf(val1.replaceFirst("^0+(?!$)",
				"")), Integer.valueOf(val2.replaceFirst("^0+(?!$)", "")));
		result = adder.add();
		return result;
	}

	private String computeTwosComplement(String toConvert, String firstNum) {
		String temp = "";
		BinaryAdder add;

		toConvert = lengthenToConvert(toConvert, firstNum);

		for (int i = 0; i < toConvert.length(); i++) {
			if (toConvert.charAt(i) == '0')
				temp = temp + "1";
			else
				temp = temp + "0";
		}

		toConvert = addOneToValue(temp);

		toConvert = lengthenToConvert(toConvert, firstNum);
		return toConvert;
	}

	private String addOneToValue(String temp) {
		String toConvert;
		BinaryAdder add;
		if (String.valueOf(temp).charAt(0) == '0') {

			temp = temp.replaceFirst("^0+(?!$)", "");
			if(temp.equals("")){
				add = new BinaryAdder(0, 1);
			}
			else{
				add = new BinaryAdder(Integer.valueOf(temp), 1);
			}
			
			toConvert = add.add() + "";
		} else {
			add = new BinaryAdder(Integer.valueOf(temp), 1);
			toConvert = add.add() + "";
		}
		return toConvert;
	}

	private String lengthenToConvert(String toConvert, String firstNum) {
		System.out.println(toConvert.length());
		System.out.println(firstNum.length());
		if (toConvert.length() < firstNum.length()) {
			toConvert = lengthen(toConvert,
					firstNum.length() - toConvert.length());
		}
		if (firstNum.length() < toConvert.length()) {
			firstNum = lengthen(firstNum,
					toConvert.length() - firstNum.length());
		}
		return toConvert;
	}

	private boolean determineIfResultWillBeNegative(String longerNum,
			String shorterNum) {

		if (Integer.valueOf(longerNum) == num1)
			return isFirstNumberGreater(longerNum, shorterNum);
		else
			return isFirstNumberGreater(shorterNum, longerNum);
	}

	private boolean isFirstNumberGreater(String firstNum, String secondNum) {

		int tempNum1 = 0;
		int tempNum2 = 0;

		for (int i = 0; i < firstNum.length(); i++) {

			tempNum1 = (int) ((tempNum1) + getDecimalValue(firstNum, i));
			tempNum2 = (int) ((tempNum2) + getDecimalValue(secondNum, i));

		}

		if (tempNum1 <= tempNum2)
			return true;
		else
			return false;
	}

	private double getDecimalValue(String inNum, int i) {
		return (int) (Integer.parseInt(""
				+ inNum.charAt(((inNum.length() - 1) - i))))
				* (Math.pow(2, i));
	}

	private String lengthen(String shorterNum, final int DIFF) {
		for (int i = 0; i < DIFF; i++) {
			shorterNum = "0" + shorterNum;
		}
		return shorterNum;
	}

	public static void main(String[] args) {
		BinarySubtractor s = new BinarySubtractor(110, 11111);
		System.out.println("Result: " + s.subtract());
	}
}
