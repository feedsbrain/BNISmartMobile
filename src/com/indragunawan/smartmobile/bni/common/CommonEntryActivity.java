package com.indragunawan.smartmobile.bni.common;

import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.R;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.StringUtils;

public class CommonEntryActivity extends SMSActivity {

	protected TextView firstLabel;
	protected TextView secondLabel;
	protected TextView thirdLabel;

	protected EditText firstEntry;
	protected EditText secondEntry;
	protected EditText thirdEntry;

	protected EntryMapper entryMapper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_common_entry);

		initialize();
		initOthers();
	}

	public void onClick(View paramView) {
		if (paramView == kirimButton) {
			if (validateFields()) {
				sendTransactionSMS();
				resetForm();
			}
		}
	}

	protected boolean validateFields() {
		boolean result = true;
		if (!StringUtils.hasValues(firstEntry.getText().toString(), secondEntry.getText().toString(), thirdEntry.getText().toString())) {
			if (!StringUtils.hasValue(firstEntry.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getFirstValidation(), null);
				if (builder != null) {
					builder.show();
				}
				result = false;
				firstEntry.requestFocus();
			} else if (!StringUtils.hasValue(secondEntry.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getSecondValidation(), null);
				if (builder != null) {
					builder.show();
				}
				result = false;
				secondEntry.requestFocus();
			} else if (!StringUtils.hasValue(thirdEntry.getText().toString())) {
				Builder builder = DialogFactory.createWarningDialog(this, entryMapper.getThirdValidation(), null);
				if (builder != null) {
					builder.show();
				}
				result = false;
				thirdEntry.requestFocus();
			}
		}
		return result;
	}

	protected void sendTransactionSMS() {

		Map<Object, Object> syntaxMap = populateSMSSyntax();
		Builder builder = (Builder) syntaxMap.get(Constants.BUILDER);
		String syntax = (String) syntaxMap.get(Constants.SYNTAX);

		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}

	}

	protected Map<Object, Object> populateSMSSyntax() {
		// Override this method on child
		return null;
	}

	@Override
	protected void initOthers() {
		firstEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		secondEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		thirdEntry.setInputType(InputType.TYPE_CLASS_NUMBER);

		firstLabel.setText(entryMapper.getFirstLabel());
		secondLabel.setText(entryMapper.getSecondLabel());
		thirdLabel.setText(entryMapper.getThirdLabel());
	}

	@Override
	protected void initialize() {
		super.setDefault();

		firstLabel = (TextView) findViewById(R.id.firstEntryLabel);
		secondLabel = (TextView) findViewById(R.id.secordEntryLabel);
		thirdLabel = (TextView) findViewById(R.id.thridEntryLabel);

		firstEntry = (EditText) findViewById(R.id.firstEntryField);
		secondEntry = (EditText) findViewById(R.id.secondEntryField);
		thirdEntry = (EditText) findViewById(R.id.thirdEntryField);
	}

	public void resetForm() {
		firstEntry.setText(Constants.EMPTY_STRING);
		secondEntry.setText(Constants.EMPTY_STRING);
		thirdEntry.setText(Constants.EMPTY_STRING);

		firstEntry.requestFocus();
	}

}
