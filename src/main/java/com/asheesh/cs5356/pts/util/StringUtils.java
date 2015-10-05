package com.asheesh.cs5356.pts.util;

public class StringUtils {

	public static boolean isEmptyOrNull(String data) {
		if (data == null || data.length() == 0 || data.trim().length() == 0)
			return true;

		return false;
	}
}
