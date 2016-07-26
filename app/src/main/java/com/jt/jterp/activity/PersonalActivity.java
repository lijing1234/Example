package com.jt.jterp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.cache.ACache;
import com.jt.jterp.utils.ManagerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.jiben_ll)
    LinearLayout jibenLl;
    @Bind(R.id.mima_ll)
    LinearLayout mimaLl;
    @Bind(R.id.sign_out)
    TextView signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.jiben_ll, R.id.mima_ll,R.id.sign_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.jiben_ll:
                break;
            case R.id.mima_ll:
                break;
            case R.id.sign_out:
                isSignOut(PersonalActivity.this);
                break;
            default:
                break;
        }
    }
    public static void isSignOut(final Context context) {
        //提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示").setMessage("退出后将删除用户信息").setPositiveButton("退出", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                ACache aCache = ACache.get(context);
                aCache.clear();
                ManagerActivity.getInstance().finishActivity();
                System.exit(0);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
}
