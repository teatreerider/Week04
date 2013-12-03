package com.example.week04;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;

public class NetworkActivity extends Activity {
	private AQuery mAq;
	private final String URL = "http://openapi.naver.com/search?key=39f0211e72a565fcf66f5b15677733ae&query=nexearch&target=rank";
	private ProgressDialog mProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network);
		mAq = new AQuery(this);
		mProgress = new ProgressDialog(this);
		mProgress.setTitle("Wait...");
		mProgress.setMessage("잠시만 기다려 주세요...");
	}

	public void onClick(View v){
		//Log.d("yko", "Button is clicked");
		mAq.progress(mProgress).ajax(URL, XmlDom.class, new AjaxCallback<XmlDom>(){

			@Override
			public void callback(String url, XmlDom xml, AjaxStatus status) {
				mAq.id(R.id.tvDown).text(xml.toString());
			}
			
		});
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.network, menu);
		return true;
	}

}
