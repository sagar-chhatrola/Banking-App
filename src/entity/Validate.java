package entity;

public class Validate {

	public static boolean checkNumber(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}
