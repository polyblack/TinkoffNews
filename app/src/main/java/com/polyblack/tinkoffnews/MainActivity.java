package com.polyblack.tinkoffnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.polyblack.tinkoffnews.recview.RecAdapter;
import com.polyblack.tinkoffnews.utilities.NetworkUtils;
import com.polyblack.tinkoffnews.utilities.OpenNewsJsonUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView ErrorMessageTV;
    private RecyclerView recView;
    private ProgressBar LoadingIndicator;
    private RecAdapter recAdapter;
    private SwipeRefreshLayout Swiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ErrorMessageTV = (TextView) findViewById(R.id.error_msg);
        Swiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        LoadingIndicator = (ProgressBar) findViewById(R.id.loading);
        loadNewsData();
        Swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNewsData();
            }
         });
    }

    private void loadNewsData() {
        showNewsDataView();
        new NewsQueryTask().execute();
    }

    private void showNewsDataView() {
        ErrorMessageTV.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        ErrorMessageTV.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView(List<String> newsResults) {
        recView = (RecyclerView) findViewById(R.id.news_recview);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recAdapter = new RecAdapter(newsResults);
        recView.setAdapter(recAdapter);
    }

    public class NewsQueryTask extends AsyncTask<Void, Void, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            URL newsSearchUrl = NetworkUtils.buildUrl();
            String newsSearchResults = null;

            try {
                String jsonNewsResponse = NetworkUtils
                        .getResponseFromHttpUrl(newsSearchUrl);

                List<String> simpleJsonNewsData = OpenNewsJsonUtils
                        .getSimpleNewsStringsFromJson(MainActivity.this, jsonNewsResponse);

                return simpleJsonNewsData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<String> newsResults) {
            LoadingIndicator.setVisibility(View.INVISIBLE);
            if (newsResults != null) {
                showNewsDataView();
                initRecyclerView(newsResults);
                Swiper.setRefreshing(false);
            } else {
                showErrorMessage();
            }
        }
    }

}
