package com.jt.jterp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.utils.ManagerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 王立强
 * @// TODO: 2016/7/26
 * 生产
 */
public class ProductionActivity extends BaseActivity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.wuliao)
    LinearLayout wuliao;
    @Bind(R.id.chanliang)
    LinearLayout chanliang;
    @Bind(R.id.shebei)
    LinearLayout shebei;
    @Bind(R.id.zhijian)
    LinearLayout zhijian;
    @Bind(R.id.qita)
    LinearLayout qita;
    @Bind(R.id.renwu_ll)
    LinearLayout renwuLl;
    @Bind(R.id.zhixing_ll)
    LinearLayout zhixingLl;
    @Bind(R.id.zhiban_ll)
    LinearLayout zhibanLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.wuliao, R.id.chanliang, R.id.shebei, R.id.zhijian, R.id.qita, R.id.renwu_ll, R.id.zhixing_ll, R.id.zhiban_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.wuliao:
                Intent toMaterial = new Intent(this,MaterielActivity.class);
                startActivity(toMaterial);
                break;
            case R.id.chanliang:
                break;
            case R.id.shebei:
                break;
            case R.id.zhijian:
                break;
            case R.id.qita:
                break;
            case R.id.renwu_ll:
                break;
            case R.id.zhixing_ll:
                break;
            case R.id.zhiban_ll:
                break;
        }
    }
}
