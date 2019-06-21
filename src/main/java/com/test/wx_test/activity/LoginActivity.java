package com.test.wx_test.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.test.wx_test.R;
import com.test.wx_test.fragment.FillInfoFragment;
import com.test.wx_test.fragment.LogoFra;
import com.test.wx_test.fragment.SignInFra;
import com.test.wx_test.fragment.SignUpFra;
import com.test.wx_test.widget.NormalTopBar;

public class LoginActivity extends Activity implements View.OnClickListener {

    private static final String LOGOFRA="LOGOFRA";
    private static final String FILLINFOFRA="LOGOFRA";
    private static final String SIGNINFRA="SIGNINFRA";
    private static final String SIGNUPFRA="SIGNUPFRA";

    private NormalTopBar normalTopBar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        System.out.println("初始化首页");
        normalTopBar=findViewById(R.id.ntb);
        normalTopBar.setOnBackListener(this);
        normalTopBar.setVisibility(View.GONE);
        fragmentManager = getFragmentManager();

        initView();
    }

    public void initView(){
        //判断开始展示首页
//        if(){
        //登陆注册页面
          FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier_login,new LogoFra(),LOGOFRA);
        fragmentTransaction.addToBackStack(LOGOFRA);
        fragmentTransaction.commit();
//        }else{
        //跳到首页
//        Intent intent = new Intent(this, IndexActivity.class);
//        startActivityForResult(intent,1);
//
//        }
    }

    public void go2SignIn(){
        Fragment fragment = fragmentManager.findFragmentByTag(SIGNINFRA);
        if(fragment==null){
            fragment=new SignInFra();
        }
        // 设置头
        normalTopBar.setVisibility(View.VISIBLE);
        normalTopBar.setTitleText("登陆");
        normalTopBar.setBackVisibi(true);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier_login,fragment,SIGNINFRA);
        fragmentTransaction.addToBackStack(SIGNINFRA);
        fragmentTransaction.commit();
    }

    public void go2SignUp(){
        Fragment fragment = fragmentManager.findFragmentByTag(SIGNUPFRA);
        if(fragment==null){
            fragment=new SignUpFra();
        }
        // 设置头
        normalTopBar.setVisibility(View.VISIBLE);
        normalTopBar.setTitleText("注册");
        normalTopBar.setBackVisibi(true);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier_login,fragment,SIGNUPFRA);
        fragmentTransaction.addToBackStack(SIGNUPFRA);
        fragmentTransaction.commit();
    }
    public void go2FillInfo(){
        Fragment fragment = fragmentManager.findFragmentByTag(SIGNUPFRA);
        if(fragment==null){
            fragment=new FillInfoFragment();
        }
        // 设置头
        normalTopBar.setVisibility(View.VISIBLE);
        normalTopBar.setTitleText("登陆");
        normalTopBar.setBackVisibi(true);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier_login,fragment,FILLINFOFRA);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        System.out.println("LoginActivity>>onBackPressed");
        super.onBackPressed();
    }

    public void clickBack(){
        int count = fragmentManager.getBackStackEntryCount();
        if(count<=1){
            System.out.println("销毁activity");
            finish();
        }else{
            fragmentManager.popBackStack();
            if(count==2){
                FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
                String name = entry.getName();
                if(LOGOFRA.equals(name)){
                    normalTopBar.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==normalTopBar.getImageView()){
            clickBack();
        }
    }

    @Override
    protected void onDestroy() {
        System.out.println("销毁LoginActivity");
        super.onDestroy();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        int code = data.getIntExtra("code", 0);
//        System.out.println(code);
//        if(code==1){
//            onBackPressed();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}
