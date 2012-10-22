package com.indragunawan.smartmobile.bni;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.indragunawan.smartmobile.bni.common.SimplePINHelper;
import com.indragunawan.smartmobile.bni.helper.Constants;
import com.indragunawan.smartmobile.bni.helper.ScreenConstants;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton visaButton;
	private ImageButton masterButton;

	private static boolean pinValid = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		visaButton = (ImageButton) findViewById(R.id.visaButton);
		visaButton.setOnClickListener(this);

		masterButton = (ImageButton) findViewById(R.id.masterButton);
		masterButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.visaButton) {
			if (verifyPIN()) {
				Intent intent = new Intent(this, CommonMenuActivity.class);
				intent.putExtra(Constants.MENU_TYPE, Constants.VISA);

				this.startActivity(intent);
			}
		}
		if (v.getId() == R.id.masterButton) {
			if (verifyPIN()) {
				Intent intent = new Intent(this, CommonMenuActivity.class);
				intent.putExtra(Constants.MENU_TYPE, Constants.MASTER);

				this.startActivity(intent);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		switch (item.getItemId()) {
		case R.id.setting:
			intent = new Intent(this, CommonMenuActivity.class);
			intent.putExtra(Constants.MENU_TYPE, "");
			intent.putExtra(Constants.SCREEN_STATE, ScreenConstants.SCREEN_SETTINGS);

			this.startActivity(intent);
			break;
		case R.id.about:
			intent = new Intent(this, AboutActivity.class);
			this.startActivity(intent);
			break;
		default:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean verifyPIN() {
		final SimplePINHelper sph = new SimplePINHelper(getApplicationContext());
		if (sph.isLocked() && !isPinValid()) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle(getString(R.string.confirmation));
			alert.setMessage(getString(R.string.pin_confirm));

			// Set an EditText view to get user input
			final EditText input = new EditText(this);

			LinearLayout layout = new LinearLayout(this);
			layout.setGravity(Gravity.CENTER);
			layout.setPadding(20, 0, 20, 5);

			input.setInputType(InputType.TYPE_CLASS_NUMBER);
			input.setTransformationMethod(PasswordTransformationMethod.getInstance());
			input.setGravity(Gravity.CENTER);

			input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

			layout.addView(input);
			alert.setView(layout);

			alert.setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					if (which == AlertDialog.BUTTON_POSITIVE) {
						String value = input.getText().toString();
						if (sph.isValid(value)) {
							Toast.makeText(getBaseContext(), getString(R.string.pin_unlocked_info), Toast.LENGTH_SHORT).show();
							pinValid = true;
						} else {
							Toast.makeText(getBaseContext(), getString(R.string.pin_error), Toast.LENGTH_SHORT).show();
							pinValid = false;
						}
					}
				}
			});
			alert.show();
		} else {
			pinValid = true;
		}
		return pinValid;
	}

	public boolean isPinValid() {
		return pinValid;
	}

	@Override
	protected void onDestroy() {
		pinValid = false;
		super.onDestroy();
	}
}