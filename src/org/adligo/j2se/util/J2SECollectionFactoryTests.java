package org.adligo.j2se.util;

import java.util.ArrayList;
import java.util.HashSet;

import org.adligo.i.util.client.CollectionFactory;
import org.adligo.i.util.client.I_Collection;
import org.adligo.i.util.mocks.MockCollectionFactory;

import junit.framework.TestCase;

public class J2SECollectionFactoryTests extends TestCase {

	public void testInit() throws Exception {
		MockCollectionFactory.uninit();
		J2SECollectionFactory.init();
		
		Exception ex = null;
		try {
			J2SECollectionFactory.init();
		} catch (Exception x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.CollectionFactory has already been initalized.", 
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
