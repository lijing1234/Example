package com.jt.jterp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jt.jterp.R;
import com.jt.jterp.activity.MaterielActivity;
import com.jt.jterp.activity.PersonalActivity;
import com.jt.jterp.activity.ProductionActivity;
import com.jt.jterp.activity.SmallCaptureActivity;
import com.jt.jterp.loading.LoadingIndicatorDialog;
import com.jt.jterp.utils.Log;

import butterknife.ButterKnife;

/**
 * Created by wangliqiang on 2015/8/11.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {

    ImageView mScan;
    ImageView mPersonal;
    LinearLayout mWuliao_ll;
    LinearLayout mChanliang_ll;


    LoadingIndicatorDialog dialog;

    public static FirstFragment instance() {
        FirstFragment view = new FirstFragment();
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        dialog = new LoadingIndicatorDialog(getActivity());
        mScan = (ImageView) view.findViewById(R.id.scan);
        mWuliao_ll = (LinearLayout) view.findViewById(R.id.wuliao_ll);
        mChanliang_ll = (LinearLayout) view.findViewById(R.id.chanliang_ll);
        mPersonal = (ImageView) view.findViewById(R.id.personal);
        mScan.setOnClickListener(this);
        mWuliao_ll.setOnClickListener(this);
        mChanliang_ll.setOnClickListener(this);
        mPersonal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan:
                IntentIntegrator.forSupportFragment(this).setCaptureActivity(SmallCaptureActivity.class).initiateScan();
                break;
            case R.id.wuliao_ll:
                Intent toMateriel = new Intent(getContext(), MaterielActivity.class);
                startActivity(toMateriel);
                break;
            case R.id.chanliang_ll:
                Intent toProduction = new Intent(getContext(), ProductionActivity.class);
                startActivity(toProduction);
                break;
            case R.id.personal:
                Intent toPersonal = new Intent(getContext(), PersonalActivity.class);
                startActivity(toPersonal);
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("MainActivity", "Cancelled scan");
            } else {
                Log.e("MainActivity", "Scanned");
                Toast.makeText(getContext(), "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
