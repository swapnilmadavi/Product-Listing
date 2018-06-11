package com.example.swapyx.productlisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapyx.productlisting.db.GamingMouse;
import com.example.swapyx.productlisting.ui.ImageWithTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link RecyclerView.Adapter<>} subclass that binds {@link GamingMouse} data
 * to the views in the product list.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder> {

    private List<GamingMouse> mProductList;
    private Context mContext;

    public ProductsAdapter(Context context) {
        mContext = context;
        mProductList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.card_product, parent, false);

        // Return a new holder instance
        ProductHolder viewHolder = new ProductHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        // Get the data model based on position
        GamingMouse gamingMouse = mProductList.get(position);
        holder.bind(gamingMouse);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    /**
     * Adds items to the adapter.
     *
     * @param mouseList gaming mouse list.
     */
    public void addItems(List<GamingMouse> mouseList) {
        mProductList.addAll(mouseList);
        notifyDataSetChanged();
    }

    /**
     * Clears items from the adapter.
     */
    public void clearList() {
        if(!mProductList.isEmpty()){
            mProductList.clear();
        }
    }

    /**
     * ViewHolder class for the Products(Gaming Mouse).
     */
    public static class ProductHolder extends RecyclerView.ViewHolder {
        private TextView mNameTextView;
        private ImageView mImageView;
        private TextView mUserReviewsTextView;
        private ImageWithTextView mProsView;
        private ImageWithTextView mLikesView;
        private ImageWithTextView mDislikesView;
        private ImageWithTextView mOpinionView;

        private int imageResId;

        public ProductHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.tv_product_name);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_product_image);
            mUserReviewsTextView = (TextView) itemView.findViewById(R.id.tv_product_reviews);
            mProsView = (ImageWithTextView) itemView.findViewById(R.id.ivtv_pros);
            mLikesView = (ImageWithTextView) itemView.findViewById(R.id.ivtv_likes);
            mDislikesView = (ImageWithTextView) itemView.findViewById(R.id.ivtv_dislikes);
            mOpinionView = (ImageWithTextView) itemView.findViewById(R.id.ivtv_opinion);
        }

        public void bind(GamingMouse gamingMouse) {
            mNameTextView.setText(gamingMouse.getName());

            mImageView.setImageResource(getFallbackImage(gamingMouse.getName()));

            mUserReviewsTextView.setText(gamingMouse.getNumberOfUserReviews() + " User Reviews");
            mProsView.setText(gamingMouse.getPros());
            mLikesView.setText(Integer.toString(gamingMouse.getLikes()));
            mDislikesView.setText(Integer.toString(gamingMouse.getDislikes()));

            if (gamingMouse.getOpinion() != null){
                mOpinionView.setVisibility(View.VISIBLE);
                mOpinionView.setText(gamingMouse.getOpinion());
            } else {
                mOpinionView.setVisibility(View.GONE);
            }
        }

        /**
         * Fallback method to provide image based on the name.
         * Getter for Image id on {@link GamingMouse} object is deprecated.
         * @param name name of the product
         * @return image res id
         */
        private int getFallbackImage(String name) {
            if (name.contains("G300s")){
                return R.drawable.image1;
            } else if (name.contains("XM-502")){
                return R.drawable.image2;
            } else if (name.contains("G102D")){
                return R.drawable.image3;
            } else if (name.contains("#4")){
                return R.drawable.image4;
            } else if (name.contains("Emera")){
                return R.drawable.image5;
            } else {
                return R.drawable.image1;
            }
        }
    }
}
