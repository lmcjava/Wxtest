package com.test.wx_test.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.wx_test.R;

public class TabIndicator  extends RelativeLayout {


    private TextView tab_indicator_hint;
    private TextView tab_indicator_unread;
    private ImageView tab_indicator_icon;

    private int normalIconId;
    private int focusIconId;

    public TabIndicator(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.tab_indicator, this);
        tab_indicator_icon=view.findViewById(R.id.tab_indicator_icon);
        tab_indicator_hint=view.findViewById(R.id.tab_indicator_hint);
        tab_indicator_unread=view.findViewById(R.id.tab_indicator_unread);
    }

    public void setTabTitle(String title){
        tab_indicator_hint.setText(title);
    }

    public void setTabIcon(int normal,int focus){
        normalIconId=normal;
        focusIconId=focus;
        tab_indicator_icon.setImageResource(normalIconId);
    }

    public void setUnreadCount(int unreadCount){
        if(unreadCount<=0){
            tab_indicator_unread.setVisibility(View.GONE);
        }else {
            if(unreadCount>99){
                tab_indicator_unread.setText("99+");
            }else{
                tab_indicator_unread.setText(unreadCount+"");
            }
            tab_indicator_unread.setVisibility(View.VISIBLE);
        }
    }

    public void setTablSelect(Boolean select){
        if(select){
            tab_indicator_icon.setImageResource(focusIconId);
        }else{
            tab_indicator_icon.setImageResource(normalIconId);
        }
    }


}
