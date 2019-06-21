package com.test.wx_test.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.wx_test.R;
import com.test.wx_test.activity.IndexActivity;
import com.test.wx_test.activity.LoginActivity;
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

public class SignInFra extends Fragment implements View.OnClickListener {

    private final static int RCODE=1;

    private EditText account;
    private EditText pwd;
    private Button sign;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_sign_in,null);
        initView(view);

        return view;
    }

    public void initView(View view){
        account=view.findViewById(R.id.et_sign_in_account);
        pwd=view.findViewById(R.id.et_sign_in_pwd);
        sign=view.findViewById(R.id.btn_sign_in);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String accountText = account.getText().toString();
        if(StringUtils.isBlank(accountText)){
            ToastUtil.show(getContext(),"账号不能为空");
            return;
        }
        String pwdText = account.getText().toString();
        if(StringUtils.isBlank(pwdText)){
            ToastUtil.show(getContext(),"密码不能为空");
            return;
        }
        Map<String,Object> map= new HashMap<>();
        map.put("userPhone",accountText);
        String md5L32 = CommonUtil.parseStrToMd5L32(pwdText);
        map.put("userPwd",md5L32);
        RetrofitUtil.getInstance().create(LoginService.class).login(map).enqueue(new Callback<Common<User>>() {
            @Override
            public void onResponse(Call<Common<User>> call, Response<Common<User>> response) {
                Common<User> common = response.body();
                ToastUtil.show(getContext(),common.getMsg());
                if(common.getCode()==200){
                    Intent intent = new Intent(getContext(), IndexActivity.class);
//            startActivityForResult(intent,RCODE);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Common<User>> call, Throwable t) {
                ToastUtil.show(getContext(),"网络繁忙,请稍后...");
            }
        });
        //登陆过程
//        if("123".equals(accountText)&&"123".equals(pwdText)){
//            Intent intent = new Intent(getContext(), IndexActivity.class);
//            startActivityForResult(intent,RCODE);
//        }else{
//            ToastUtil.show(getContext(),"用户名或密码不正确");
//            return;
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int code = data.getIntExtra("code", 0);
        if(code==RCODE){
           getActivity().onBackPressed();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
