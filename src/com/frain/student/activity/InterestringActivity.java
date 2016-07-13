package com.frain.student.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.frain.student.R;

public class InterestringActivity extends Activity {
	Button btn;

	protected void onCreate(Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.checkbox_layout);
		btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(clickListener);

		if (getIntent().getExtras() != null) {
			Log.i("data=====>", 
					getIntent().getExtras().getString("hello"));
		}

	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent = getIntent();
			intent.putExtra("value", "123");
			setResult(102, intent);//结果码
			finish();//必须finish才能回传参数
		}
	};

}
