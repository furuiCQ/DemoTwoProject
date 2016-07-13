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
	// 必须重写
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

	// action category的结合使用，调用指定动作及范畴的页面
	public void openNewActivity() {

		Intent intent = new Intent();
		intent.setAction("com.frain.haha");// setAction
		intent.addCategory(Intent.CATEGORY_DEFAULT);// setCategory
		startActivity(intent);
	}

	// action的单独使用
	public void openWeb() {
		Uri uri = Uri.parse("http://www.baidu.com");// 实例化一个Uri对象
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	// ACTION_CALL
	public void callSomeBody() {
		Uri uri = Uri.parse("tel:18716320125");// 实例化一个Uri对象
		Intent intent = new Intent(Intent.ACTION_CALL, uri);
		startActivity(intent);
	}
	
	//调用拨号界面
	public void openDail(){
		Uri uri = Uri.parse("tel:18716320125");// 实例化一个Uri对象
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		startActivity(intent);
	}
	//Type  【调用的相册/联系人列表】
	public void getContent() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);// 设置Intent Action属性
		intent.setType("vnd.android.cursor.item/phone");//获取的数据的属性
		startActivity(intent); // 启动Activity
		//startActivityForResult(intent, requestCode);
	}
	//Component  打开其他app的某个页面  
	public void openOtherApp() {
		Intent i = new Intent();
		ComponentName comp = new ComponentName("com.android.browser","com.android.browser.BrowserActivity");
		//实例化一个对象 							包名						具体的某个Activity的全名
		i.setComponent(comp);
		Uri uri = Uri.parse("http://www.baidu.com");
		i.setData(uri);
		startActivity(i);
	}

}
