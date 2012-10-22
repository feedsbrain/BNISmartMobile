package com.indragunawan.smartmobile.bni.common;

import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import com.indragunawan.smartmobile.bni.R;
import com.indragunawan.smartmobile.bni.helper.StringUtils;

public class TransferEntryActivity extends CommonEntryActivity {

	protected TextView fourthLabel;
	protected EditText fourthEntry;

	protected TextView fifthLabel;
	protected EditText fifthEntry;

	protected TextView sixthLabel;
	protected EditText sixthEntry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_smart_transfer);

		initialize();
		initOthers();
	}

	@Override
	protected boolean validateFields() {
		boolean result = super.validateFields();
		if (result) {
			if (!StringUtils.hasValues(fourthEntry.getText().toString(), fifthEntry.getText().toString(), sixthEntry.getText().toString())) {
				if (!StringUtils.hasValue(fourthEntry.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getFourthValidation(), null);
					if (builder != null) {
						builder.show();
					}
					result = false;
					fourthEntry.requestFocus();
				} else if (!StringUtils.hasValue(fifthEntry.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getFifthValidation(), null);
					if (builder != null) {
						builder.show();
					}
					result = false;
					fifthEntry.requestFocus();
				} else if (!StringUtils.hasValue(sixthEntry.getText().toString())) {
					Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getSixthValidation(), null);
					if (builder != null) {
						builder.show();
					}
					result = false;
					sixthEntry.requestFocus();
				}
			}
		}
		return result;
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		return null;
	}

	@Override
	protected void initOthers() {
		super.initOthers();

		secondEntry.setInputType(InputType.TYPE_CLASS_TEXT);
		thirdEntry.setInputType(InputType.TYPE_CLASS_TEXT);

		if (fourthEntry != null) {
			fourthEntry.setInputType(InputType.TYPE_CLASS_TEXT);
		}
		if (fourthLabel != null) {
			fourthLabel.setText(entryMapper.getFourthLabel());
		}

		if (fifthEntry != null) {
			fifthEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		if (fifthLabel != null) {
			fifthLabel.setText(entryMapper.getFifthLabel());
		}

		if (sixthEntry != null) {
			sixthEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		if (sixthLabel != null) {
			sixthLabel.setText(entryMapper.getSixthLabel());
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

		if (findViewById(R.id.fifthEntryLabel) != null) {
			fifthLabel = (TextView) findViewById(R.id.fifthEntryLabel);
		}

		if (findViewById(R.id.fifthEntryField) != null) {
			fifthEntry = (EditText) findViewById(R.id.fifthEntryField);
		}

		if (findViewById(R.id.sixthEntryLabel) != null) {
			sixthLabel = (TextView) findViewById(R.id.sixthEntryLabel);
		}

		if (findViewById(R.id.sixthEntryField) != null) {
			sixthEntry = (EditText) findViewById(R.id.sixthEntryField);
		}
	}

}
