package org.fishlab.codegenius.test.utils;


import org.fishlab.codegenius.util.PackageName;
import org.junit.Assert;
import org.junit.Test;

public class TestPackageName {
	@Test
	public void case1(){
		PackageName pn=PackageName.fromClass(this.getClass());
		Assert.assertEquals("org.fishlab.codegenius.test.utils", pn.toString());
		Assert.assertEquals("org/fishlab/codegenius/test/utils", pn.toPath());
	}
}
