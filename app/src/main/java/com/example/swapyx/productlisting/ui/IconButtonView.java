package com.example.swapyx.productlisting.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.swapyx.productlisting.R;

/**
 * Custom view representing a Button with icon and text, center aligned.
 */
public class IconButtonView extends LinearLayout {
    private TextView mTextView;
    private ImageView mImageView;
    private String text;
    private float textSizeInPixels;
    private int textStyle;
    private int textColor;
    private Drawable imageDrawable;

    public IconButtonView(Context context) {
        this(context,null);
    }

    public IconButtonView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttributes(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IconButtonView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        obtainAttributes(context, attrs);
        init();
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        // attrs contains the raw values for the XML attributes
        // that were specified in the layout, which don't include
        // attributes set by styles or themes, and which may have
        // unresolved references. Call obtainStyledAttributes()
        // to get the final values for each attribute.
        //
        // This call uses R.styleable.PeerScaleView, which is an array of
        // the custom attributes that were declared in attrs.xml.
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.IconButtonView, 0, 0);

        try {
            text = a.getString(R.styleable.IconButtonView_ic_btn_text);
            textSizeInPixels = a.getDimension(R.styleable.IconButtonView_ic_btn_textSize,
                    ImageWithTextView.convertDpToPixel(14));
            textStyle = a.getInt(R.styleable.IconButtonView_ic_btn_textStyle, 0);
            textColor = a.getColor(R.styleable.IconButtonView_ic_btn_textColor,
                    Color.parseColor("#000000"));
            imageDrawable = a.getDrawable(R.styleable.IconButtonView_ic_btn_imageSrc);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }
    }


    /**
     * Initialize view
     */
    private void init() {
        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a CardView will be added to this CardView ending up
        //with two view groups

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_icon_button, this, true);

        mTextView = view.findViewById(R.id.tv_icon_button_label);
        mImageView = view.findViewById(R.id.iv_icon_button_icon);

        setTextProperties();
        setImageProperties();
    }

    private void setTextProperties() {
        mTextView.setText(text);
        mTextView.setTextSize(ImageWithTextView.convertPixelsToDp(textSizeInPixels));
        mTextView.setTextColor(textColor);
        mTextView.setTypeface(mTextView.getTypeface(), textStyle);
    }

    private void setImageProperties() {
        LayoutParams layoutParams = new LayoutParams((int) textSizeInPixels + 1 , (int) textSizeInPixels + 1);
        mImageView.setLayoutParams(layoutParams);
        mImageView.setImageDrawable(imageDrawable);
    }

    public void setText(String text) {
        this.text = text;
        mTextView.setText(text);
    }
}
