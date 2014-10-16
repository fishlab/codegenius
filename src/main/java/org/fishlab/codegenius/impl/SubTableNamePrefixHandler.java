package org.fishlab.codegenius.impl;

public class SubTableNamePrefixHandler implements PrefixHandler {
	private int num;
	private char fill;
	public SubTableNamePrefixHandler(int num,char fill){
		this.num=num;
		this.fill=fill;
	}
	@Override
	public String getPrefix(String fieldName) {
		int len=fieldName.length();
		if(len<this.num){
			char[] ch=new char[this.num];
			int i;
			for(i=0;i<len;i++){
				ch[i]=fieldName.charAt(i);
			}
			for(i=len;i<num;i++){
				ch[i]=fill;
			}
			return new String(ch);
		}else{
			return fieldName.substring(0,num);
		}
	}
}
