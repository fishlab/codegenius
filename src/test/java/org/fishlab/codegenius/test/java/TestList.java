package org.fishlab.codegenius.test.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {
	
	@Test
	public void ttt(){
		List<String> s=new ArrayList<String>();
		s.add("11");s.add("22");s.add("33");
		for (int i=0;i<s.size();i++){
			String str=s.get(i);
			if(str.equals("11")){
				s.remove(i);
//				i--;
			}else{
				System.out.println(str);
			}
		}
	}

}
