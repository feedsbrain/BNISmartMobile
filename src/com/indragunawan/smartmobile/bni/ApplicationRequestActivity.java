package com.indragunawan.smartmobile.bni;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.ExtendedEntryActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class ApplicationRequestActivity extends ExtendedEntryActivity {

	private String syntax;
	private int menuCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_APPLICATION_REQUEST)) {
			screenState = ScreenConstants.SCREEN_APPLICATION_REQUEST;
			menuHeaderText.setText(ScreenConstants.SCREEN_APPLICATION_REQUEST_TITLE.toUpperCase());

			syntax = getString(R.string.aplikasi);
			menuCode = R.string.aplikasi;
		}

		entryMapper.setFirstLabel(getString(R.string.nama));
		entryMapper.setFirstValidation(getString(R.string.nama_required));

		entryMapper.setSecondLabel(getString(R.string.alamat));
		entryMapper.setSecondValidation(getString(R.string.alamat_required));

		entryMapper.setThirdValidation(getString(R.string.kota_required));
		entryMapper.setThirdLabel(getString(R.string.kota));

		entryMapper.setFourthLabel(getString(R.string.kode_pos));
		entryMapper.setFourthValidation(getString(R.string.kode_pos_required));

		super.initOthers();

		if (firstEntry != null) {
			firstEntry.setInputType(InputType.TYPE_CLASS_TEXT);
		}

		if (secondEntry != null) {
			secondEntry.setInputType(InputType.TYPE_CLASS_TEXT);
		}

		if (thirdEntry != null) {
			thirdEntry.setInputType(InputType.TYPE_CLASS_TEXT);
		}

		if (fourthEntry != null) {
			fourthEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
		}

	}

	@Override
	protected void sendTransactionSMS() {
		Map<Object, Object> syntaxMap = populateSMSSyntax();
		Builder builder = (Builder) syntaxMap.get(Constants.BUILDER);
		String syntax = (String) syntaxMap.get(Constants.SYNTAX);

		this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generatePlainSyntax(syntax));
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}

	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();

		String nama = firstEntry.getText().toString();
		String alamat = secondEntry.getText().toString();
		String kota = thirdEntry.getText().toString();
		String kode = fourthEntry.getText().toString();

		String data = nama.concat(Constants.SEPERATOR_STRING).concat(alamat).concat(Constants.SEPERATOR_STRING).concat(kota).concat(Constants.SEPERATOR_STRING)
				.concat(kode);

		syntax = syntax.replace(SyntaxTemplate.DATA_TEMPLATE, data);

		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this, nama, alamat, kota, kode);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
