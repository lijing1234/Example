package com.jt.jterp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jt.jterp.R;
import com.jt.jterp.base.BaseActivity;
import com.jt.jterp.cache.ACache;
import com.jt.jterp.model.TaskInfo;
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

/***
 * @author 王立强
 * @// TODO: 2016/7/19
 */
public class MaterielRequisitionActivity extends BaseActivity implements TextWatcher{

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.wuliao_taskNo)
    EditText wuliaoTaskNo;
    @Bind(R.id.wuliao_branch)
    TextView wuliaoBranch;
    @Bind(R.id.wuliao_workshop)
    TextView wuliaoWorkshop;
    @Bind(R.id.wuliao_line)
    TextView wuliaoLine;
    @Bind(R.id.wuliao_step)
    TextView wuliaoStep;
    @Bind(R.id.wuliao_flight)
    TextView wuliaoFlight;
    @Bind(R.id.wuliao_team)
    TextView wuliaoTeam;
    @Bind(R.id.wuliao_name)
    TextView wuliaoName;
    @Bind(R.id.wuliao_startdate)
    TextView wuliaoStartDate;
    @Bind(R.id.wuliao_enddate)
    TextView wuliaoEndDate;
    @Bind(R.id.wuliao_content)
    LinearLayout wuliaoContent;
    @Bind(R.id.wuliao_search)
    Button wuliaoSearch;

    String ID;//产品编号
    String ProCompanyID;//公司ID
    String MaterialCode;//产品编号
    String Model;//产品规格
    String MaterialName;//产品名称
    String TaskAmount;//订单量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiel_requisition);
        ButterKnife.bind(this);
        wuliaoTaskNo.addTextChangedListener(this);
    }

    @OnClick({R.id.back, R.id.wuliao_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                ManagerActivity.getInstance().removeActivity(this);
                finish();
                break;
            case R.id.wuliao_search:
                Intent toRequisition = new Intent(this,MaterielBringBackActivity.class);
                toRequisition.putExtra("ID",ID);
                toRequisition.putExtra("ProCompanyID",ProCompanyID);
                toRequisition.putExtra("MaterialCode",MaterialCode);
                toRequisition.putExtra("Model",Model);
                toRequisition.putExtra("MaterialName",MaterialName);
                toRequisition.putExtra("TaskAmount",TaskAmount);
                startActivity(toRequisition);
                break;
        }
    }

    private void getTaskInfoByNo(){
        OkHttpUtils
                .post()
                .url(Config.SERVER_URL+"/AppMateriel/TaskInfo")
                .addParams("TaskNumber", wuliaoTaskNo.getText().toString())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        wuliaoContent.setVisibility(View.GONE);
                        wuliaoSearch.setVisibility(View.GONE);
                        wuliaoBranch.setText("");
                        wuliaoWorkshop.setText("");
                        wuliaoLine.setText("");
                        wuliaoStep.setText("");
                        wuliaoFlight.setText("");
                        wuliaoTeam.setText("");
                        wuliaoName.setText("");
                        wuliaoStartDate.setText("");
                        wuliaoEndDate.setText("");
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onResponse(String response) {
                        wuliaoContent.setVisibility(View.VISIBLE);
                        TaskInfo taskInfo = JSON.parseObject(response,TaskInfo.class);
                        if(taskInfo.isSuccess() && "1".equals(taskInfo.getCode()) && taskInfo.getTaskInfo().toString() != "[]"){
                            wuliaoSearch.setVisibility(View.VISIBLE);
                            wuliaoBranch.setText(taskInfo.getTaskInfo().get(0).getProCompanyName());
                            wuliaoWorkshop.setText(taskInfo.getTaskInfo().get(0).getWorkShopName());
                            wuliaoLine.setText("");
                            wuliaoStep.setText("");
                            wuliaoFlight.setText("");
                            wuliaoTeam.setText("");
                            wuliaoName.setText(taskInfo.getTaskInfo().get(0).getMaterialName());
                            wuliaoStartDate.setText(taskInfo.getTaskInfo().get(0).getStartTaskDate().replace("T", " "));
                            wuliaoEndDate.setText(taskInfo.getTaskInfo().get(0).getEndTaskDate().replace("T", " "));
                            ACache aCache = ACache.get(MaterielRequisitionActivity.this);
                            aCache.put("ID", taskInfo.getTaskInfo().get(0).getID());
                            aCache.put("ProCompanyID", taskInfo.getTaskInfo().get(0).getProCompanyID());
                            aCache.put("TaskType",taskInfo.getTaskInfo().get(0).getTaskType()+"");
                            ID = taskInfo.getTaskInfo().get(0).getID();//产品编号
                            ProCompanyID = taskInfo.getTaskInfo().get(0).getProCompanyID();//产品编号
                            MaterialCode = taskInfo.getTaskInfo().get(0).getMaterialCode();//产品编号
                            Model = taskInfo.getTaskInfo().get(0).getModel();//产品规格
                            MaterialName = taskInfo.getTaskInfo().get(0).getMaterialName();//产品名称
                            TaskAmount = taskInfo.getTaskInfo().get(0).getTaskAmount()+"";//订单量
                        }else{
//                            Toast.makeText(MaterielRequisitionActivity.this,"请输入正确的任务号",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        OkHttpUtils.getInstance().cancelTag(this);
        getTaskInfoByNo();
    }
}
