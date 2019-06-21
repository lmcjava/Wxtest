package com.test.wx_test.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.test.wx_test.R;
import com.test.wx_test.fragment.ChatFragment;
import com.test.wx_test.fragment.ContactFragment;
import com.test.wx_test.fragment.DiscoverFragment;
import com.test.wx_test.fragment.MeFragment;
import com.test.wx_test.widget.TabIndicator;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;

    private TabIndicator chat;
    private TabIndicator contact;
    private TabIndicator discover;
    private TabIndicator me;

    private FragmentManager fragmentManager;

    private List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new ChatFragment());
        list.add(new ContactFragment());
        list.add(new DiscoverFragment());
        list.add(new MeFragment());
        fragmentManager = getFragmentManager();
        setTitle("ASasAS");
        init();
    }

    public void init(){
        //使用TabLayout初始化底部菜单栏
        TabLayout tabLayout=findViewById(R.id.tab_layout);
        TabLayout.Tab chatTab = tabLayout.newTab();
        chat = new TabIndicator(this);
        chat.setTabTitle("微信");
        chat.setTabIcon(R.drawable.tab_icon_chat_normal,R.drawable.tab_icon_chat_focus);
        chat.setUnreadCount(20);
        chatTab.setCustomView(chat);
        tabLayout.addTab(chatTab);
        TabLayout.Tab contactTab = tabLayout.newTab();
        contact = new TabIndicator(this);
        contact.setTabTitle("通讯录");
        contact.setTabIcon(R.drawable.tab_icon_contact_normal,R.drawable.tab_icon_contact_focus);
        contact.setUnreadCount(30);
        contactTab.setCustomView(contact);
        tabLayout.addTab(contactTab);
        TabLayout.Tab discoverTab = tabLayout.newTab();
        discover = new TabIndicator(this);
        discover.setTabTitle("发现");
        discover.setTabIcon(R.drawable.tab_icon_discover_normal,R.drawable.tab_icon_discover_focus);
        discover.setUnreadCount(10);
        discoverTab.setCustomView(discover);
        tabLayout.addTab(discoverTab);
        TabLayout.Tab meTab = tabLayout.newTab();
        me = new TabIndicator(this);
        me.setTabTitle("我");
        me.setTabIcon(R.drawable.tab_icon_me_normal,R.drawable.tab_icon_me_focus);
        me.setUnreadCount(50);
        meTab.setCustomView(me);
        tabLayout.addTab(meTab);
        //设置默认fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = list.get(0);
        fragmentTransaction.replace(R.id.activity_home_container,fragment);
        System.out.println("111");
        fragmentTransaction.commit();
        chat.setTablSelect(true);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int i = tab.getPosition();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = list.get(i);
        fragmentTransaction.replace(R.id.activity_home_container,fragment);
        fragmentTransaction.commit();
        TabIndicator tabIndicator=(TabIndicator)tab.getCustomView();
        tabIndicator.setTablSelect(true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        TabIndicator tabIndicator=(TabIndicator)tab.getCustomView();
        tabIndicator.setTablSelect(false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent();
//        intent.putExtra("code",1);
//        setResult(1,intent);
        System.out.println("回退onBackPressed");
        finish();
        super.onBackPressed();
    }




    @Override
    protected void onDestroy() {
        System.out.println("销毁indexActivity");
        super.onDestroy();
    }
}
