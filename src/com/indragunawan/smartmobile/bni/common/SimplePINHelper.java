package com.indragunawan.smartmobile.bni.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.R;

public class SimplePINHelper {

	static SharedPreferences settings;

	public SimplePINHelper(Context context) {
		settings = context.getSharedPreferences("SECURITY", Context.MODE_PRIVATE);
	}

	public static void savePIN(String pin) {
		Editor editor = settings.edit();
		editor.putString("PIN", pin);
		editor.commit();
	}

	public boolean isValid(String pin) {
		if (!isLocked() || (getPIN().equals(pin))) {
			return true;
		}
		return false;
	}

	public boolean isLocked() {
		String pin = getPIN();
		if (pin.length() > 0) {
			return true;
		}
		return false;
	}

	private String getPIN() {
		return settings.getString("PIN", "");
	}

	public boolean executeChangePIN(Context context, String oldPIN, String newPIN, String confirmPIN) {
		boolean result = validatePIN(context, oldPIN, newPIN, confirmPIN);

		savePIN(newPIN);
		Toast.makeText(context, context.getString(R.string.change_pin_success), Toast.LENGTH_SHORT).show();
		return result;
	}

	public boolean validatePIN(Context context, String oldPIN, String newPIN, String confirmPIN) {
		boolean result = true;
		if (!isValid(oldPIN)) {
			Toast.makeText(context, context.getString(R.string.old_pin_error), Toast.LENGTH_SHORT).show();
			result = false;
		}
		if (!newPIN.equals(confirmPIN)) {
			Toast.makeText(context, context.getString(R.string.confirm_pin_error), Toast.LENGTH_SHORT).show();
			result = false;
		}
		if ((newPIN.length() > 0) && ((newPIN.length() < 4) || (confirmPIN.length() < 4))) {
			Toast.makeText(context, context.getString(R.string.pin_length_error), Toast.LENGTH_SHORT).show();
			result = false;
		}
		return result;
	}
}
