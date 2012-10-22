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

public class PhoneReloadActivity extends CommonEntryActivity {

	private String syntax;
	private int menuCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_SIMPATI)) {
			screenState = ScreenConstants.SCREEN_RELOAD_SIMPATI;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_SIMPATI_TITLE.toUpperCase());

			syntax = getString(R.string.simpati);
			menuCode = R.string.simpati;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_AS)) {
			screenState = ScreenConstants.SCREEN_RELOAD_AS;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_AS_TITLE.toUpperCase());

			syntax = getString(R.string.as);
			menuCode = R.string.as;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_MENTARI)) {
			screenState = ScreenConstants.SCREEN_RELOAD_MENTARI;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_MENTARI_TITLE.toUpperCase());

			syntax = getString(R.string.mentari);
			menuCode = R.string.mentari;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_STARONE)) {
			screenState = ScreenConstants.SCREEN_RELOAD_STARONE;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_STARONE_TITLE.toUpperCase());

			syntax = getString(R.string.starone);
			menuCode = R.string.starone;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_IM3)) {
			screenState = ScreenConstants.SCREEN_RELOAD_IM3;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_IM3_TITLE.toUpperCase());

			syntax = getString(R.string.im3);
			menuCode = R.string.im3;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_SMARTFREN)) {
			screenState = ScreenConstants.SCREEN_RELOAD_SMARTFREN;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_SMARTFREN_TITLE.toUpperCase());

			syntax = getString(R.string.fren);
			menuCode = R.string.fren;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_FLEXI)) {
			screenState = ScreenConstants.SCREEN_RELOAD_FLEXI;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_FLEXI_TITLE.toUpperCase());

			syntax = getString(R.string.flexi);
			menuCode = R.string.flexi;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_XL_JEMPOL)) {
			screenState = ScreenConstants.SCREEN_RELOAD_XL_JEMPOL;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_XL_JEMPOL_TITLE.toUpperCase());

			syntax = getString(R.string.xl_jempol);
			menuCode = R.string.xl_jempol;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_ESIA)) {
			screenState = ScreenConstants.SCREEN_RELOAD_ESIA;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_ESIA_TITLE.toUpperCase());

			syntax = getString(R.string.esia);
			menuCode = R.string.esia;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_XL_BEBAS_REGULER)) {
			screenState = ScreenConstants.SCREEN_RELOAD_XL_BEBAS_REGULER;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_XL_BEBAS_REGULER_TITLE.toUpperCase());

			syntax = getString(R.string.xl_reg);
			menuCode = R.string.xl_reg;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_XL_BEBAS_XTRA)) {
			screenState = ScreenConstants.SCREEN_RELOAD_XL_BEBAS_XTRA;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_XL_BEBAS_XTRA_TITLE.toUpperCase());

			syntax = getString(R.string.xl_xtra);
			menuCode = R.string.xl_xtra;
		}

		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_THREE)) {
			screenState = ScreenConstants.SCREEN_RELOAD_THREE;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_THREE_TITLE.toUpperCase());

			syntax = getString(R.string.simpati);
			menuCode = R.string.simpati;
		}

		entryMapper.setFirstLabel(getString(R.string.phone_no));
		entryMapper.setFirstValidation(getString(R.string.phone_no_required));

		entryMapper.setSecondLabel(getString(R.string.jumlah_pulsa));
		entryMapper.setSecondValidation(getString(R.string.jumlah_pulsa_required));

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
