package com.example.week04;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class FreeLineActivity extends Activity {
	
	//컬렉션 생성
	private MyView mMyView;
	private ArrayList <Vertex> mList = new ArrayList <Vertex>();
	private int penSize;
	private int penColor;
	private TextView tvSize;
	private Button btnSize;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_free_line);
		
		//자바코드로 커스텀뷰 붙이기
		mMyView = new MyView(this);
		LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
		linear.addView(mMyView);
		
//		final int size = readPenSize();
		
		View actionView =View.inflate(this, R.layout.penmenu, null); //레이아웃을 view로 변환
		tvSize = (TextView) actionView.findViewById(R.id.tvSize);
		tvSize.setText(String.valueOf(penSize));
		
		btnSize = (Button) actionView.findViewById(R.id.btnSize);
		btnSize.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View view = View.inflate(FreeLineActivity.this, R.layout.sizepopup, null);
				
				final SeekBar seekbar = (SeekBar) view.findViewById(R.id.seekBar);
				final TextView tvPopSize = (TextView) view.findViewById(R.id.tvPopSize);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(FreeLineActivity.this);
				builder.setTitle("Select Pen Size")
					.setView(view)
					.setPositiveButton("확인", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							penSize = seekbar.getProgress();
							Log.d("seekbar.getProgress", Integer.toString(seekbar.getProgress()));
							tvPopSize.setText(Integer.toString(penSize));
							tvSize.setText(tvPopSize.getText());
							mMyView.setStrokeWidth(penSize);
							setProgress(penSize);
							
						}
					})
					.setNegativeButton("취소", null)
					.create().show();
				
				//SeekBar 이벤트 처리
				seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
					}
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
					}
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						//progress를 textview 에 세팅
						penSize = progress; // 인스턴스 변수 이므로 사라진다
//						savePenSize(penSize);
//						tvPopSize.setText(String.valueOf(penSize));
					}
				});
			}
		});

//		 getSystemServece() 는 Android FrameWork 의 도움을 받을때 필요하다
//		 액정화면의  크기를 가져와보자
//		 HD 760*1280
//		 fullHD 1080*1920
//		 samsung 800*1280 < 표준 안지킴
//		Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
//		int displayWidth = display.getWidth();
//		int displayHeight = display.getHeight();
		
//		mMyView.postDelayed(new Runnable() {
//			public void run() {
//				// view.getwidth() 와 view.getHeight() 가 0값으로 나오므로 1초간 지연시켜서 로그 본다
//				// 지연시킬때 러너블 인터페이스 구현방법 숙지
//				Log.d("yko", "myView width"+mMyView.getWidth());
//				Log.d("yko", "myView height"+mMyView.getHeight());
//			}
//		}, 1000);
		
		
		//2. xml로 커스텀뷰 붙이기
		// MyView에서 2개짜리 생성자와 3개 짜리 생성자 생성
		// <com.example.week04.FreeLineActivity$MyView
        //  android:layout_width="match_parent"
        //android:layout_height="match_parent">
        //</com.example.week04.FreeLineActivity>
		
		
		
		
		ActionBar mAction = getActionBar();
		mAction.setDisplayShowCustomEnabled(true);
		mAction.setCustomView(actionView);
		
		//액션바의 penSize 이벤트 처리
		
		
		//penColor 이벤트 처리 : opensource https://github.com/brk3/android-color-picker.git
		final TextView tvColor = (TextView) actionView.findViewById(R.id.tvColor);
		
		Button btnColor = (Button) findViewById(R.id.btnColor);
		btnColor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AmbilWarnaDialog mDialog = new AmbilWarnaDialog
						(FreeLineActivity.this, ((ColorDrawable)tvColor.getBackground()).getColor(), new OnAmbilWarnaListener() {
					
					@Override
					public void onOk(AmbilWarnaDialog dialog, int color) {
//						Log.d("ldk", "color:" + color);
						penColor = color;
						tvColor.setBackgroundColor(color);
					}
					
					@Override
					public void onCancel(AmbilWarnaDialog dialog) {
					}
				});
				mDialog.show();
			}
		});
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.free_line, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch(item.getItemId()) {
//		case R.id.action_settings:
//			//AlertDialog 띄우기
//			//AlertDialog mDialog = new AlertDialog(this);
//			//Builder 패턴으로 AlertDialog 만들기
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("제목")
//				.setMessage("메시지")
//				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						Toast.makeText(FreeLineActivity.this, "ok is cliced", Toast.LENGTH_SHORT).show();
//					}
//				})
//				.setNegativeButton("cancle", null)
//				.create()
//				.show();
//			break;
//		case R.id.action_share:
//			Toast.makeText(this, "share icon is clicked", Toast.LENGTH_SHORT).show();
//			break;
//		}
//		return super.onOptionsItemSelected(item);
//	}

//	private void savePenSize(int penSize){
//		// 프레퍼런스 이용하여 액티비티가 종료하더라도 저장된 값을 불러오기
//		// AndExam 25장 PrefTest.java 참고
//		SharedPreferences pref = getSharedPreferences("Practice", 0);
//		SharedPreferences.Editor edit = pref.edit();
//		
//		edit.putInt("size", penSize);
//		edit.commit(); // 반드시 커밋해야 한다
//	}
//	
//	public int readPenSize(){
//		SharedPreferences pref = getSharedPreferences("Practice", 0);
//		return pref.getInt("size", penSize);
//	
//	}
	
	class Vertex {
		int x;
		int y;
		int type; // 이벤트 종류
		int size; // 선 굵기
		int color; // 선 색깔 
		
		public Vertex(int x, int y, int type, int size, int color){
			this.x = x;
			this.y = y;
			this.type = type;
			this.size = size;
			this.color = color;
		}
		
		
	}
	
	class MyView extends View{
		
		Paint mPaint = new Paint();
		
		public MyView(Context context) {
			super(context);
			mPaint = new Paint();
			mPaint.setColor(penColor);
			mPaint.setStrokeWidth(penSize);
		}

		public void setStrokeWidth(int size) {
			mPaint.setStrokeWidth(size);
		}
		
		public void setColor(int color) {
			mPaint.setColor(color);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			 
//			canvas.drawColor(Color.WHITE);
//			Paint mPaint = new Paint();
//			mPaint.setColor(Color.BLACK);
//			mPaint.setTextSize(30);
//			canvas.drawText("hello", 100, 100, mPaint);
			
			
			for(int i =0; i<mList.size()-1 ; i++){
				mPaint.setColor(mList.get(i).color);
				mPaint.setTextSize(mList.get(i).size);
				canvas.drawLine(mList.get(i).x, mList.get(i).y, mList.get(i+1).x	, mList.get(i+1).y, mPaint);
			}
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			
//			Log.d("yko", "event action:" + event.getAction());
//			Log.d("yko", "x,y : " + event.getX() + "," + event.getY() );
			// true 를 리턴하면 상위 뷰로 이벤트를 전달하지 앟않는다.
			//false 를 리턴하면 상위 뷰로 이벤트를 전달한다.
			
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				//Vertex 인스턴스를 생성하고, mList컬렉션에 add하기
				Vertex vt = new Vertex((int)event.getX(), (int)event.getY(), MotionEvent.ACTION_DOWN, penSize, penColor);
				mList.add(vt);
				break;
			case MotionEvent.ACTION_MOVE:
				Vertex vt2= new Vertex((int)event.getX(), (int)event.getY(), MotionEvent.ACTION_MOVE, penSize, penColor);
				mList.add(vt2);
				invalidate(); // onDraw 를 호출한다!!
				break;
			case MotionEvent.ACTION_UP:
				
				break;	
			}
			
			return true;
		}
		
		
	}
	//MyView calss end --------------------------------------------------------------------------------
	
	
	
//	// 액티비티 기준으로 x,y 값을 구해본다
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		
//		Log.d("yko", "event action:" + event.getAction());
//		Log.d("yko", "x,y : " + event.getX() + "," + event.getY() );
//		// true 를 리턴하면 상위 뷰로 이벤트를 전달하지 앟않는다.
//		//false 를 리턴하면 상위 뷰로 이벤트를 전달한다.
//		return false;
//	}
	
}

















