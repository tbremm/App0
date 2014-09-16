package com.example.myfirstapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*
 * We should add a class for global constants and functions.
 */


public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_search:
				openSearch();
				return true;
			case R.id.action_settings:
				openSettings();
				return true;
			case R.id.action_draw:
				doDraw();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void doDraw() {
		// Launch the Draw activity, send the string along for custom commands
		Intent intent = new Intent(this, ActivityDraw.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	/** On click handler for Plot! button **/
	public void onPlot(View view) {
		// Respond to the button click
		Intent intent = new Intent(this, PlotActivity.class);
		startActivity(intent);	
	}

	/** On click handler for Toast button **/
	public void onToast (View view)
	{
		// Respond to the button click
		doToast ();
	}
	
	private void doToast() {		
		// Get text to send to toast
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		
		// Create and send toast
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}

	private void openSettings() {
		// TODO Auto-generated method stub
		// Create a text view
		String message = "This would be settings.";
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		// Create a text view
		String message = "This would be search.";
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	/** On click handler for Send button **/
	public void sendMessage (View view)
	{
		// Respond to the button click
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
