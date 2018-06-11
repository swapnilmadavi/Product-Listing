package com.example.swapyx.productlisting;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.Toast;

import com.example.swapyx.productlisting.db.GamingMouse;
import com.example.swapyx.productlisting.db.GamingMouseDatabase;
import com.example.swapyx.productlisting.ui.BottomButtonPanel;
import com.example.swapyx.productlisting.ui.BottomPanelBehaviour;
import com.example.swapyx.productlisting.ui.EqualSpacingItemDecoration;
import com.example.swapyx.productlisting.ui.ImageWithTextView;

import java.util.List;

/**
 * An {@link AppCompatActivity} subclass representing the Product List screen.
 */
public class ProductListingActivity extends AppCompatActivity
        implements DataRepository.RepositoryListener, BottomButtonPanel.OnClickListener {
    private Toolbar mToolbar;
    private ProgressBar mProgress;
    private RecyclerView mProductRecyclerView;
    private ProductsAdapter mProductsAdapter;
    private BottomButtonPanel mBottomPanel;
    private DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ProductListingActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_product_listing);
        mProductRecyclerView = (RecyclerView) findViewById(R.id.rv_product_list);
        mProgress = (ProgressBar) findViewById(R.id.pb_product_list);
        mBottomPanel = (BottomButtonPanel) findViewById(R.id.bbp);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mBottomPanel.setOnClickListener(this);

        // attaching bottom view behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomPanel.getLayoutParams();
        layoutParams.setBehavior(new BottomPanelBehaviour());

        setProductRecyclerView();

        mDataRepository = DataRepository.getInstance(GamingMouseDatabase.getInstance(this),
                new AppExecutors());

        if (isFirstLaunch()) {
            mDataRepository.initializeDb(this);
        } else {
            mDataRepository.getAllProducts(this);
        }
    }

    private void setProductRecyclerView() {
        // Set layout manager to position the items
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mProductRecyclerView.setLayoutManager(mLayoutManager);
        mProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mProductRecyclerView.addItemDecoration(
                new EqualSpacingItemDecoration((int) ImageWithTextView.convertDpToPixel(8),
                        EqualSpacingItemDecoration.VERTICAL));

        // Create adapter passing in the news data
        mProductsAdapter = new ProductsAdapter(this);

        // Attach the adapter to the recyclerview to populate items
        mProductRecyclerView.setAdapter(mProductsAdapter);
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

    /**
     * Defines actions to be taken after the db initialization.
     */
    @Override
    public void onDbInitialized() {
        changeFirstLaunchStatus();
        mDataRepository.getAllProducts(this);
    }

    /**
     * Defines actions to be taken after fetching the data from db.
     * @param productList list of Products
     */
    @Override
    public void onProductsFetched(final List<GamingMouse> productList) {
        hideProgress();
        showProductList();

        mProductRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mProductsAdapter.addItems(productList);
            }
        });
    }

    /**
     * Defines actions to be taken on click of Filter button.
     */
    @Override
    public void onLeftButtonClick() {
        Toast.makeText(this, "Filter clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * Defines actions to be taken on click of Sort button.
     */
    @Override
    public void onRightButtonClick() {
        Toast.makeText(this, "Sort clicked", Toast.LENGTH_SHORT).show();
    }
}
