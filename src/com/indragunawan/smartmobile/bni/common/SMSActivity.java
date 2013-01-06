package com.indragunawan.smartmobile.bni.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.R;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.MessageRenderer;

public abstract class SMSActivity extends Activity implements android.view.View.OnClickListener, android.content.DialogInterface.OnClickListener {

	protected final String SENT = "SMS_SENT";
	protected final String DELIVERED = "SMS_DELIVERED";

	protected String menuSession;
	protected String screenState;

	protected ImageView menuImage;
	protected TextView menuHeaderText;

	protected Button kirimButton;

	protected MessageRenderer messageRenderer = new MessageRenderer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected abstract void initOthers();

	protected abstract void initialize();

	protected void sendSMS(String phoneNumber, String message) {

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		this.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), R.string.sms_sent, Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), R.string.generic_failure, Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), R.string.no_service, Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), R.string.null_pdu, Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), R.string.radio_off, Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		this.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), R.string.sms_delivered, Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), R.string.sms_not_delivered, Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

		PreferencesHelper ph = new PreferencesHelper(getApplicationContext());
		if (Constants.ON.equals(ph.getPreferences(Constants.DEBUG))) {
			saveSMSToSentFolder(phoneNumber, message);
		}
	}

	protected void prepareAndSendSMS(Integer menuCode) {
		Builder builder = DialogFactory.createConfirmDialog(this, menuCode, this);
		if (builder != null) {
			builder.show();
		} else {
			Toast.makeText(getBaseContext(), getString(R.string.execution_error), Toast.LENGTH_SHORT);
		}
	}

	public void saveSMSToSentFolder(String phoneNumber, String message) {
		ContentValues values = new ContentValues();

		values.put(Constants.ADDRESS, phoneNumber);
		values.put(Constants.BODY, message);

		getBaseContext().getContentResolver().insert(Uri.parse("content://sms/sent"), values);
	}

	public void onClick(DialogInterface paramDialogInterface, int paramInt) {
		if (AlertDialog.BUTTON_POSITIVE == paramInt) {
			Bundle bundle = this.getIntent().getExtras();
			String message = bundle.getString(Constants.SMS_MESSAGE);

			this.sendSMS(getString(R.string.smscenter), message);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.back_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.back:
			finish();
			break;
		default:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void setDefault() {
		menuImage = (ImageView) findViewById(R.id.menuImage);
		menuHeaderText = (TextView) findViewById(R.id.menuHeaderText);

		menuSession = getIntent().getExtras().getString(Constants.MENU_TYPE);
		screenState = getIntent().getExtras().getString(Constants.SCREEN_STATE);

		if (menuSession.equals(Constants.VISA)) {
			menuImage.setImageResource(R.drawable.visa_logo);
		} else if (menuSession.equals(Constants.MASTER)) {
			menuImage.setImageResource(R.drawable.master_logo);
		} else {
			menuImage.setVisibility(View.INVISIBLE);
		}

		kirimButton = (Button) findViewById(R.id.kirimButton);

		if (kirimButton != null) {
			kirimButton.setOnClickListener(this);
		}
	}

}
