package com.frain.student.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.frain.student.R;

public class MainActivity extends Activity {
	Button showBtn;
	Button showBtn2;
	Button openWebBtn;
	Button callBtn;

	@Override
	// ������д
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Log.i("onCreate", "onCreate");
		setContentView(R.layout.activity_main);
		showBtn = (Button) findViewById(R.id.show_btn);
		showBtn2 = (Button) findViewById(R.id.show_btn2);
		openWebBtn = (Button) findViewById(R.id.open_web_btn);
		callBtn = (Button) findViewById(R.id.call_btn);
		showBtn.setOnClickListener(onClickListener);
		showBtn2.setOnClickListener(onClickListener);
		openWebBtn.setOnClickListener(onClickListener);
		callBtn.setOnClickListener(onClickListener);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.show_btn:
				openNewActivity();
				break;
			case R.id.show_btn2:
				demoIntent();
				break;
			case R.id.open_web_btn:
				openWeb();
				break;
			case R.id.call_btn:
				openOtherApp();
				break;
			default:
				break;
			}
		}
	};

	public void demoIntent() {
		Intent intent = new Intent(MainActivity.this, MainTwoActivity.class);
		startActivity(intent);
	}

	// action category�Ľ��ʹ�ã�����ָ�������������ҳ��
	public void openNewActivity() {

		Intent intent = new Intent();
		intent.setAction("com.frain.haha");// setAction
		intent.addCategory(Intent.CATEGORY_DEFAULT);// setCategory
		startActivity(intent);
	}

	// action�ĵ���ʹ��
	public void openWeb() {
		Uri uri = Uri.parse("http://www.baidu.com");// ʵ����һ��Uri����
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	// ACTION_CALL
	public void callSomeBody() {
		Uri uri = Uri.parse("tel:18716320125");// ʵ����һ��Uri����
		Intent intent = new Intent(Intent.ACTION_CALL, uri);
		startActivity(intent);
	}
	
	//���ò��Ž���
	public void openDail(){
		Uri uri = Uri.parse("tel:18716320125");// ʵ����һ��Uri����
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(intent);
	}
	//Type  �����õ����/��ϵ���б�
	public void getContent() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);// ����Intent Action����
		intent.setType("vnd.android.cursor.item/phone");//��ȡ�����ݵ�����
		startActivity(intent); // ����Activity
		//startActivityForResult(intent, requestCode);
	}
	//Component  ������app��ĳ��ҳ��  
	public void openOtherApp() {
		Intent i = new Intent();
		ComponentName comp = new ComponentName("com.android.browser","com.android.browser.BrowserActivity");
		//ʵ����һ������ 							����						�����ĳ��Activity��ȫ��
		i.setComponent(comp);
		Uri uri = Uri.parse("http://www.baidu.com");
		i.setData(uri);
		startActivity(i);
	}

}
