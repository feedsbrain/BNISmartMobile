package com.indragunawan.smartmobile.bni.common;

import com.indragunawan.smartmobile.bni.R;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class BaseActivity extends Activity {
	
	protected abstract void initOthers();

	protected abstract void initialize();
	
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

}
