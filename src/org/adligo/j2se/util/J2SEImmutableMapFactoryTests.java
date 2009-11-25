package org.adligo.j2se.util;

import java.util.HashMap;

import org.adligo.i.util.client.I_ImmutableMap;
import junit.framework.TestCase;

public class J2SEImmutableMapFactoryTests extends TestCase {

	public void testCalls() throws Exception {
		J2SEImmutableMapFactory factory = new J2SEImmutableMapFactory();
		I_ImmutableMap map = (I_ImmutableMap) factory.createNew(null);
		assertNotNull(map);
		assertEquals("{}", map.toString());
		
		HashMap<String, String> inMap = new HashMap<String, String>();
		inMap.put("key", "value");
		
		Exception ex = null;
		try {
			map = (I_ImmutableMap) factory.createNew(inMap);
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("java.util.HashMap cannot be cast to org.adligo.i.util.client.I_Map", ex.getMessage());
		
		map = (I_ImmutableMap) factory.createNew(new MapWrapper(inMap));
		assertEquals("{key=value}", map.toString());
	}
	
}
