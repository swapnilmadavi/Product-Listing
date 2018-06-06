package com.example.swapyx.productlisting.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.swapyx.productlisting.R;

public class BottomButtonPanel extends ConstraintLayout {
    private IconButtonView mIconButtonLeft;
    private IconButtonView mIconButtonRight;

    private OnClickListener listener;


    public BottomButtonPanel(Context context) {
        this(context,null);
    }

    public BottomButtonPanel(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomButtonPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * Initialize view
     */
    private void initView() {
        //Inflate xml resource, pass "this" as the parent, we use <merge> tag in xml to avoid
        //redundant parent, otherwise a CardView will be added to this CardView ending up
        //with two view groups

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_bottom_buttons, this, true);

        mIconButtonLeft = view.findViewById(R.id.ic_btn_filter);
        mIconButtonRight = view.findViewById(R.id.ic_btn_sort);

        mIconButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onLeftButtonClick();
                }
            }
        });

        mIconButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onRightButtonClick();
                }
            }
        });
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public interface OnClickListener{
        void onLeftButtonClick();
        void onRightButtonClick();
    }
}
