package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltt.overseas.R;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.model.CompanyBean;
import com.ltt.overseas.model.UpdateCompany;
import com.ltt.overseas.model.UserProfileBean;
import com.ltt.overseas.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class CompanyProfileActivity extends BaseActivity {
    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.btn_left)
    ImageView btnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.tv_bottom)
    TextView tvBottom;
    @BindView(R.id.rl_usertitle)
    RelativeLayout rlUsertitle;
    @BindView(R.id.btn_right)
    ImageView btnRight;
    @BindView(R.id.btn_left2)
    ImageView btnLeft2;
    @BindView(R.id.btn_right2)
    ImageView btnRight2;
    @BindView(R.id.right_btn)
    TextView rightBtn;
    @BindView(R.id.right_btn2)
    TextView rightBtn2;
    @BindView(R.id.img_center)
    ImageView imgCenter;
    @BindView(R.id.iv_notify)
    ImageView ivNotify;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    @BindView(R.id.action_bar)
    LinearLayout actionBar;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_change_name)
    TextView tvCompanyChangeName;
    @BindView(R.id.iv_company_name)
    ImageView ivCompanyName;
    @BindView(R.id.tv_company_no)
    TextView tvCompanyNo;
    @BindView(R.id.tv_company_change_no)
    TextView tvCompanyChangeNo;
    @BindView(R.id.iv_company_no)
    ImageView ivCompanyNo;
    @BindView(R.id.tv_company_website)
    TextView tvCompanyWebsite;
    @BindView(R.id.tv_company_change_website)
    TextView tvCompanyChangeWebsite;
    @BindView(R.id.iv_company_website)
    ImageView ivCompanyWebsite;
    @BindView(R.id.tv_company_country)
    TextView tvCompanyCountry;
    @BindView(R.id.tv_company_change_country)
    TextView tvCompanyChangeCountry;
    @BindView(R.id.iv_company_country)
    ImageView ivCompanyCountry;
    @BindView(R.id.tv_company_contact)
    TextView tvCompanyContact;
    @BindView(R.id.tv_company_change_contact)
    TextView tvCompanyChangeContact;
    @BindView(R.id.iv_company_contact)
    ImageView ivCompanyContact;
    @BindView(R.id.tv_company_description)
    TextView tvCompanyDescription;
    @BindView(R.id.tv_company_change_description)
    TextView tvCompanyChangeDescription;
    @BindView(R.id.iv_company_description)
    ImageView ivCompanyDescription;
    @BindView(R.id.iv_update)
    ImageButton ivUpdate;

    private PopupWindow popupWindow;
    private View view;
    private String upCon;
    private UserProfileBean.DataBean userdata;
    private ActionBar bar;
    private boolean isshowchanger = true;    @Override
    protected int bindLayoutID() {
        return R.layout.activity_company_profile;
    }

    @Override
    protected void prepareActivity() {
        bar = ActionBar.init(this);
        bar.setLeft(R.mipmap.back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bar.setTitle("My Profile");
        bar.setRightButton2("Edit", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isshowchanger) {
                    isshowchanger = !isshowchanger;
                    ivCompanyContact.setVisibility(View.VISIBLE);
                    ivCompanyCountry.setVisibility(View.VISIBLE);
                    ivCompanyDescription.setVisibility(View.VISIBLE);
                    ivCompanyName.setVisibility(View.VISIBLE);
                    ivCompanyNo.setVisibility(View.VISIBLE);
                    ivCompanyWebsite.setVisibility(View.VISIBLE);
                    ivUpdate.setVisibility(View.VISIBLE);
                } else {
                    isshowchanger = !isshowchanger;
                    ivCompanyWebsite.setVisibility(View.INVISIBLE);
                    ivUpdate.setVisibility(View.INVISIBLE);
                    ivCompanyNo.setVisibility(View.INVISIBLE);
                    ivCompanyDescription.setVisibility(View.INVISIBLE);
                    ivCompanyContact.setVisibility(View.INVISIBLE);
                    ivCompanyCountry.setVisibility(View.INVISIBLE);
                    ivCompanyName.setVisibility(View.INVISIBLE);
                }


            }
        });
        initData();
    }

    /**
     * get company information
     */
    private void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_left, R.id.iv_company_name, R.id.iv_company_no, R.id.iv_company_website, R.id.iv_company_country, R.id.iv_company_contact, R.id.iv_company_description, R.id.iv_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                break;
            case R.id.iv_company_name:
                updateUserCon("company_name");
                break;
            case R.id.iv_company_no:
                updateUserCon("company_no");
                break;
            case R.id.iv_company_website:
                updateUserCon("company_website");
                break;
            case R.id.iv_company_country:
                updateUserCon("company_country");
                break;
            case R.id.iv_company_contact:
                updateUserCon("company_contact");
                break;
            case R.id.iv_company_description:
                updateUserCon("company_description");
                break;
            case R.id.iv_update:
                update_information();
                break;
        }
    }

    private void update_information() {
        CompanyBean companyBean = new CompanyBean();
        companyBean.setAddress("");
        companyBean.setCompany_name(tvCompanyChangeName.getText().toString().trim());
        companyBean.setContact_number(tvCompanyChangeContact.getText().toString().trim());
        String trim = tvCompanyChangeCountry.getText().toString().trim();
        try {
            int a = Integer.parseInt(trim);
            companyBean.setCountry_id(a);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ToastUtils.showToast("country Id need number type");
            return;
        }
        companyBean.setDescription(tvCompanyChangeDescription.getText().toString().trim());
        companyBean.setRegistered_number(tvCompanyChangeNo.getText().toString().trim());
        companyBean.setStates_id(123);
        Call<UpdateCompany> loginCall = RetrofitUtil.getAPIService().updateCompany(companyBean);
        loginCall.enqueue(new CustomerCallBack<UpdateCompany>() {
            @Override
            public void onResponseResult(UpdateCompany response) {
                int code=response.getCode();
                Log.d("code:", ""+code);
                if(code==200) {

                 ToastUtils.showToast(response.getMsg());
                }
            }

            @Override
            public void onResponseError(BaseBean errorMessage, boolean isNetError) {
                dismissLoadingView();
                ToastUtils.showToast(errorMessage.getMsg().toString());
            }
        });
    }

    private void updateUserCon(final String con) {
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.update_usermsg_popupview, null);
            final EditText et_con = view.findViewById(R.id.et_con);

            Button bt_submit =  view.findViewById(R.id.bt_submit);

            bt_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upCon = et_con.getText().toString();
                    if (upCon.trim().isEmpty()){
                        ToastUtils.showToast("Couldn't is empty");
                        return;
                    }
                    update_change(con,upCon);
                    et_con.setText("");
                    et_con.setText("");
                }
            });
            // 创建一个PopuWidow对象
            popupWindow = new PopupWindow(view, 800, 400);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth()/2
                - popupWindow.getWidth()/2;
        Log.i("coder", "xPos:" + xPos);

        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0,0);
    }

    private void update_change(String con, String upCon) {
        if (con.equals("company_name")){
            tvCompanyChangeName.setText(upCon);
        }
        if (con.equals("company_no")){
            tvCompanyChangeNo.setText(upCon);
        }
        if (con.equals("company_website")){
            tvCompanyChangeWebsite.setText(upCon);
        }
        if (con.equals("company_country")){
            tvCompanyChangeCountry.setText(upCon);
        }
        if (con.equals("company_contact")){
            tvCompanyChangeContact.setText(upCon);
        }
        if (con.equals("company_description")){
            tvCompanyChangeDescription.setText(upCon);
        }
    }
}
