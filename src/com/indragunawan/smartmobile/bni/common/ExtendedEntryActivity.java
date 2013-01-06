package com.indragunawan.smartmobile.bni.common;

import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.indragunawan.smartmobile.bni.R;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.StringUtils;

public class ExtendedEntryActivity extends CommonEntryActivity {

	protected TextView fourthLabel;
	protected EditText fourthEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_extended_entry);

		initialize();
		initOthers();
	}

	@Override
	protected boolean validateFields() {
		boolean result = super.validateFields();
		if (result) {
			if (!StringUtils.hasValue(fourthEntry.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getFourthValidation(), null);
				if (builder != null) {
					builder.show();
				}
				result = false;
				fourthEntry.requestFocus();
			}
		}
		return result;
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		// Override this method on child
		return null;
	}

	@Override
	protected void initOthers() {
		super.initOthers();
		
		secondEntry.setInputType(InputType.TYPE_CLASS_TEXT);

		if (fourthEntry != null) {
			fourthEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		if (fourthLabel != null) {
			fourthLabel.setText(entryMapper.getFourthLabel());
		}
	}

	@Override
	protected void initialize() {
		super.initialize();

		if (findViewById(R.id.fourthEntryLabel) != null) {
			fourthLabel = (TextView) findViewById(R.id.fourthEntryLabel);
		}

		if (findViewById(R.id.fourthEntryField) != null) {
			fourthEntry = (EditText) findViewById(R.id.fourthEntryField);
		}
	}

	@Override
	public void resetForm() {
		super.resetForm();

		fourthEntry.setText(Constants.EMPTY_STRING);
		firstEntry.requestFocus();
	}

}
