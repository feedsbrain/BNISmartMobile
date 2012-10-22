package com.indragunawan.smartmobile.bni.helper;

public class StringUtils {

	public static boolean hasValue(String value) {
		if ((null == value) || (value.trim().equals(Constants.EMPTY_STRING) || value.length() <= 0)) {
			return false;
		}
		return true;
	}

	public static boolean hasValues(String... args) {
		boolean result = true;
		for (int i = 0; i < args.length; i++) {
			if (!hasValue(args[i])) {
				result = false;
			}
		}
		return result;
	}

}
