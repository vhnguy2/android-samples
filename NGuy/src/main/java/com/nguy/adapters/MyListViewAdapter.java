package com.nguy.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

  private final Context mContext;
  private final DisplayMetrics mDisplayMetrics;
  private List<String> mData;
  private int mPreviouslyAnimatedPosition = -1;

  public MyListViewAdapter(Context context, DisplayMetrics displayMetrics) {
    mData = new ArrayList<String>();
    mContext = context;
    mDisplayMetrics = displayMetrics;
  }

  public void addData(List<String> data) {
    mData.addAll(data);
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
      if (mPreviouslyAnimatedPosition < position) {
        mPreviouslyAnimatedPosition = position;
        TranslateAnimation animation = new TranslateAnimation(mDisplayMetrics.widthPixels / 2, 0, 0, 0);
        animation.setDuration(300);
        convertView.startAnimation(animation);
      }
    } else {
      TextView textView = new TextView(mContext);
      textView.setText((String) getItem(position));
      convertView = textView;
    }

    return convertView;
  }

}
