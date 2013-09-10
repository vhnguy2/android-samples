package com.nguy;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.AbsListView;
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
    mListView.setOnScrollListener(new EndlessScrollListener());
  }

  @Override
  protected void onResume() {
    super.onResume();
    loadMoreData(100);
  }

  private void loadMoreData(int numRecords) {
    List<String> data = new ArrayList<String>();
    for (int i = 0 ; i < numRecords ; i++) {
      data.add(String.valueOf(Math.random() * 1000));
    }
    mAdapter.addData(data);
  }

  private class EndlessScrollListener implements AbsListView.OnScrollListener {
    private int mCurrentScrollState;
    private int mCurrentFirstVisibleItem;
    private int mCurrentVisibleItemCount;
    private int mCurrentTotalItemCount;

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
      mCurrentScrollState = scrollState;
      maybeLoadMoreData();
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
      mCurrentFirstVisibleItem = firstVisibleItem;
      mCurrentVisibleItemCount = visibleItemCount;
      mCurrentTotalItemCount = totalItemCount;
    }

    private void maybeLoadMoreData() {
      if (mCurrentVisibleItemCount > 0 &&
          mCurrentScrollState == SCROLL_STATE_IDLE &&
          mCurrentVisibleItemCount + mCurrentFirstVisibleItem == mCurrentTotalItemCount) {
        loadMoreData(20);
      }
    }
  }

}
