package org.fishlab.codegenius.test;

import java.lang.reflect.Method;

import org.fishlab.util.lang.ClassUtils;
import org.junit.Test;
class A{
	int f;
	public void setF(int f){
		this.f=f;
	}
}
class B extends A{
	int d;
	public void set(){
		
	}
	public void setD(int d){
		
	}
}

public class TestReflection {
	@Test 
	public void test(){
		
		try {
			Method m = B.class.getDeclaredMethod("setD", int.class);
			System.out.println(m);
			for(Method l:B.class.getMethods() ){
				System.out.println(l.getName());
			}
			System.out.println("-----");
			for(Method l:B.class.getDeclaredMethods() ){
				System.out.println(l.getName());
			}
			
			System.out.println("-----");
			for(Method l:ClassUtils.getAllSettersIncludeSuperClass(B.class) ){
				System.out.println(l.getName());
			}
			
			
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}
}
