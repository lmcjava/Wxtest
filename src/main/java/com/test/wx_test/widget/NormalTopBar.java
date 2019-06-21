package com.test.wx_test.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.wx_test.R;

public class NormalTopBar extends RelativeLayout {


    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;

    public NormalTopBar(Context context) {
        super(context,null);
    }

    public NormalTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.bar_normal,this);
        imageView=findViewById(R.id.bar_back);
        textView2=findViewById(R.id.bar_title);
        textView1=findViewById(R.id.bar_action);
    }

    public void setBackVisibi(Boolean show){
        imageView.setVisibility(show ? View.VISIBLE:View.INVISIBLE);
    }

    public void setOnBackListener(OnClickListener onClickListener){
        imageView.setOnClickListener(onClickListener);
    }


    public void setActionOnBackListener(OnClickListener onClickListener){
        textView1.setOnClickListener(onClickListener);
    }


    public void setActionText(String text){
        textView1.setText(text);
    }

    public void setTitleText(String text){
        textView2.setText(text);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getActionView() {
        return textView1;
    }

    public TextView getActionTitle() {
        return textView2;
    }

    public void setActionTextVisible(Boolean visible){
        textView1.setVisibility(visible ? View.VISIBLE:View.INVISIBLE);
    }
}
