package com.frain.student.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;

public class MyAdapter extends BaseAdapter {
	Context context;
	int[] images;

	public MyAdapter() {
	}

	public MyAdapter(Context context, int[] images) {
		this.context = context;
		this.images = images;
	}
	public void addData(int[] images){
		this.images=images;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView=new ImageView(context);
		LayoutParams layoutParams = new LayoutParams(500,500);
		imageView.setLayoutParams(layoutParams);
		Drawable drawable=context.getResources().getDrawable(images[position]);
		drawable.setBounds(0, 0, 500, 500);
		imageView.setImageDrawable(drawable);
		return imageView;
	}

}
