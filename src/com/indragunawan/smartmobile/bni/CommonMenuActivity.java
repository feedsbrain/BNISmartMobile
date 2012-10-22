package com.indragunawan.smartmobile.bni;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.DialogFactory;
import com.indragunawan.smartmobile.bni.common.PreferencesHelper;
import com.indragunawan.smartmobile.bni.common.SMSActivity;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.MenuItems;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;

public class CommonMenuActivity extends SMSActivity implements OnItemClickListener {

	private ListView menuListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		initialize();
		initOthers();
	}

	@Override
	protected void initOthers() {
		if (screenState == null) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_UTAMA);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_MAIN;
			menuHeaderText.setText(ScreenConstants.SCREEN_MAIN_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_INFORMASI)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_INFORMASI);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_INFORMASI;
			menuHeaderText.setText(ScreenConstants.SCREEN_INFORMASI_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_LAYANAN)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_LAYANAN);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_LAYANAN;
			menuHeaderText.setText(ScreenConstants.SCREEN_LAYANAN_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_TRANSAKSI)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_TRANSAKSI);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_TRANSAKSI;
			menuHeaderText.setText(ScreenConstants.SCREEN_TRANSAKSI_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_BILL_PAYMENT)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_BILL_PAYMENT);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_BILL_PAYMENT;
			menuHeaderText.setText(ScreenConstants.SCREEN_BILL_PAYMENT_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_RELOAD_PULSA)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_RELOAD_PULSA);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_RELOAD_PULSA;
			menuHeaderText.setText(ScreenConstants.SCREEN_RELOAD_PULSA_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_OTODEBET)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_OTODEBET);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_OTODEBET;
			menuHeaderText.setText(ScreenConstants.SCREEN_OTODEBET_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_OTODEBET_BILL)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_OTODEBET_BILL);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_OTODEBET_BILL;
			menuHeaderText.setText(ScreenConstants.SCREEN_OTODEBET_BILL_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_OTODEBET_RELOAD)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,
					MenuItems.MENU_OTODEBET_RELOAD);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_OTODEBET_RELOAD;
			menuHeaderText.setText(ScreenConstants.SCREEN_OTODEBET_RELOAD_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_LAIN_LAIN)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_LAIN_LAIN);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_LAIN_LAIN;
			menuHeaderText.setText(ScreenConstants.SCREEN_LAIN_LAIN_TITLE.toUpperCase());
		}
		if (screenState.equals(ScreenConstants.SCREEN_SETTINGS)) {
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, MenuItems.MENU_SETTINGS);
			menuListView.setAdapter(adapter);
			screenState = ScreenConstants.SCREEN_SETTINGS;
			menuHeaderText.setText(ScreenConstants.SCREEN_SETTINGS_TITLE.toUpperCase());
		}
	}

	@Override
	protected void initialize() {
		super.setDefault();

		menuListView = (ListView) findViewById(R.id.menuListView);
		menuListView.setOnItemClickListener(this);

	}

	public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
		if (screenState.equals(ScreenConstants.SCREEN_MAIN)) {
			doMainMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_SETTINGS)) {
			doSettingsMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_INFORMASI)) {
			doInformasiMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_LAYANAN)) {
			doLayananMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_TRANSAKSI)) {
			doTransaksiMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_BILL_PAYMENT)) {
			doBillPaymentMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_RELOAD_PULSA)) {
			doReloadPulsaMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_OTODEBET)) {
			doOtodebetMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_OTODEBET_BILL)) {
			doOtodebetBillMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_OTODEBET_RELOAD)) {
			doOtodebetReloadMenuSession(paramInt);
		} else if (screenState.equals(ScreenConstants.SCREEN_LAIN_LAIN)) {
			doLainLainMenuSession(paramInt);
		}
	}

	private void doLainLainMenuSession(int paramInt) {
		switch (paramInt) {
		case 0:
			Intent intent = new Intent(this, SmartMobilePINActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_GANTI_PIN_SMARTMOBILE);
			this.startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, CommonMenuActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, getString(R.string.fitur)));
			this.prepareAndSendSMS(R.string.fitur);
			break;
		case 2:
			intent = new Intent(this, RegistrasiWebActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_REGISTRASI_WEB);
			this.startActivity(intent);
			break;
		case 3:
			intent = new Intent(this, ApplicationRequestActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_APPLICATION_REQUEST);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doOtodebetReloadMenuSession(int paramInt) {
		Intent intent = new Intent(this, PhoneSmartReloadActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTRELOAD_SIMPATI);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTRELOAD_MENTARI);
			this.startActivity(intent);
			break;
		case 2:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTRELOAD_IM3);
			this.startActivity(intent);
			break;
		case 3:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTRELOAD_SMARTFREN);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}

	}

	private void doOtodebetBillMenuSession(int paramInt) {
		Intent intent = new Intent(this, PhoneSmartBillActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_TELKOM);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_TELKOMSEL);
			this.startActivity(intent);
			break;
		case 2:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_MATRIX);
			this.startActivity(intent);
			break;
		case 3:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_INDOVISION);
			this.startActivity(intent);
			break;
		case 4:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_KABELVISION);
			this.startActivity(intent);
			break;
		case 5:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_TPJ);
			this.startActivity(intent);
			break;
		case 6:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_XPLOR);
			this.startActivity(intent);
			break;
		case 7:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTBILL_SMARTFREN);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doReloadPulsaMenuSession(int paramInt) {
		Intent intent = new Intent(this, PhoneReloadActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_SIMPATI);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_AS);
			this.startActivity(intent);
			break;
		case 2:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_MENTARI);
			this.startActivity(intent);
			break;
		case 3:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_STARONE);
			this.startActivity(intent);
			break;
		case 4:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_IM3);
			this.startActivity(intent);
			break;
		case 5:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_SMARTFREN);
			this.startActivity(intent);
			break;
		case 6:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_FLEXI);
			this.startActivity(intent);
			break;
		case 7:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_XL_JEMPOL);
			this.startActivity(intent);
			break;
		case 8:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_ESIA);
			this.startActivity(intent);
			break;
		case 9:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_XL_BEBAS_REGULER);
			this.startActivity(intent);
			break;
		case 10:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_XL_BEBAS_XTRA);
			this.startActivity(intent);
			break;
		case 11:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_THREE);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doBillPaymentMenuSession(int paramInt) {
		Intent intent = new Intent(this, PhoneBillPaymentActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_BILL_PAYMENT_TELKOM);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_BILL_PAYMENT_TELKOMSEL);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doSettingsMenuSession(int paramInt) {
		Intent intent;
		switch (paramInt) {
		case 0:
			intent = new Intent(this, PINActivity.class);
			this.startActivity(intent);
			break;
		case 1:
			PreferencesHelper ph = new PreferencesHelper(getApplicationContext());
			if (Constants.ON.equals(ph.getPreferences(Constants.DEBUG))) {
				ph.setPreferences(Constants.DEBUG, Constants.OFF);
			} else {
				ph.setPreferences(Constants.DEBUG, Constants.ON);
				Builder builder = DialogFactory.createWarningDialog(this, getString(R.string.debug_mode_warning), null);
				builder.show();
			}
			Toast.makeText(getBaseContext(), getString(R.string.mode_debug_prompt) + " " + ph.getPreferences(Constants.DEBUG), Toast.LENGTH_SHORT).show();
			break;
		case 2:
			intent = new Intent(this, AboutActivity.class);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doLayananMenuSession(int paramInt) {
		switch (paramInt) {
		case 0:
			Intent intent = new Intent(this, RaiseLimitActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_KENAIKAN_LIMIT);

			this.startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, PrintBillRequestActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_CETAK_ULANG_TAGIHAN);

			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doInformasiMenuSession(int paramInt) {
		Intent intent = new Intent(this, CommonMenuActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);

		switch (paramInt) {
		case 0:
			this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, getString(R.string.saldo)));
			this.prepareAndSendSMS(R.string.saldo);
			break;
		case 1:
			this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, getString(R.string.tagihan)));
			this.prepareAndSendSMS(R.string.tagihan);
			break;
		case 2:
			this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, getString(R.string.pembayaran)));
			this.prepareAndSendSMS(R.string.pembayaran);
			break;
		case 3:
			this.getIntent().putExtra(Constants.SMS_MESSAGE, messageRenderer.generateSyntax(menuSession, getString(R.string.poin)));
			this.prepareAndSendSMS(R.string.poin);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doOtodebetMenuSession(int paramInt) {
		Intent intent = new Intent(this, CommonMenuActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_OTODEBET_BILL);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_OTODEBET_RELOAD);
			this.startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, SmartTransferActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SMARTTRANSFER_TRANSFER);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doTransaksiMenuSession(int paramInt) {
		Intent intent = new Intent(this, CommonMenuActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent = new Intent(this, DanaplusActivity.class);
			intent.putExtra(Constants.MENU_TYPE, menuSession);
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_DANA_PLUS);

			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_BILL_PAYMENT);
			this.startActivity(intent);
			break;
		case 2:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_RELOAD_PULSA);
			this.startActivity(intent);
			break;
		case 3:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_OTODEBET);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	private void doMainMenuSession(int paramInt) {
		Intent intent = new Intent(this, CommonMenuActivity.class);
		intent.putExtra(Constants.MENU_TYPE, menuSession);
		switch (paramInt) {
		case 0:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_INFORMASI);
			this.startActivity(intent);
			break;
		case 1:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_LAYANAN);
			this.startActivity(intent);
			break;
		case 2:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_TRANSAKSI);
			this.startActivity(intent);
			break;
		case 3:
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_LAIN_LAIN);
			this.startActivity(intent);
			break;
		default:
			Toast.makeText(getBaseContext(), getString(R.string.menu_error), Toast.LENGTH_SHORT).show();
		}
	}

	public void onClick(View arg0) {
	}
}
