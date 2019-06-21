package com.test.wx_test.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.test.wx_test.R;
import com.test.wx_test.entity.Common;
import com.test.wx_test.entity.User;
import com.test.wx_test.retrofit.LoginService;
import com.test.wx_test.utils.CommonUtil;
import com.test.wx_test.utils.RetrofitUtil;
import com.test.wx_test.utils.ToastUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFra extends Fragment implements View.OnClickListener {

    private EditText signUpAcctount;
    private EditText signUpPwd;
    private Button signUpBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fra_sign_up,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        signUpAcctount=view.findViewById(R.id.et_sign_up_account);
        signUpPwd=view.findViewById(R.id.et_sign_up_pwd);
        signUpBtn=view.findViewById(R.id.btn_sign_up);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String signUpAccountText = signUpAcctount.getText().toString();
        String signUpPwdText = signUpPwd.getText().toString();
        if(StringUtils.isBlank(signUpAccountText)){
            ToastUtil.show(getContext(),"请输入账号");
            return;
        }
        if(StringUtils.isBlank(signUpPwdText)){
            ToastUtil.show(getContext(),"请输入密码");
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userPhone",signUpAccountText);
        map.put("userPwd",CommonUtil.parseStrToMd5L32(signUpPwdText));
        //注册账号
        RetrofitUtil.getInstance().create(LoginService.class).redister(map).enqueue(new Callback<Common<String>>() {
            @Override
            public void onResponse(Call<Common<String>> call, Response<Common<String>> response) {
                ToastUtil.show(getContext(),response.body().getMsg());
                System.out.println(">>>>>>>>>>>>>>");
            }

            @Override
            public void onFailure(Call<Common<String>> call, Throwable t) {
                ToastUtil.show(getContext(),"网络繁忙,请稍后...");
                System.out.println("<<<<<<<<<<<<<<<<<");
            }
        });


    }
}
