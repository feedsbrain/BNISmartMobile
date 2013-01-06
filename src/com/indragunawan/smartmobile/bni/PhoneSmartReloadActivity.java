package com.indragunawan.smartmobile.bni;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.ExtendedEntryActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class PhoneSmartReloadActivity extends ExtendedEntryActivity {

	private String syntax;
	private int menuCode;

	private int dueDate;

	private static final int FIRST_DUE_DATE = 13;
	private static final int SECOND_DUE_DATE = 28;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_SMARTRELOAD_SIMPATI)) {
			screenState = ScreenConstants.SCREEN_SMARTRELOAD_SIMPATI;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTRELOAD_SIMPATI_TITLE.toUpperCase());

			syntax = getString(R.string.auto_simpati);
			menuCode = R.string.auto_simpati;
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTRELOAD_MENTARI)) {
			screenState = ScreenConstants.SCREEN_SMARTRELOAD_MENTARI;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTRELOAD_MENTARI_TITLE.toUpperCase());

			syntax = getString(R.string.auto_mentari);
			menuCode = R.string.auto_mentari;
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTRELOAD_IM3)) {
			screenState = ScreenConstants.SCREEN_SMARTRELOAD_IM3;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTRELOAD_IM3_TITLE.toUpperCase());

			syntax = getString(R.string.auto_im3);
			menuCode = R.string.auto_im3;
		}

		if (screenState.equals(ScreenConstants.SCREEN_SMARTRELOAD_SMARTFREN)) {
			screenState = ScreenConstants.SCREEN_SMARTRELOAD_SMARTFREN;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTRELOAD_SMARTFREN_TITLE.toUpperCase());

			syntax = getString(R.string.auto_fren);
			menuCode = R.string.auto_fren;
		}

		entryMapper.setFirstLabel(getString(R.string.phone_no));
		entryMapper.setFirstValidation(getString(R.string.phone_no_required));

		entryMapper.setSecondLabel(getString(R.string.atas_nama));
		entryMapper.setSecondValidation(getString(R.string.atas_nama_reload_required));

		entryMapper.setThirdLabel(getString(R.string.jumlah_pulsa));
		entryMapper.setThirdValidation(getString(R.string.jumlah_pulsa_required));

		entryMapper.setFourthLabel(getString(R.string.pin_smartmobile));
		entryMapper.setFourthValidation(getString(R.string.pin_smartmobile_required));

		super.initOthers();

		if (fourthEntry != null) {
			fourthEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
		}
	}

	private final void performOnClick(View paramView) {
		super.onClick(paramView);
	}

	@Override
	public void onClick(View paramView) {
		if (paramView == kirimButton) {
			if (validateFields()) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle(getString(R.string.confirmation));
				alert.setMessage(getString(R.string.reload_due_date_confirm));

				// Set an EditText view to get user input
				final RadioGroup radioGroup = new RadioGroup(this);

				final RadioButton firstButton = new RadioButton(this);
				final RadioButton secondButton = new RadioButton(this);

				final Button returnButton = kirimButton;

				firstButton.setText(String.valueOf(FIRST_DUE_DATE));
				secondButton.setText(String.valueOf(SECOND_DUE_DATE));

				radioGroup.setOrientation(RadioGroup.HORIZONTAL);
				radioGroup.addView(firstButton);
				radioGroup.addView(secondButton);
				
				firstButton.setChecked(true);

				LinearLayout layout = new LinearLayout(this);
				layout.setGravity(Gravity.CENTER);
				layout.setPadding(20, 0, 20, 5);

				layout.addView(radioGroup);
				alert.setView(layout);

				alert.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (which == AlertDialog.BUTTON_POSITIVE) {
							if (firstButton.isChecked()) {
								dueDate = FIRST_DUE_DATE;
							} else {
								dueDate = SECOND_DUE_DATE;
							}
							performOnClick(returnButton);
						}
					}
				});
				alert.show();
			}
		}
	}

	@Override
	protected Map<Object, Object> populateSMSSyntax() {
		Map<Object, Object> returnMap = new HashMap<Object, Object>();

		String nomor = firstEntry.getText().toString();
		String nama = secondEntry.getText().toString();
		String jumlah = thirdEntry.getText().toString();
		String pin = fourthEntry.getText().toString();

		String due = String.valueOf(dueDate);

		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);
		syntax = syntax.replace(SyntaxTemplate.TELEPON_TEMPLATE, nomor);
		syntax = syntax.replace(SyntaxTemplate.AMOUNT_TEMPLATE, jumlah);
		syntax = syntax.replace(SyntaxTemplate.NAME_TEMPLATE, nama);
		syntax = syntax.replace(SyntaxTemplate.DATE_TEMPLATE, due);

		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this, nomor, nama, jumlah, due, menuSession);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
