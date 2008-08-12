package org.adligo.j2se.util;

import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;
import org.adligo.i.log.client.SimpleLog;
import org.adligo.i.util.client.Event;
import org.adligo.j2se.util.init.Init;

import junit.framework.TestCase;

public class TestLogLevels extends TestCase {
	private static final Object init = Init.init();
	private static final Log infoLog = LogFactory.getLog(System.class);
	private static final Log debugLog = LogFactory.getLog(TestLogLevels.class);
	private static final Log warnLog = LogFactory.getLog(Event.class);
	
	public void testLevels() {
		assertTrue("System should be set to info",
				infoLog.isInfoEnabled());
		assertFalse("System should NOT be set to debug" + 
				((SimpleLog) infoLog).getLevel(),
				infoLog.isDebugEnabled());
		assertTrue("System should be set to warn" + 
				((SimpleLog) infoLog).getLevel(),
				infoLog.isWarnEnabled());
		assertTrue("TestLogLevels should be set to debug",
				debugLog.isDebugEnabled());
		assertTrue("TestLogLevels should be set to info",
				debugLog.isInfoEnabled());
		assertFalse("TestLogLevels should NOT be set to trace",
				debugLog.isTraceEnabled());
		
		assertTrue("Event should be set to warn",
				warnLog.isWarnEnabled());
		assertTrue("Event should be set to Error",
				warnLog.isErrorEnabled());
		assertFalse("Event should NOT be set to Info",
				warnLog.isInfoEnabled());
	}
}
