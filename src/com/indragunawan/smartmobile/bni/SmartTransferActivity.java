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
import com.indragunawan.smartmobile.bni.common.TransferEntryActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.EntryMapper;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;
import com.indragunawan.smartmobile.bni.helper.SyntaxTemplate;

public class SmartTransferActivity extends TransferEntryActivity {

	private String syntax;
	private int menuCode;

	private int dueDate;

	private static final int FIRST_DUE_DATE = 7;
	private static final int SECOND_DUE_DATE = 12;
	private static final int THIRD_DUE_DATE = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initOthers() {
		entryMapper = new EntryMapper();

		if (screenState.equals(ScreenConstants.SCREEN_SMARTTRANSFER_TRANSFER)) {
			screenState = ScreenConstants.SCREEN_SMARTTRANSFER_TRANSFER;
			menuHeaderText.setText(ScreenConstants.SCREEN_SMARTTRANSFER_TITLE.toUpperCase());

			syntax = getString(R.string.smart_transfer);
			menuCode = R.string.smart_transfer;
		}

		entryMapper.setFirstLabel(getString(R.string.no_rekening_tujuan));
		entryMapper.setFirstValidation(getString(R.string.rekening_value_required));

		entryMapper.setSecondLabel(getString(R.string.atas_nama));
		entryMapper.setSecondValidation(getString(R.string.atas_nama_smarttransfer_required));

		entryMapper.setThirdLabel(getString(R.string.nama_bank));
		entryMapper.setThirdValidation(getString(R.string.nama_bank_required));

		entryMapper.setFourthLabel(getString(R.string.nama_cabang));
		entryMapper.setFourthValidation(getString(R.string.nama_cabang_required));

		entryMapper.setFifthLabel(getString(R.string.jumlah_transfer));
		entryMapper.setFifthValidation(getString(R.string.jumlah_transfer_required));

		entryMapper.setSixthLabel(getString(R.string.pin_smartmobile));
		entryMapper.setSixthValidation(getString(R.string.pin_smartmobile_required));

		super.initOthers();

		if (sixthEntry != null) {
			sixthEntry.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
				final RadioButton thirdButton = new RadioButton(this);

				final Button returnButton = kirimButton;

				firstButton.setText(String.valueOf(FIRST_DUE_DATE));
				secondButton.setText(String.valueOf(SECOND_DUE_DATE));
				thirdButton.setText(String.valueOf(THIRD_DUE_DATE));

				radioGroup.setOrientation(RadioGroup.HORIZONTAL);
				radioGroup.addView(firstButton);
				radioGroup.addView(secondButton);
				radioGroup.addView(thirdButton);

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
							} else if (secondButton.isChecked()) {
								dueDate = SECOND_DUE_DATE;
							} else {
								dueDate = THIRD_DUE_DATE;
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
		String bank = thirdEntry.getText().toString();
		String cabang = fourthEntry.getText().toString();
		String amount = fifthEntry.getText().toString();
		String pin = sixthEntry.getText().toString();

		String due = String.valueOf(dueDate);

		syntax = syntax.replace(SyntaxTemplate.PIN_TEMPLATE, pin);
		syntax = syntax.replace(SyntaxTemplate.BANK_TEMPLATE, bank.concat(Constants.SEPERATOR_STRING).concat(cabang).concat(Constants.SEPERATOR_STRING));
		syntax = syntax.replace(SyntaxTemplate.REK_TEMPLATE, nomor);
		syntax = syntax.replace(SyntaxTemplate.NAME_TEMPLATE, nama);
		syntax = syntax.replace(SyntaxTemplate.AMOUNT_TEMPLATE, amount);
		syntax = syntax.replace(SyntaxTemplate.DATE_TEMPLATE, due);

		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this, nomor, nama, bank, cabang, amount, due, menuSession);
		returnMap.put(Constants.BUILDER, builder);
		returnMap.put(Constants.SYNTAX, syntax);
		return returnMap;
	}

}
