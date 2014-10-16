package org.fishlab.codegenius.test.utils;

import java.util.Set;



import org.fishlab.codegenius.util.PackageUtil;
import org.junit.Assert;
import org.junit.Test;

public class TestPackageUtil {
	@Test
	public void case1(){
		Set<Class<?>> classes=
		PackageUtil.getClasses("org.fishlab.codegenius.testdata");
		Assert.assertTrue(classes.size()>0);
		

	}
}
