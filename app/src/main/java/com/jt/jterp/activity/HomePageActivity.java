package com.jt.jterp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.utils.ManagerActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePageActivity extends BaseActivity {

    @Bind(R.id.personal)
    ImageView personal;
    @Bind(R.id.custom_add)
    LinearLayout customAdd;
    @Bind(R.id.xiaoxi)
    LinearLayout xiaoxi;
    @Bind(R.id.liucheng)
    LinearLayout liucheng;
    @Bind(R.id.caigou)
    LinearLayout caigou;
    @Bind(R.id.xiaoshou)
    LinearLayout xiaoshou;
    @Bind(R.id.cangchu)
    LinearLayout cangchu;
    @Bind(R.id.shengchan)
    LinearLayout shengchan;
    @Bind(R.id.caiwu)
    LinearLayout caiwu;
    @Bind(R.id.renzi)
    LinearLayout renzi;
    @Bind(R.id.xitong)
    LinearLayout xitong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.personal, R.id.custom_add, R.id.xiaoxi, R.id.liucheng, R.id.caigou, R.id.xiaoshou, R.id.cangchu, R.id.shengchan, R.id.caiwu, R.id.renzi, R.id.xitong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal:
                Intent toPersonal = new Intent(this, PersonalActivity.class);
                startActivity(toPersonal);
                break;
            case R.id.custom_add:
                break;
            case R.id.xiaoxi:
                break;
            case R.id.liucheng:
                break;
            case R.id.caigou:
                break;
            case R.id.xiaoshou:
                break;
            case R.id.cangchu:
                break;
            case R.id.shengchan:
                Intent toProduction = new Intent(this, ProductionActivity.class);
                startActivity(toProduction);
                break;
            case R.id.caiwu:
                break;
            case R.id.renzi:
                break;
            case R.id.xitong:
                break;
        }
    }

    /**
     * 双击退出函数
     * 在2秒内双击返回键，退出应用
     */
    private static Boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(HomePageActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            ManagerActivity.getInstance().finishActivity();
            System.exit(0);
        }
    }
}
