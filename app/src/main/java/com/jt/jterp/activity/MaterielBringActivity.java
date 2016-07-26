package com.jt.jterp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jt.jterp.R;
import com.jt.jterp.cache.ACache;
import com.jt.jterp.loading.LoadingIndicatorDialog;
import com.jt.jterp.utils.Config;
import com.jt.jterp.utils.Log;
import com.jt.jterp.utils.ManagerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class MaterielBringActivity extends AppCompatActivity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.bring_save)
    TextView bringSave;
    @Bind(R.id.bring_taskNo)
    TextView bringTaskNo;
    @Bind(R.id.bring_lv)
    ListView bringLv;

    LoadingIndicatorDialog dialog;
    bring_m_adapter mBring_m_adapter;

    String ID;//任务ID
    String MaterialID;//物料ID
    String ProCompanyID;//公司ID
    String TaskType;//类型
    String StockType;
    String InOrOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel_bring);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        dialog = new LoadingIndicatorDialog(this);
        ACache aCache = ACache.get(this);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");//产品ID
        TaskType = aCache.getAsString("TaskType");
        if ("2".equals(TaskType)) {
            StockType = "jinghua"; //净化
        } else if ("3".equals(TaskType)) {
            StockType = "baozhuang";//包装
        }
        MaterialID = intent.getStringExtra("MaterialID");
        ProCompanyID = intent.getStringExtra("ProCompanyID");//公司ID
        InOrOut = intent.getStringExtra("InOrOut");

        getOutStocks(ID, StockType, InOrOut, ProCompanyID, MaterialID);
    }

    @OnClick({R.id.back, R.id.bring_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.bring_save:
                break;
        }
    }

    public void getOutStocks(String ID, String stockType, String inOrOut, String proCompanyID, String materialID) {
        OkHttpUtils
                .get()
                .url(Config.SERVER_URL + "/AppMateriel/GetOutStocks")
                .addParams("KeyValue", ID)
                .addParams("StockType", stockType)
                .addParams("InOrOut", inOrOut)
                .addParams("companyId", proCompanyID)
                .addParams("MaterialID", materialID)
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
                        Log.e("response",response);
                    }
                });

    }

    /*物料适配器*/
    class bring_m_adapter extends BaseAdapter {

        Context ctx;

        public bring_m_adapter(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_materielbring, null);
                holder = new viewHolder(convertView);
            } else {
                holder = (viewHolder) convertView.getTag();
            }

            return convertView;
        }
    }

    /*UI*/
    public class viewHolder {
        public TextView bring_chanchengpinNo;
        public TextView bring_guige;
        public TextView bring_dingdanNum;

        public viewHolder(View view) {
            bring_chanchengpinNo = (TextView) view.findViewById(R.id.bring_chanchengpinNo);
            bring_guige = (TextView) view.findViewById(R.id.bring_guige);
            bring_dingdanNum = (TextView) view.findViewById(R.id.bring_dingdanNum);
            view.setTag(this);
        }
    }
}
