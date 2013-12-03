package com.example.week04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnFreeLine, btnNetwork, btnFreeTeacher;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnFreeLine = (Button) findViewById(R.id.btnfreeline);
		btnFreeLine.setOnClickListener(mClick);
		btnNetwork = (Button) findViewById(R.id.btnNetwork);
		btnNetwork.setOnClickListener(mClick);
		btnFreeTeacher = (Button) findViewById(R.id.btnFreeTeacher);
		btnFreeTeacher.setOnClickListener(mClick);
	}
	
	public class MyClick implements OnClickListener{

		@Override
		public void onClick(View v) {
		 
			switch(v.getId()){
			case R.id.btnfreeline:
				Intent intent01 = new Intent(MainActivity.this, FreeLineActivity.class);
				startActivity(intent01);
				break;
			case R.id.btnNetwork:
				Intent intent02 = new Intent(MainActivity.this, NetworkActivity.class);
				startActivity(intent02);
				break;	
			case R.id.btnFreeTeacher:
				Intent intent03 = new Intent(MainActivity.this, FreeLineTeacher.class);
				startActivity(intent03);
				break;		
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	MyClick mClick = new MyClick();
}
