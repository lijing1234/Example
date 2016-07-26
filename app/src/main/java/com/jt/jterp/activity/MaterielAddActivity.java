package com.jt.jterp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jt.jterp.R;
import com.jt.jterp.utils.ManagerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaterielAddActivity extends AppCompatActivity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.materialadd_save)
    TextView materialaddSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel_add);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.materialadd_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.materialadd_save:
                break;
        }
    }
}
