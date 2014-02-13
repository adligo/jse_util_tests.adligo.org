package org.adligo.jse.util;

import java.util.ArrayList;
import java.util.HashSet;

import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Collection;
import org.adligo.i.util_tests.mocks.MockCollectionFactory;
import org.adligo.jse.util.JSECollectionFactory;

import junit.framework.TestCase;

public class JSECollectionFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockCollectionFactory.uninit();
		JSECollectionFactory.init();
		
		Exception ex = null;
		try {
			JSECollectionFactory.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.shared.CollectionFactory has already been initalized.", 
				ex.getMessage());
		
		I_Collection collection = CollectionFactory.create();
		assertNotNull(collection);
		
		collection.add("Item");
		assertEquals("[Item]", collection.toString());
		
		collection = CollectionFactory.get(null);
		assertEquals("[]", collection.toString());
		assertEquals(ArrayList.class, collection.getWrapped().getClass());
		
		collection = CollectionFactory.get(new HashSet<Object>());
		assertEquals("[]", collection.toString());
		assertEquals(HashSet.class, collection.getWrapped().getClass());
		collection.add("OtherItem");
		assertEquals("[OtherItem]", collection.toString());
	}
}
