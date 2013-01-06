package com.indragunawan.smartmobile.bni;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.SMSActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.StringUtils;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class RegistrasiWebActivity extends SMSActivity {

	private EditText pinEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_registrasi);

		initialize();
		initOthers();
	}

	@Override
	protected void initOthers() {
		if (screenState.equals(ScreenConstants.SCREEN_REGISTRASI_WEB)) {
			screenState = ScreenConstants.SCREEN_REGISTRASI_WEB;
			menuHeaderText.setText(ScreenConstants.SCREEN_REGISTRASI_WEB_TITLE.toUpperCase());
		}
	}

	@Override
	protected void initialize() {
		super.setDefault();
		pinEntry = (EditText) findViewById(R.id.pinFieldEntry);
	}

	private void doRegistrasiWebSend() {
		String syntax = getString(R.string.register);
		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pinEntry.getText().toString());

		Builder builder = DialogFactory.createConfirmDialog(this, R.string.register, this);
		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generatePlainSyntax(syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}
	}

	public void onClick(View paramView) {
		if (paramView == kirimButton) {
			if (!StringUtils.hasValue(pinEntry.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.pin_smartmobile_required), null);
				if (builder != null) {
					builder.show();
				}
				pinEntry.requestFocus();
			} else {
				doRegistrasiWebSend();
				resetForm();
			}
		}
	}

	public void resetForm() {
		pinEntry.setText(Constants.EMPTY_STRING);
		pinEntry.requestFocus();
	}

}
