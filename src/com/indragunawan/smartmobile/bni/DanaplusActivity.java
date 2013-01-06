package com.indragunawan.smartmobile.bni;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import com.indragunawan.smartmobile.bni.common.CommonEntryActivity;
import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class DanaplusActivity extends CommonEntryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		screenState = ScreenConstants.SCREEN_DANA_PLUS;
		menuHeaderText.setText(ScreenConstants.SCREEN_DANA_PLUS_TITLE.toUpperCase());

		entryMapper = new EntryMapper();

		entryMapper.setFirstLabel(getString(R.string.no_rekening_tujuan));
		entryMapper.setFirstValidation(getString(R.string.rekening_value_required));

		entryMapper.setSecondLabel(getString(R.string.jumlah_transfer));
		entryMapper.setSecondValidation(getString(R.string.nominal_transfer_required));

		entryMapper.setThirdLabel(getString(R.string.pin_smartmobile));
		entryMapper.setThirdValidation(getString(R.string.pin_smartmobile_required));

		super.initOthers();
		
		thirdEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		String pin = thirdEntry.getText().toString();
		String rekening = firstEntry.getText().toString();
		String jumlah = secondEntry.getText().toString();

		String syntax = getString(R.string.danaplus);
		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);
		syntax = syntax.replace(SyntaxTemplate.REK_TEMPLATE, rekening);
		syntax = syntax.replace(SyntaxTemplate.AMOUNT_TEMPLATE, jumlah);

		Builder builder = DialogFactory.createConfirmDialog(this, R.string.danaplus, this, rekening, jumlah, menuSession);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
