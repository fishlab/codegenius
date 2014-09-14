package org.fishlab.codegenius.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	private Logger logger=LoggerFactory.getLogger(TestLog.class);
	
	@Test
	public void test(){
		logger.info("a format");
		logger.info("a format22:01:56.081[main]INFO [o.f.codegenius.test.TestLog   ] a format"
				+"22:01:56.081[main]INFO [o.f.codegenius.test.TestLog   ] a format");
	}

}
