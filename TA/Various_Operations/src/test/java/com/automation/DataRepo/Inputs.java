package com.automation.DataRepo;

public enum Inputs {

	URL("chrome-extension://jjdemeiffadmmjhkbbpglgnlgeafomjo/assets/pages/app.html"),
	USER_ID("albert+cypress@contactout.io"),
	PWD("testpassword");

	private String str;

	Inputs(String string) {
		// TODO Auto-generated constructor stub
		this.str = string;
	}

	public String getData() {
		return this.str;
	} 

}
