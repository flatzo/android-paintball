package com.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashScreen extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splashscreen);
		
		Button btn = (Button) this.findViewById(R.id.button1);
		btn.setOnClickListener(clickToPlay);
		
		btn = (Button) this.findViewById(R.id.button2);
		btn.setOnClickListener(clickToInstructions);
	}
	
	private OnClickListener clickToPlay = new OnClickListener() {
		public void onClick(View v) {
			
			Intent intent = new Intent().setClass(getApplicationContext(),
					HelloWorldAndroid.class);
			startActivity(intent);

		}
	};
	
	private OnClickListener clickToInstructions = new OnClickListener() {
		public void onClick(View v) {
			
			Intent intent = new Intent().setClass(getApplicationContext(),
					Instructions.class);
			startActivity(intent);

		}
	};
}
