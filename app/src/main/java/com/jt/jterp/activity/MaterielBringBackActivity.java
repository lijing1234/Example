package com.jt.jterp.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.loading.LoadingIndicatorDialog;
import com.jt.jterp.model.MaterialBringBackInfo;
import com.jt.jterp.utils.Config;
import com.jt.jterp.utils.Log;
import com.jt.jterp.utils.ManagerActivity;
import com.jt.jterp.widget.SwipeItem;
import com.jt.jterp.widget.SwipeListView;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.xiaosu.pulllayout.PullLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class MaterielBringBackActivity extends BaseActivity implements PullLayout.OnPullCallBackListener {

    @Bind(R.id.bringback_listview)
    SwipeListView mListView;
    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.add)
    ImageView add;
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.material_id)
    TextView materialId;
    @Bind(R.id.material_model)
    TextView materialModel;
    @Bind(R.id.material_name)
    TextView materialName;
    @Bind(R.id.material_ordernum)
    TextView materialOrdernum;
    @Bind(R.id.pull_layout)
    PullLayout pullLayout;

    LoadingIndicatorDialog dialog;
    MaterialBringBackAdapter adapter;

    int currentPage = 1; //页数
    int currentRows = 10; // 行数
    String ID; //产品编号
    String ProCompanyID; //公司ID

    private List<MaterialBringBackInfo.RowsBean> infos = new ArrayList<MaterialBringBackInfo.RowsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel_bring_back);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        dialog = new LoadingIndicatorDialog(this);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");//产品编号
        ProCompanyID = intent.getStringExtra("ProCompanyID");//公司ID
        String MaterialCode = intent.getStringExtra("MaterialCode");//产品编号
        String Model = intent.getStringExtra("Model");//产品规格
        String MaterialName = intent.getStringExtra("MaterialName");//产品名称
        String TaskAmount = intent.getStringExtra("TaskAmount");//订单量
        materialId.setText("产品编号：" + MaterialCode);
        materialModel.setText("规格：" + Model);
        materialName.setText("产品名：" + MaterialName);
        materialOrdernum.setText("订单量：" + TaskAmount);
        pullLayout.setOnPullListener(this);
        //获取领退数据
        getBringBackInfo(ID, currentPage, currentRows);
    }

    @OnClick({R.id.back, R.id.add, R.id.scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.add:
                Intent toAdd = new Intent(this,MaterielAddActivity.class);
                startActivity(toAdd);
                break;
            case R.id.scan:
                new IntentIntegrator(this).setCaptureActivity(SmallCaptureActivity.class).initiateScan();
                break;
        }
    }

    /*上拉加载---start*/
    public void postDelay(Runnable action, long delayMillis) {
        getWindow().getDecorView().postDelayed(action, delayMillis);
    }

    @Override
    protected PullLayout pullLayout() {
        return pullLayout;
    }

    @Override
    protected void onLoadInner() {
        adapter.notifyDataSetChanged();
    }

    //下拉刷新
    @Override
    public void onRefresh() {

    }

    //上拉加载
    @Override
    public void onLoad() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                getBringBackInfo(ID, currentPage, currentRows);
                onLoadInner();
                pullLayout().finishPull();
            }
        }, 1000);
    }
    /*上拉加载---end*/

    /*扫码返回值---start*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("MainActivity", "Cancelled scan");
            } else {
                Log.e("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
     /*扫码返回值---end*/

    public void getBringBackInfo(String ID, final int Page, int Rows) {
        OkHttpUtils
                .post()
                .url(Config.SERVER_URL + "/AppMateriel/GetLingTuiList")
                .addParams("page", String.valueOf(Page))
                .addParams("rows", String.valueOf(Rows))
                .addParams("KeyValue", ID)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        dialog.setMessage("Loading");
                        dialog.show();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        dialog.cancel();
                        Log.e("onError", e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        dialog.cancel();
                        MaterialBringBackInfo materialBringBackInfo = JSON.parseObject(response, MaterialBringBackInfo.class);
                        if (!"0".equals(materialBringBackInfo.getRecords()) && !"[]".equals(materialBringBackInfo.getRows().toString())) {
                            currentPage = materialBringBackInfo.getPage() + 1;
                            infos.addAll(materialBringBackInfo.getRows());
                            adapter = new MaterialBringBackAdapter(MaterielBringBackActivity.this, infos);
                            mListView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            SnackbarManager.show(Snackbar.with(MaterielBringBackActivity.this).text("没有更多数据了").duration(1000));
                        }
                    }
                });

    }

    /*适配器*/
    public class MaterialBringBackAdapter extends BaseAdapter {

        List<MaterialBringBackInfo.RowsBean> infos;
        Context ctx;

        public MaterialBringBackAdapter(Context ctx, List<MaterialBringBackInfo.RowsBean> infos) {
            this.ctx = ctx;
            this.infos = infos;
        }

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public Object getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            viewHolder holder = null;
            View menuView = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_bringback, null);
                menuView = View.inflate(getApplicationContext(),
                        R.layout.swipemenu, null);

                convertView = new SwipeItem(convertView, menuView);
                holder = new viewHolder(convertView);
            } else {
                holder = (viewHolder) convertView.getTag();
            }
            holder.liao_name.setText(infos.get(position).getMaterialCode());
            holder.liao_model.setText("规格：" + infos.get(position).getMaterialName());
            holder.liao_pcs.setText("单位：" + infos.get(position).getUnitName());
            holder.liao_sum.setText("累计领料量：" + String.valueOf(infos.get(position).getSumReceiveAmount()));
            holder.liao_date.setText("最近领料时间：" + infos.get(position).getLastReceiveDate());
            holder.bring_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toBring = new Intent(ctx,MaterielBringActivity.class);
                    toBring.putExtra("ID",ID);
                    toBring.putExtra("MaterialID",infos.get(position).getMaterialID());
                    toBring.putExtra("ProCompanyID",ProCompanyID);
                    toBring.putExtra("InOrOut","out");
                    startActivity(toBring);
                }
            });
            holder.back_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return convertView;
        }
    }

    /*UI*/
    public class viewHolder {
        public TextView liao_name;
        public TextView liao_model;
        public TextView liao_pcs;
        public TextView liao_sum;
        public TextView liao_date;
        public TextView bring_tv;
        public TextView back_tv;

        public viewHolder(View view) {
            liao_name = (TextView) view.findViewById(R.id.liao_name);
            liao_model = (TextView) view.findViewById(R.id.liao_model);
            liao_pcs = (TextView) view.findViewById(R.id.liao_pcs);
            liao_sum = (TextView) view.findViewById(R.id.liao_sum);
            liao_date = (TextView) view.findViewById(R.id.liao_date);
            bring_tv = (TextView) view.findViewById(R.id.bring_tv);
            back_tv = (TextView) view.findViewById(R.id.back_tv);
            view.setTag(this);
        }
    }
}
