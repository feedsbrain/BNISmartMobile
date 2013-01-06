package com.indragunawan.smartmobile.bni;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.ExtendedEntryActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class PhoneSmartBillActivity extends ExtendedEntryActivity {

	private String syntax;
	private int menuCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_TELKOM)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_TELKOM;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_TELKOM_TITLE.toUpperCase());

			syntax = getString(R.string.bill_telkom);
			menuCode = R.string.bill_telkom;

			entryMapper.setFirstLabel(getString(R.string.phone_no));
			entryMapper.setFirstValidation(getString(R.string.phone_no_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_TELKOMSEL)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_TELKOMSEL;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_TELKOMSEL_TITLE.toUpperCase());

			syntax = getString(R.string.bill_telkomsel);
			menuCode = R.string.bill_telkomsel;

			entryMapper.setFirstLabel(getString(R.string.phone_no));
			entryMapper.setFirstValidation(getString(R.string.phone_no_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_MATRIX)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_MATRIX;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_MATRIX_TITLE.toUpperCase());

			syntax = getString(R.string.bill_matrix);
			menuCode = R.string.bill_matrix;

			entryMapper.setFirstLabel(getString(R.string.phone_no));
			entryMapper.setFirstValidation(getString(R.string.phone_no_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_INDOVISION)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_INDOVISION;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_INDOVISION_TITLE.toUpperCase());

			syntax = getString(R.string.bill_indovision);
			menuCode = R.string.bill_indovision;

			entryMapper.setFirstLabel(getString(R.string.no_pelanggan));
			entryMapper.setFirstValidation(getString(R.string.no_pelanggan_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_KABELVISION)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_KABELVISION;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_KABELVISION_TITLE.toUpperCase());

			syntax = getString(R.string.bill_kabelvision);
			menuCode = R.string.bill_kabelvision;

			entryMapper.setFirstLabel(getString(R.string.no_pelanggan));
			entryMapper.setFirstValidation(getString(R.string.no_pelanggan_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_TPJ)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_TPJ;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_TPJ_TITLE.toUpperCase());

			syntax = getString(R.string.bill_tpj);
			menuCode = R.string.bill_tpj;

			entryMapper.setFirstLabel(getString(R.string.no_pelanggan));
			entryMapper.setFirstValidation(getString(R.string.no_pelanggan_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_XPLOR)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_XPLOR;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_XPLOR_TITLE.toUpperCase());

			syntax = getString(R.string.bill_xplor);
			menuCode = R.string.bill_xplor;

			entryMapper.setFirstLabel(getString(R.string.phone_no));
			entryMapper.setFirstValidation(getString(R.string.phone_no_required));
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTBILL_SMARTFREN)) {
			screenState = ScreenConstants.SCREEN_SMARTBILL_SMARTFREN;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTBILL_SMARTFREN_TITLE.toUpperCase());

			syntax = getString(R.string.bill_fren);
			menuCode = R.string.bill_fren;

			entryMapper.setFirstLabel(getString(R.string.phone_no));
			entryMapper.setFirstValidation(getString(R.string.phone_no_required));
		}

		entryMapper.setSecondLabel(getString(R.string.atas_nama));
		entryMapper.setSecondValidation(getString(R.string.atas_nama_billing_required));

		entryMapper.setThirdLabel(getString(R.string.due_date));
		entryMapper.setThirdValidation(getString(R.string.due_date_required));

		entryMapper.setFourthLabel(getString(R.string.pin_smartmobile));
		entryMapper.setFourthValidation(getString(R.string.pin_smartmobile_required));

		super.initOthers();

		if (fourthEntry != null) {
			fourthEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}
	}

	@Override
	protected boolean validateFields() {
		boolean result = super.validateFields();
		if (result) {
			int dueDate = Integer.parseInt(thirdEntry.getText().toString());
			if ((dueDate < 1) || (dueDate > 31)) {
				Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.due_date_range_error), null);
				if (builder != null) {
					builder.show();
				}
				result = false;
				thirdEntry.requestFocus();
				thirdEntry.selectAll();
			}
		}
		return result;
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();

		String nomor = firstEntry.getText().toString();
		String nama = secondEntry.getText().toString();
		String due = thirdEntry.getText().toString();
		String pin = fourthEntry.getText().toString();

		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);
		syntax = syntax.replace(SyntaxTemplate.TELEPON_TEMPLATE, nomor);
		syntax = syntax.replace(SyntaxTemplate.DATE_TEMPLATE, due);
		syntax = syntax.replace(SyntaxTemplate.NAME_TEMPLATE, nama);

		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this, nomor, nama, due, menuSession);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
