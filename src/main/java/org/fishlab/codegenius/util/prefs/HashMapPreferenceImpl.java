package org.fishlab.codegenius.util.prefs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HashMapPreferenceImpl extends HashMap<String,String> implements Preference{
	private static final long serialVersionUID = 1L;
	public HashMapPreferenceImpl (){
	}
	
	@Override
	public int getInt(String name){
		String s=this.get(name);
		int i=0;
		try{
			i=Integer.parseInt(s);
		}catch(Exception e){}
		return i;
	}
	@Override
	public long getLong(String name){
		String s=this.get(name);
		long i=0L;
		try{
			i=Long.parseLong(s);
		}catch(Exception e){}
		return i;
	}
	
	@Override
	public boolean isTrue(String name){
		String s=this.get(name);
		if(s!=null){
			return s.equals("true");
		}			
		return false;
	}
	
	@Override
	public float getFloat(String name){
		String s=this.get(name);
		if(s!=null){
			return Float.parseFloat(s);
		}			
		return 0f;
	}
	
	@Override
	public void setInt(String name, int value) {
		this.put(name, String.valueOf(value));
	}

	@Override
	public void setLong(String name, long value) {
		this.put(name, String.valueOf(value));
	}

	@Override
	public void setBoolean(String name, boolean value) {
		this.put(name, String.valueOf(value));
	}

	@Override
	public void setFloat(String name, float value) {
		this.put(name, String.valueOf(value));
	}

	@Override
	public List<Preference.Entry> entryList() {
		List<Preference.Entry> entries=new LinkedList<Preference.Entry>();
		for (String entryName:this.keySet()){
			entries.add(new Preference.Entry(entryName,this.get(entryName)));
		}
		return entries;
	}

	@Override
	public String getString(String name) {
		return this.get(name);
	}

	@Override
	public void setString(String name, String value) {
		this.put(name, value);
	}

	@Override
	public void emtpty() {
		super.clear();
	}

	


}
