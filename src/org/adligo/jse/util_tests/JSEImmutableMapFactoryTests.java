package org.adligo.jse.util_tests;

import java.util.HashMap;

import org.adligo.i.util.shared.I_ImmutableMap;
import org.adligo.jse.util.JSEImmutableMapFactory;
import org.adligo.jse.util.MapWrapper;

import junit.framework.TestCase;

public class JSEImmutableMapFactoryTests extends TestCase {

	public void testCalls() throws Exception {
		JSEImmutableMapFactory factory = new JSEImmutableMapFactory();
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
		assertEquals("java.util.HashMap cannot be cast to org.adligo.i.util.shared.I_Map", ex.getMessage());
		
		map = (I_ImmutableMap) factory.createNew(new MapWrapper(inMap));
		assertEquals("{key=value}", map.toString());
	}
	
}
