package com.indragunawan.smartmobile.bni;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.SMSActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.StringUtils;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class PrintBillRequestActivity extends SMSActivity {

	private Spinner monthSpinner;
	private EditText faxEditText;
	private EditText pinEditText;

	private List<String> monthValueList;

	SimpleDateFormat valueFormat = new SimpleDateFormat("MM-yyyy");
	SimpleDateFormat displayFormat = new SimpleDateFormat("MMMM yyyy");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_print_bill);

		initialize();
		initOthers();
	}

	@Override
	protected void initOthers() {
		if (screenState.equals(ScreenConstants.SCREEN_CETAK_ULANG_TAGIHAN)) {
			screenState = ScreenConstants.SCREEN_CETAK_ULANG_TAGIHAN;
			menuHeaderText.setText(ScreenConstants.SCREEN_CETAK_ULANG_TAGIHAN_TITLE.toUpperCase());
		}
		populateMonthSpinner();
	}

	private void populateMonthSpinner() {
		Calendar cal = Calendar.getInstance();
		List<String> monthList = new ArrayList<String>();
		monthValueList = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			if (i > 0) {
				cal.add(Calendar.MONTH, -1);
			}
			monthValueList.add(valueFormat.format(cal.getTime()));
			monthList.add(displayFormat.format(cal.getTime()));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthList);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monthSpinner.setAdapter(dataAdapter);
	}

	@Override
	protected void initialize() {
		super.setDefault();

		monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
		faxEditText = (EditText) findViewById(R.id.faxEditText);
		pinEditText = (EditText) findViewById(R.id.pinEditText);
	}

	public void onClick(View paramView) {
		if (paramView == kirimButton) {
			if (!StringUtils.hasValues(faxEditText.getText().toString(), pinEditText.getText().toString())) {
				if (!StringUtils.hasValue(faxEditText.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.cetak_ulang_value_warning), null);
					if (builder != null) {
						builder.show();
					}
					faxEditText.requestFocus();
				} else if (!StringUtils.hasValue(pinEditText.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.pin_smartmobile_required), null);
					if (builder != null) {
						builder.show();
					}
					pinEditText.requestFocus();
				}
			} else {
				doCetakUlangTagihanSend();
				populateMonthSpinner();
				resetForm();
			}
		}
	}

	private void doCetakUlangTagihanSend() {

		String faxNo = faxEditText.getText().toString();
		String periodeDisplay = monthSpinner.getSelectedItem().toString();
		String periodeValue = monthValueList.get(monthSpinner.getSelectedItemPosition());
		String pin = pinEditText.getText().toString();

		String syntax = getString(R.string.cetak_ulang);
		syntax = syntax.replace(SyntaxTemplate.PERIODE_TEMPLATE, periodeValue);
		syntax = syntax.replace(SyntaxTemplate.FAX_TEMPLATE, faxNo);
		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);

		Builder builder = DialogFactory.createConfirmDialog(this, R.string.cetak_ulang, this, periodeDisplay, faxNo, menuSession);
		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}

	}

	public void resetForm() {
		faxEditText.setText(Constants.EMPTY_STRING);
		pinEditText.setText(Constants.EMPTY_STRING);
		faxEditText.requestFocus();
	}

}
