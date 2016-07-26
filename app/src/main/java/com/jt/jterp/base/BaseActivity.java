package com.jt.jterp.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jt.jterp.utils.Log;
import com.jt.jterp.utils.ManagerActivity;
import com.xiaosu.pulllayout.PullLayout;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/6/16.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG="lifescycle";
    BaseNetReceiver baseNetReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除系统标题
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        registerReceiver();
        ManagerActivity.getInstance().addActivity(this);
    }
    protected void onLoadInner(){};

    protected PullLayout pullLayout(){
        return null;
    };

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        baseNetReceiver = new BaseNetReceiver();
        this.registerReceiver(baseNetReceiver, filter);
    }

    private void unregisterReceiver() {
        this.unregisterReceiver(baseNetReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
        ButterKnife.unbind(this);
        unregisterReceiver();
        Log.d(TAG, "BaseActivity onDestroy Invoke...");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity onStart Invoke...");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity onRestart Invoke...");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "BaseActivity onLowMemory Invoke...");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "BaseActivity onBackPressed Invoke...");
        ManagerActivity.getInstance().removeActivity(this);
    }
}
