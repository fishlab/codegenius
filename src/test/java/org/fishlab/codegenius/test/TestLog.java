package org.fishlab.codegenius.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	private Logger logger=LoggerFactory.getLogger(TestLog.class);
	
	@Test
	public void test(){
		logger.info("a log message");
		logger.info("{} log" ,"test");
	}

}
