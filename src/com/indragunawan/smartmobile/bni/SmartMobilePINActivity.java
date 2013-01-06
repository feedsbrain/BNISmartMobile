package com.indragunawan.smartmobile.bni;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.SMSActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.StringUtils;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class SmartMobilePINActivity extends SMSActivity {

	private EditText oldPin;
	private EditText newPin;
	private EditText confirmPin;

	private Button setButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_pin);

		initialize();
		initOthers();
	}

	public void onClick(View paramView) {
		if (paramView == setButton) {
			if (!StringUtils.hasValues(oldPin.getText().toString(), newPin.getText().toString(), confirmPin.getText().toString())) {
				if (!StringUtils.hasValue(oldPin.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.old_pin_required), null);
					if (builder != null) {
						builder.show();
					}
					oldPin.requestFocus();
				} else if (!StringUtils.hasValue(newPin.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.new_pin_required), null);
					if (builder != null) {
						builder.show();
					}
					newPin.requestFocus();
				} else if (!StringUtils.hasValue(confirmPin.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.confirm_pin_required), null);
					if (builder != null) {
						builder.show();
					}
					confirmPin.requestFocus();
				}
			} else {
				doChangeSmartmobilePIN();
				resetForm();
			}
		}
	}

	private void doChangeSmartmobilePIN() {
		String oldPinValue = oldPin.getText().toString();
		String newPinValue = newPin.getText().toString();
		String confirmPinValue = confirmPin.getText().toString();

		String syntax = getString(R.string.pin);
		syntax = syntax.replace(SyntaxTemplate.OLD_TEMPLATE, oldPinValue);
		syntax = syntax.replace(SyntaxTemplate.NEW_TEMPLATE, newPinValue);
		syntax = syntax.replace(SyntaxTemplate.CONFIRM_TEMPLATE, confirmPinValue);

		Builder builder = DialogFactory.createConfirmDialog(this, R.string.pin, this, menuSession);
		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}
	}

	@Override
	protected void initOthers() {
		if (screenState.equals(ScreenConstants.SCREEN_GANTI_PIN_SMARTMOBILE)) {
			screenState = ScreenConstants.SCREEN_GANTI_PIN_SMARTMOBILE;
			menuHeaderText.setText(ScreenConstants.SCREEN_GANTI_PIN_SMARTMOBILE_TITLE.toUpperCase());
		}
	}

	@Override
	protected void initialize() {
		super.setDefault();

		oldPin = (EditText) findViewById(R.id.oldPinText);
		newPin = (EditText) findViewById(R.id.newPinText);
		confirmPin = (EditText) findViewById(R.id.confirmPinText);

		setButton = (Button) findViewById(R.id.setButton);
		setButton.setText(getString(R.string.kirim_button));
		setButton.setOnClickListener(this);
	}

	private void resetForm() {
		oldPin.setText(Constants.EMPTY_STRING);
		newPin.setText(Constants.EMPTY_STRING);
		confirmPin.setText(Constants.EMPTY_STRING);
	}

}
