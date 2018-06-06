package com.example.swapyx.productlisting.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.swapyx.productlisting.AppExecutors;
import com.example.swapyx.productlisting.DataRepository;
import com.example.swapyx.productlisting.R;
import com.example.swapyx.productlisting.db.GamingMouse;
import com.example.swapyx.productlisting.db.GamingMouseDatabase;

import java.util.List;

public class ProductListingActivity extends AppCompatActivity implements DataRepository.RepositoryListener {
    private Toolbar mToolbar;
    private ProgressBar mProgress;
    private RecyclerView mProductRecyclerView;
    private ProductsAdapter mProductsAdapter;
    private DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ProductListingActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_product_listing);
        mProductRecyclerView = (RecyclerView) findViewById(R.id.rv_product_list);
        mProgress = (ProgressBar) findViewById(R.id.pb_product_list);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gaming Mouses");

        // Set layout manager to position the items
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mProductRecyclerView.setLayoutManager(mLayoutManager);
        mProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mProductRecyclerView.addItemDecoration(
                new EqualSpacingItemDecoration((int)ImageWithTextView.convertDpToPixel(8),
                        EqualSpacingItemDecoration.VERTICAL));

        // Create adapter passing in the news data
        mProductsAdapter = new ProductsAdapter(this);

        // Attach the adapter to the recyclerview to populate items
        mProductRecyclerView.setAdapter(mProductsAdapter);

        mDataRepository = DataRepository.getInstance(GamingMouseDatabase.getInstance(this),
                new AppExecutors());

        if (isFirstLaunch()) {
            Log.d("ProductListingActivity", "first launch!!");
            mDataRepository.initializeDb(this);
        } else {
            Log.d("ProductListingActivity", "Regular launch!!");
            mDataRepository.getAllProducts(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDbInitialized() {
        changeFirstLaunchStatus();
        mDataRepository.getAllProducts(this);
    }

    @Override
    public void onProductsFetched(List<GamingMouse> productList) {
        hideProgress();
        showProductList();
        logProducts(productList);
    }

    private void hideProductList() {
        mProductRecyclerView.setVisibility(View.GONE);
    }

    private void showProductList() {
        mProductRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    private void logProducts(final List<GamingMouse> list) {
        Log.d("ProductListingActivity", "logProducts called");
        for (GamingMouse product : list){
            Log.d("ProductListingActivity", product.toString());
        }

        mProductRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mProductsAdapter.addItems(list);
            }
        });
    }

    private boolean isFirstLaunch() {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        return preferences.getBoolean("KEY_FIRST_LAUNCH", true);
    }

    private void changeFirstLaunchStatus() {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putBoolean("KEY_FIRST_LAUNCH", false).apply();
    }
}
