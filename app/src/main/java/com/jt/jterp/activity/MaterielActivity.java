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
 * 物料
 *
 * @author 王立强
 * @TODO: 2016/7/18
 */
public class MaterielActivity extends BaseActivity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.lingliao_ll)
    LinearLayout lingliaoLl;
    @Bind(R.id.ruku_ll)
    LinearLayout rukuLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.lingliao_ll, R.id.ruku_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.lingliao_ll:
                Intent toMaterialRequisition = new Intent(this,MaterielRequisitionActivity.class);
                startActivity(toMaterialRequisition);
                break;
            case R.id.ruku_ll:
                break;
        }
    }
}
