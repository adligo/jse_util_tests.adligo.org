package org.adligo.jse.util_tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util_tests.mocks.MockIteratorFactory;
import org.adligo.jse.util_tests.mock.DelegatingJSEIteratorFactory;

public class JSEIteratorFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockIteratorFactory.uninit();
		
		DelegatingJSEIteratorFactory.init();
		
		Exception ex = null;
		try {
			DelegatingJSEIteratorFactory.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("IteratorFactory was already initialized.", ex.getMessage());
		
		List<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		strings.add("3");
		I_Iterator it =  IteratorFactory.create(strings);
		
		assertTrue(it.hasNext());
		assertEquals("1", it.next());
		assertTrue(it.hasNext());
		assertEquals("2", it.next());
		assertTrue(it.hasNext());
		assertEquals("3", it.next());
		assertFalse(it.hasNext());
	}
}
