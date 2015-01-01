package org.adligo.jse.util_tests;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_ImmutableMap;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util_tests.shared.mocks.MockMapFactory;
import org.adligo.jse.util_tests.mock.DelegatingJSEMapFactory;

public class JSEMapFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockMapFactory.unInit();
		
		DelegatingJSEMapFactory.init();
		
		Exception ex = null;
		try {
			DelegatingJSEMapFactory.init();
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.shared.MapFactory has already been initalized.", ex.getMessage());
		
		I_Map map = MapFactory.create();
		assertNotNull(map);
		map.put("key", "val");
		assertEquals("{key=val}", map.toString());
		
		I_ImmutableMap newMap = MapFactory.create(map);
		assertEquals("{key=val}", newMap.toString());
		
		
	}
}
