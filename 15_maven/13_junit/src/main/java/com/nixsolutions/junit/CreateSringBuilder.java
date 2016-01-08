package com.nixsolutions.junit;

public class CreateSringBuilder {
	private StringBuilder build;

	public CreateSringBuilder(String str) {
		build = new StringBuilder(str);
	}

	public StringBuilder getBuild() {
		return build;
	}

	public String appender(String str) {

		build.append(str);
		return build.toString();
	}

}
