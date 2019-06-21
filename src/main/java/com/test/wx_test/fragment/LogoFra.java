package com.test.wx_test.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.wx_test.R;
import com.test.wx_test.activity.LoginActivity;

public class LogoFra extends Fragment implements View.OnClickListener {

    private Button signInButton;
    private Button signUpButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_logo, null);
        init(view);
        return view;
    }

    public void init(View view){
        signInButton=view.findViewById(R.id.sign_in);
        signUpButton=view.findViewById(R.id.sign_up);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Activity activity = getActivity();
        if(signInButton == v){
            ((LoginActivity) activity).go2SignIn();
        }else if(signUpButton == v){
            ((LoginActivity) activity).go2SignUp();
        }
    }

    @Override
    public void onDestroy() {
        getActivity().finish();
        System.out.println("销毁LogoFra");
        super.onDestroy();
    }
}
