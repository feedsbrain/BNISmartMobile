package com.indragunawan.smartmobile.bni.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesHelper {

	static SharedPreferences settings;

	public PreferencesHelper(Context context) {
		settings = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
	}

	public void setPreferences(String key, String value) {
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public String getPreferences(String key) {
		return settings.getString(key, "");
	}

}
