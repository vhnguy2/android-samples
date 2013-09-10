package com.nguy.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

  private final Context mContext;
  private final DisplayMetrics mDisplayMetrics;
  private List<String> mData;

  public MyListViewAdapter(Context context, DisplayMetrics displayMetrics) {
    mContext = context;
    mDisplayMetrics = displayMetrics;
  }

  public void setData(List<String> data) {
    this.mData = data;
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return mData == null ? 0 : mData.size();
  }

  @Override
  public Object getItem(int i) {
    return mData.get(i);
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView != null) {
      ((TextView) convertView).setText((String) getItem(position));
    } else {
      TextView textView = new TextView(mContext);
      textView.setText((String) getItem(position));
      convertView = textView;
    }
    TranslateAnimation animation = new TranslateAnimation(mDisplayMetrics.widthPixels / 2, 0, 0, 0);
    animation.setDuration(300);
    convertView.startAnimation(animation);

    return convertView;
  }
}
