package org.adligo.jse.util;

import org.adligo.i.util.client.I_ImmutableMap;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.MapFactory;
import org.adligo.i.util.tests.mocks.MockMapFactory;
import org.adligo.jse.util.JSEMapFactory;

import junit.framework.TestCase;

public class JSEMapFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockMapFactory.unInit();
		
		JSEMapFactory.init();
		
		Exception ex = null;
		try {
			JSEMapFactory.init();
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.MapFactory has already been initalized.", ex.getMessage());
		
		I_Map map = MapFactory.create();
		assertNotNull(map);
		map.put("key", "val");
		assertEquals("{key=val}", map.toString());
		
		I_ImmutableMap newMap = MapFactory.create(map);
		assertEquals("{key=val}", newMap.toString());
		
		
	}
}
