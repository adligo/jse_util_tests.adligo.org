package org.adligo.jse.util;

import java.util.ArrayList;
import java.util.List;

import org.adligo.i.util.client.I_Iterator;
import org.adligo.i.util.client.IteratorFactory;
import org.adligo.i.util.tests.mocks.MockIteratorFactory;
import org.adligo.jse.util.JSEIteratorFactory;

import junit.framework.TestCase;

public class JSEIteratorFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockIteratorFactory.uninit();
		
		JSEIteratorFactory.init();
		
		Exception ex = null;
		try {
			JSEIteratorFactory.init();
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
