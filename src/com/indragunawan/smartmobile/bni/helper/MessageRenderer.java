package com.indragunawan.smartmobile.bni.helper;

public class MessageRenderer {

	private static final String WHITESPACE = " ";

	public String generateSyntax(String menuSession, String syntax) {
		return menuSession.concat(WHITESPACE).concat(syntax);
	}

	public String generatePlainSyntax(String syntax) {
		return syntax.trim();
	}

}
