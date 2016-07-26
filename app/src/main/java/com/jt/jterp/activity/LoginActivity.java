package com.jt.jterp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.cache.ACache;
import com.jt.jterp.loading.LoadingIndicatorDialog;
import com.jt.jterp.model.UserInfo;
import com.jt.jterp.utils.Config;
import com.jt.jterp.utils.Log;
import com.jt.jterp.utils.MD5;
import com.jt.jterp.utils.ManagerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/***
 * @author 王立强
 * @// TODO: 2016/7/19
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.username_sign_in_button)
    Button usernameSignInButton;

    LoadingIndicatorDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new LoadingIndicatorDialog(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.username_sign_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.username_sign_in_button:
                loginTask(username.getText().toString(), password.getText().toString());
                break;
        }
    }

    //登录
    public void loginTask(final String username, final String password) {
        OkHttpUtils.post()
                .url(Config.SERVER_URL + "/AppLogin/Login")
                .addParams("Account", username)
                .addParams("Password", MD5.md5(password))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        dialog.show();
                        dialog.setMessage("Loading");
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        dialog.cancel();
                        Log.e("onError", e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        dialog.cancel();
                        UserInfo userInfo = JSON.parseObject(response, UserInfo.class);
                        if (userInfo.isSuccess() && "3".equals(userInfo.getCode())) {
                            Toast.makeText(LoginActivity.this, userInfo.getMessage(), Toast.LENGTH_SHORT).show();
                            ACache aCache = ACache.get(LoginActivity.this);
                            aCache.put("acount", username);
                            aCache.put("password", password);
                            aCache.put("userInfo", response);
                            Intent toHome = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(toHome);
                            finish();
                            ManagerActivity.getInstance().removeActivity(LoginActivity.this);
                        } else {
                            Toast.makeText(LoginActivity.this, userInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

