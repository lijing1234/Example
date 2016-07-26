package com.jt.jterp.loading;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jt.jterp.R;

/**
 * Created by 王立强 on 2016/7/13.
 */
public class LoadingIndicatorDialog extends AlertDialog {

    private TextView mMessageView;

    public LoadingIndicatorDialog(Context context) {
        super(context);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.progress,null);
        mMessageView= (TextView) view.findViewById(R.id.message);
        setView(view);
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessageView.setText(message);
    }
}
