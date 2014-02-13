package org.adligo.jse.util_tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util.shared.PropertyFileReadException;
import org.adligo.i.util_tests.mocks.MockMapFactory;
import org.adligo.i.util_tests.mocks.MockPropertyFactory;
import org.adligo.jse.util.JSEPropertyFactory;
import org.adligo.jse.util_tests.mock.DelegatingJSEPropertyFactory;
import org.adligo.jse.util_tests.mock.DelegatingJSEMapFactory;

public class JSEPropertyFactoryTests extends TestCase {
	I_Event result;
	
	public void testInit() throws Exception {
		MockPropertyFactory.uninit();
		MockMapFactory.unInit();
		
		DelegatingJSEMapFactory.init();
		DelegatingJSEPropertyFactory.init();
		
		Exception ex = null;
		try {
			DelegatingJSEPropertyFactory.init();
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
		PropertyFileReadException pfre = (PropertyFileReadException) t;
		assertEquals("/not_there.properties", pfre.getFileName());
		File file = new File(".");
		String absPath = file.getAbsolutePath();
		assertNull(pfre.getAttemptedSystemFileName());
		
		
		
		assertNotNull(result.getValue());
		I_Map map = (I_Map) result.getValue();
		assertEquals(0, map.size());
		
		InputStream is = JSEPropertyFactory.class.getResourceAsStream("/adligo_log.properties");
		File systemFile = new File("./test_foo.properties");
		systemFile.createNewFile();
		OutputStream out = new FileOutputStream(systemFile);
		byte [] bytes = new byte[1];
		while (is.read(bytes) != -1) {
			out.write(bytes);
		}
		is.close();
		out.close();
		String absFilePath = systemFile.getAbsolutePath();
		System.out.println("loading from file " + absFilePath);
		
		//from file
		result = null;
		PropertyFactory.get(absFilePath, new I_Listener() {
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
		assertEquals("DEBUG", map.get("org.adligo.jse.util_tests.TestLogLevels"));
		assertEquals("WARN", map.get("org.adligo.i.util.shared.Event"));
		
		//from classpath
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
		assertEquals("DEBUG", map.get("org.adligo.jse.util_tests.TestLogLevels"));
		assertEquals("WARN", map.get("org.adligo.i.util.shared.Event"));
	}
}
