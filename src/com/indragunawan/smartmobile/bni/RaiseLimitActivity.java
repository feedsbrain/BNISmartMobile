package com.indragunawan.smartmobile.bni;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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

public class RaiseLimitActivity extends SMSActivity {

	private EditText jumlahEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_limit);

		initialize();
		initOthers();
	}

	@Override
	protected void initOthers() {
		if (screenState.equals(ScreenConstants.SCREEN_KENAIKAN_LIMIT)) {
			screenState = ScreenConstants.SCREEN_KENAIKAN_LIMIT;
			menuHeaderText.setText(ScreenConstants.SCREEN_KENAIKAN_LIMIT_TITLE.toUpperCase());
		}
	}

	@Override
	protected void initialize() {
		super.setDefault();
		jumlahEdit = (EditText) findViewById(R.id.jumlahEdit);
	}

	private void doKenaikanLimitSend() {
		NumberFormat nf = new DecimalFormat("#,##0");
		String limit = nf.format(Double.valueOf(jumlahEdit.getText().toString()));

		String syntax = getString(R.string.kenaikan_limit);
		syntax = syntax.replace(SyntaxTemplate.LIMIT_TEMPLATE, jumlahEdit.getText().toString());

		Builder builder = DialogFactory.createConfirmDialog(this, R.string.kenaikan_limit, this, limit, menuSession);
		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}
	}

	public void onClick(View paramView) {
		if (paramView == kirimButton) {
			if (!StringUtils.hasValue(jumlahEdit.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.limit_value_warning), null);
				if (builder != null) {
					builder.show();
				}
			} else {
				doKenaikanLimitSend();
				resetForm();
			}
		}
	}

	public void resetForm() {
		jumlahEdit.setText(Constants.EMPTY_STRING);
		jumlahEdit.requestFocus();
	}

}
