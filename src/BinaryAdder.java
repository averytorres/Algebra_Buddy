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
		boolean carry = false;
		int diff = longerBinaryNum.length() - shorterBinaryNum
				.length();
		
		for (int i = 0; i < diff; i++) {
			shorterBinaryNum = "0" + shorterBinaryNum;
		}

		if (Integer.valueOf(longerBinaryNum) > 0) {
			for (int i = 0; i < String.valueOf(longerBinaryNum).length(); i++) {
				if (longerBinaryNum.charAt(longerBinaryNum.length() - (i + 1)) == '0') {
					if (shorterBinaryNum.charAt(shorterBinaryNum.length()
							- (i + 1)) == '0') {
						// bother are 0
						if (!carry)
							result.insert(0, "0");
						else {
							result.insert(0, "1");
							carry = false;
						}
					} else {
						// longer is 0 shorter is 1
						if (!carry)
							result.insert(0, "1");
						else {
							result.insert(0, "0");
							carry = true;
							if (i == longerBinaryNum.length() - 1) {
								result.insert(0, "1");
							}
						}

					}
				} else {
					if (shorterBinaryNum.charAt(shorterBinaryNum.length()
							- (i + 1)) == '0') {
						// longer is 1 shorter is 0
						if (!carry)
							result.insert(0, "1");
						else {
							result.insert(0, "0");
							carry = true;
							if (i == longerBinaryNum.length() - 1) {
								result.insert(0, "1");
							}
						}
					} else {
						// both are 1
						if(carry)
							result.insert(0, "1");
						else
							result.insert(0, "0");
						
						carry = true;
						if (i == longerBinaryNum.length() - 1) {
							result.insert(0, "1");
						}
					}
				}
			}
		} else {
			result.append("0");
		}

		return Integer.valueOf(result.toString());
	}

	public static void main(String[] args) {
		// BinaryAdder b = new BinaryAdder(11100, 1010);
		BinaryAdder b = new BinaryAdder(11111111, 000);
		System.out.println(b.add());
	}

}//0111111110
