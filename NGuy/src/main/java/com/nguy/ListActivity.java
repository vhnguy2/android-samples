package com.nguy;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import com.nguy.adapters.MyListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity {

  private ListView mListView;
  private MyListViewAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);

    mListView = (ListView) findViewById(R.id.listview);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    mAdapter = new MyListViewAdapter(getBaseContext(), displayMetrics);

    mListView.setAdapter(mAdapter);
  }

  @Override
  protected void onResume() {
    super.onResume();

    List<String> data = new ArrayList<String>();
    for (int i = 0 ; i < 50 ; i++) {
      data.add(String.valueOf(Math.random() * 1000));
    }
    mAdapter.setData(data);
  }

}
