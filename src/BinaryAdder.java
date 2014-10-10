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
		String result = "";

		if (Integer.valueOf(longerBinaryNum) > 0) {
			for (int i = 0; i < String.valueOf(longerBinaryNum).length(); i++) {
				if (longerBinaryNum.charAt(longerBinaryNum.length() - (i + 1)) == '0') {
					if (shorterBinaryNum.charAt(shorterBinaryNum.length()
							- (i + 1)) == '0') {
						System.out.println("bother are 0");
					} else {
						System.out.println("longer is 0 shorter is 1");
					}
				}
				else{
					if (shorterBinaryNum.charAt(shorterBinaryNum.length()
							- (i + 1)) == '0') {
						System.out.println("longer is 1 shorter is 0");
					} else {
						System.out.println("both are 1");
					}
				}
			}
		} else {
			result = "0";
		}

		return Integer.valueOf(result);
	}

	public static void main(String[] args) {
		BinaryAdder b = new BinaryAdder(1111100, 11111);
		b.add();
	}

}
