package org.adligo.j2se.util.init;

import org.adligo.i.log.client.LogPlatform;
import org.adligo.j2se.util.J2SEPlatform;

public class Init {
	static {
		try {
			System.out.println("Entering static block");
			J2SEPlatform.init();
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
