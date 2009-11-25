package org.adligo.j2se.util;

import org.adligo.i.util.client.I_Event;
import org.adligo.i.util.client.I_Listener;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.PropertyFactory;
import org.adligo.i.util.mocks.MockPropertyFactory;


import junit.framework.TestCase;

public class J2SEPropertyFactoryTests extends TestCase {
	I_Event result;
	
	public void testInit() throws Exception {
		MockPropertyFactory.uninit();
		
		J2SEPropertyFactory.init();
		
		Exception ex = null;
		try {
			J2SEPropertyFactory.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("PropertyFactory was already initialized.", ex.getMessage());
		
		
		PropertyFactory.get("/not_there.properties", new I_Listener() {
			public void onEvent(I_Event p) {
				result = p;
			}
		});
		assertNotNull(result);
		assertTrue(result.threwException());
		assertNotNull(result.getException());
		Throwable t = result.getException();
		assertEquals("Error reading property file '/not_there.properties'  " +
				"file system name 'null' file content; \n" +
				"null", t.getMessage());
		assertNotNull(result.getValue());
		I_Map map = (I_Map) result.getValue();
		assertEquals(0, map.size());
		
		result = null;
		PropertyFactory.get("/adligo_log.properties", new I_Listener() {
			public void onEvent(I_Event p) {
				result = p;
			}
		});
		
		assertNotNull(result);
		assertFalse(result.threwException());
		assertNull(result.getException());
		assertNotNull(result.getValue());
		map = (I_Map) result.getValue();
		assertEquals(3, map.size());
		
		assertEquals("INFO", map.get("defaultlog"));
		assertEquals("DEBUG", map.get("org.adligo.j2se.util.TestLogLevels"));
		assertEquals("WARN", map.get("org.adligo.i.util.client.Event"));
	}
}
