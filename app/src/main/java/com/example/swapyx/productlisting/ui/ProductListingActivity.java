package com.example.swapyx.productlisting.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.swapyx.productlisting.AppExecutors;
import com.example.swapyx.productlisting.DataRepository;
import com.example.swapyx.productlisting.R;
import com.example.swapyx.productlisting.db.GamingMouse;
import com.example.swapyx.productlisting.db.GamingMouseDatabase;

import java.util.List;

public class ProductListingActivity extends AppCompatActivity implements DataRepository.RepositoryListener {

    private DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ProductListingActivity", "onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        mDataRepository = DataRepository.getInstance(GamingMouseDatabase.getInstance(this),
                new AppExecutors(), this);
    }

    @Override
    public void onDbInitialized() {
        mDataRepository.getAllProducts(this);
    }

    @Override
    public void onProductsFetched(List<GamingMouse> productList) {
        logProducts(productList);
    }

    private void logProducts(List<GamingMouse> list) {
        Log.d("ProductListingActivity", "logProducts called");
        for (GamingMouse product : list){
            Log.d("ProductListingActivity", product.toString());
        }
    }
}
