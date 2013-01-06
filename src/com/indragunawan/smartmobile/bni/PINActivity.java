package com.indragunawan.smartmobile.bni;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.indragunawan.smartmobile.bni.common.BaseActivity;
import com.indragunawan.smartmobile.bni.common.SimplePINHelper;
import com.indragunawan.smartmobile.bni.helper.Constants;

public class PINActivity extends BaseActivity implements OnClickListener {

	private EditText oldPin;
	private EditText newPin;
	private EditText confirmPin;

	private Button setButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_pin);

		initialize();
		resetForm();
	}

	private void resetForm() {
		oldPin.setText(Constants.EMPTY_STRING);
		newPin.setText(Constants.EMPTY_STRING);
		confirmPin.setText(Constants.EMPTY_STRING);

		oldPin.requestFocus();
	}

	@Override
	protected void initOthers() {
	}

	@Override
	protected void initialize() {
		oldPin = (EditText) findViewById(R.id.oldPinText);
		newPin = (EditText) findViewById(R.id.newPinText);
		confirmPin = (EditText) findViewById(R.id.confirmPinText);

		setButton = (Button) findViewById(R.id.setButton);
		setButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.setButton) {
			if (isSamePIN()) {
				finish();
			} else {
				SimplePINHelper sph = new SimplePINHelper(getApplicationContext());
				boolean result = sph.executeChangePIN(getBaseContext(), oldPin.getText().toString(), newPin.getText().toString(), confirmPin.getText()
						.toString());
				if (result) {
					finish();
				}
			}
		}
	}

	public boolean isSamePIN() {
		if (oldPin.getText().toString().equals(newPin.getText().toString()) && newPin.getText().toString().equals(confirmPin.getText().toString())) {
			return true;
		}
		return false;
	}
}
