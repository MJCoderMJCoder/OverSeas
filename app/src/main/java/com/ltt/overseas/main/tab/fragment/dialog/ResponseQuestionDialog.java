package com.ltt.overseas.main.tab.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.main.tab.fragment.activity.ExploreActivity;
import com.ltt.overseas.main.tab.fragment.activity.NotificationActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/13 0013.
 */

public class ResponseQuestionDialog extends Dialog{
    private Context mContext;
    private View mView;
    public ResponseQuestionDialog(@NonNull Context context,int theme) {
        super(context,theme);
    }
    public ResponseQuestionDialog(Context context, int theme, double width, double height) {
        super(context, theme);
        mContext=context;
        mView= LayoutInflater.from(mContext).inflate(R.layout.dialog_response_question_layout, null);
        setContentView(mView);
        ImageView iv_add= (ImageView) mView.findViewById(R.id.btn_response_add);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout) mView.findViewById(R.id.lay_question);
                if (layout.getChildCount()>10)
                    return;
                View viewQeustions = LayoutInflater.from(mContext).inflate(R.layout.item_response_question, null);
                TextView tvQustion = (TextView) viewQeustions.findViewById(R.id.response_quetion_title);
                tvQustion.setText("Qeustion " +  Integer.toString(layout.getChildCount() +1));
                layout.addView(viewQeustions);
            }
        });
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p =getWindow().getAttributes();
        p.width = (int) (d.getWidth() * width); // 宽度设置为屏幕的0.65
        if(height > 0){
            p.height = (int) (d.getHeight() * height);
        }
        getWindow().setAttributes(p);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
