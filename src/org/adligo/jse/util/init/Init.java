package org.adligo.jse.util.init;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.jse.util.JSEPlatform;

public class Init {
	static {
		try {
			System.out.println("Entering static block");
			JSEPlatform.init();
			LogPlatform.init();
			System.out.println("static block sucess");
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public static final Object init() {
		return new Object();
	}
}