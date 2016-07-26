package com.jt.jterp.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.cache.ACache;
import com.jt.jterp.model.UserInfo;
import com.jt.jterp.utils.Config;
import com.jt.jterp.utils.Log;
import com.jt.jterp.utils.MD5;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/***
 * @author 王立强
 * @// TODO: 2016/7/19
 */
public class SplashActivity extends BaseActivity {
    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_LOGINCTIVITY = 1001;
    private final static int STAY_TIME = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ACache aCache = ACache.get(SplashActivity.this);
        if (aCache.getAsString("acount") != null && aCache.getAsString("password") != null) {
            String acount = aCache.getAsString("acount");
            String password = aCache.getAsString("password");
            if (!"".equals(acount) && !"".equals(password)) {
                loginAsync(acount, password);
            }
        } else {
            mHandler.sendEmptyMessageDelayed(SWITCH_LOGINCTIVITY, STAY_TIME);
        }

    }

    public void loginAsync(String acount, String password) {
        OkHttpUtils.post()
                .url(Config.SERVER_URL + "/AppLogin/Login")
                .addParams("Account", acount)
                .addParams("Password", MD5.md5(password))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e("onError", e.toString());
                        mHandler.sendEmptyMessageDelayed(SWITCH_LOGINCTIVITY, STAY_TIME);

                    }

                    @Override
                    public void onResponse(String response) {
                        UserInfo userInfo = JSON.parseObject(response, UserInfo.class);
                        if (userInfo.isSuccess() && "3".equals(userInfo.getCode())) {
                            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY, STAY_TIME);
                            createShortCut();
                        } else {
                            mHandler.sendEmptyMessageDelayed(SWITCH_LOGINCTIVITY, STAY_TIME);
                        }
                    }
                });
    }

    //*************************************************
    // Handler:跳转至不同页面
    //*************************************************
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_MAINACTIVITY:
                    Intent mIntent = new Intent();
                    mIntent.setClass(SplashActivity.this, HomePageActivity.class);
                    SplashActivity.this.startActivity(mIntent);
                    SplashActivity.this.finish();
                    break;
                case SWITCH_LOGINCTIVITY:
                    mIntent = new Intent();
                    mIntent.setClass(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(mIntent);
                    SplashActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 创建快捷图标
     */
    private void createShortCut() {
        // 先判断该快捷是否存在
        if (!isExist()) {
            Intent intent = new Intent();
            // 指定动作名称
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            // 指定快捷方式的图标
            Parcelable icon = Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
            // 指定快捷方式的名称
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "金天微商城");
            // 指定快捷图标激活哪个activity
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName component = new ComponentName(this, SplashActivity.class);
            i.setComponent(component);
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
            sendBroadcast(intent);
        }
    }

    /**
     * 判断快捷图标是否在数据库中已存在
     */
    private boolean isExist() {
        boolean isExist = false;
        int version = getSdkVersion();
        Uri uri = null;
        if (version < 2.0) {
            uri = Uri.parse("content://com.android.launcher.settings/favorites");
        } else {
            uri = Uri.parse("content://com.android.launcher2.settings/favorites");
        }
        String selection = " title = ?";
        String[] selectionArgs = new String[]{"Fragment"};
        Cursor c = getContentResolver().query(uri, null, selection, selectionArgs, null);

        if (c != null && c.getCount() > 0) {
            isExist = true;
        }
        if (c != null) {
            c.close();
        }
        return isExist;
    }

    /**
     * 得到当前系统SDK版本
     */
    private int getSdkVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }
}
