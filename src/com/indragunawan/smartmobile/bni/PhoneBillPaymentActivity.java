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

public class PhoneBillPaymentActivity extends CommonEntryActivity {

	private String syntax;
	private int menuCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_BILL_PAYMENT_TELKOM)) {
			screenState = ScreenConstants.SCREEN_BILL_PAYMENT_TELKOM;
			menuHeaderText.setText(ScreenConstants.SCREEN_BILL_PAYMENT_TELKOM_TITLE.toUpperCase());

			syntax = getString(R.string.telkom);
			menuCode = R.string.telkom;
		}

		if (screenState.equals(ScreenConstants.SCREEN_BILL_PAYMENT_TELKOMSEL)) {
			screenState = ScreenConstants.SCREEN_BILL_PAYMENT_TELKOMSEL;
			menuHeaderText.setText(ScreenConstants.SCREEN_BILL_PAYMENT_TELKOMSEL_TITLE.toUpperCase());

			syntax = getString(R.string.telkomsel);
			menuCode = R.string.telkomsel;
		}

		entryMapper.setFirstLabel(getString(R.string.phone_no));
		entryMapper.setFirstValidation(getString(R.string.phone_no_required));

		entryMapper.setSecondLabel(getString(R.string.jumlah_pembayaran));
		entryMapper.setSecondValidation(getString(R.string.jumlah_pembayaran_required));

		entryMapper.setThirdLabel(getString(R.string.pin_smartmobile));
		entryMapper.setThirdValidation(getString(R.string.pin_smartmobile_required));

		super.initOthers();
		
		thirdEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();

		String pin = thirdEntry.getText().toString();
		String telepon = firstEntry.getText().toString();
		String jumlah = secondEntry.getText().toString();

		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);
		syntax = syntax.replace(SyntaxTemplate.TELEPON_TEMPLATE, telepon);
		syntax = syntax.replace(SyntaxTemplate.AMOUNT_TEMPLATE, jumlah);

		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this, telepon, jumlah, menuSession);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
