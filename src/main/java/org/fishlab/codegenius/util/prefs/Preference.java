package org.fishlab.codegenius.util.prefs;

import java.util.List;

/**
 * @author Black Lotus 用来保存简单信息
 * */
public interface Preference {
	public static class Entry {
		private String name;
		private String value;

		public Entry(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
	

	String getString(String name);

	int getInt(String name);

	long getLong(String name);

	boolean isTrue(String name);

	float getFloat(String name);

	void setString (String name,String value);
	void setInt(String name, int value);

	void setLong(String name, long value);

	void setBoolean(String name, boolean value);

	void setFloat(String name, float value);

	void emtpty();
	List<Preference.Entry> entryList();
}
