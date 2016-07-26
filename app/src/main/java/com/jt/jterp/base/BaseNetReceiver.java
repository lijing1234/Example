package com.jt.jterp.base;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by 王立强 on 2016/7/4.
 */
public class BaseNetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
//            Toast.makeText(context,"网络不可用,请检查网络",Toast.LENGTH_SHORT).show();
            SnackbarManager.show(Snackbar
                            .with(context)
                            .text("网络不可用,请检查网络")
                            .duration(1000));
            OkHttpUtils.getInstance().cancelTag(this);
            //改变背景或者 处理网络的全局变量
        } else {
            //改变背景或者 处理网络的全局变量
        }

    }
}
