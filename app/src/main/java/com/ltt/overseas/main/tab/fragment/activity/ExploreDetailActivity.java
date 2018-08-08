package com.ltt.overseas.main.tab.fragment.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ltt.overseas.R;
import com.ltt.overseas.XApplication;
import com.ltt.overseas.base.BaseActivity;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.core.ActionBar;
import com.ltt.overseas.http.CustomerCallBack;
import com.ltt.overseas.http.RetrofitUtil;
import com.ltt.overseas.main.tab.fragment.dialog.GoEditProfitDialog;
import com.ltt.overseas.main.tab.fragment.dialog.ResponseQuestionDialog;
import com.ltt.overseas.model.AttachmentFileBean;
import com.ltt.overseas.model.CreateResponseBean;
import com.ltt.overseas.model.CreateResponseBodyBean;
import com.ltt.overseas.model.ExploreQuestionBean;
import com.ltt.overseas.model.ExploreQuestionListBean;
import com.ltt.overseas.model.ExploreResponseDataBean;
import com.ltt.overseas.model.QuestionValueBean;
import com.ltt.overseas.model.ResponseBean;
import com.ltt.overseas.model.ResponseListBean;
import com.ltt.overseas.utils.ToastUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class ExploreDetailActivity extends BaseActivity {
    ActionBar bar;
    private String requestid;
    private String userName;
    private String dataCreateTime;
    //>0 可以创建
    private String show_button_response = "0";
    private LayoutInflater mlflater;
    @BindView(R.id.ly_listquest)
    LinearLayout lyRequestList;
    @BindView(R.id.btn_Response)
    Button btn_response;
    public static final int DOWNLOAD_ERROR = 2;
    // 下载成功
    public static final int DOWNLOAD_SUCCESS = 1;
    private String authorization = XApplication.globalUserBean.getAccess_token();

    @Override
    protected int bindLayoutID() {
        return R.layout.activity_explore_detail;
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
        bar.setTitle("Enquiry");
        bar.showNotify();
        mlflater = getLayoutInflater().from(ExploreDetailActivity.this);
        requestid = this.getIntent().getStringExtra("requestid");
        dataCreateTime=getIntent().getStringExtra("datacratetime");
        userName=getIntent().getStringExtra("username");
        show_button_response = this.getIntent().getStringExtra("show_button_response");
        if (show_button_response.equals("0")) {
            btn_response.setVisibility(View.GONE);
        }
        getRequestList();

    }

    @OnClick({R.id.iv_notify, R.id.btn_Response})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_notify:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btn_Response:

                clickBtnResponse();
                break;
        }
    }

    private void clickBtnResponse() {
        // 1 创建response
        // 2 成功后调用conversaion
        showLoadingView();
        Call<CreateResponseBean> call = RetrofitUtil.getAPIService().postCreateResponse(new CreateResponseBodyBean(requestid), authorization);
        call.enqueue(new CustomerCallBack<CreateResponseBean>() {
            @Override
            public void onResponseResult(CreateResponseBean response) {
                dismissLoadingView();
                Intent intent = new Intent(getContext(), ChatsActivity.class);
                intent.putExtra("conversation_id", response.getConversation());
                intent.putExtra("username", userName);
                //intent.putExtra("request_category", dataBean.getRequest_category());
                intent.putExtra("request_id", requestid);
                intent.putExtra("date_created", dataCreateTime);
                startActivity(intent);
            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }

    private void getRequestList() {
        showLoadingView();
        Call<ExploreResponseDataBean> call = RetrofitUtil.getAPIService().getQuestions(requestid);
        call.enqueue(new CustomerCallBack<ExploreResponseDataBean>() {
            @Override
            public void onResponseResult(ExploreResponseDataBean response) {
                dismissLoadingView();
                for (ExploreQuestionListBean reqeustData : response.getData()) {
                    if (reqeustData.getForm_type().equals("file")) {
                        String value = reqeustData.getValue();
                        if (value.equals("false"))
                            continue;
                        Gson gson = new Gson();
                        List<AttachmentFileBean> attachmentFileList = gson.fromJson(value, new TypeToken<List<AttachmentFileBean>>() {
                        }.getType());
                        for (final AttachmentFileBean attachmentfile :
                                attachmentFileList) {
                            if (attachmentfile.getFile_type().equals("image/png") || attachmentfile.getFile_type().equals("image/jpeg")) {
                                LinearLayout ly_iamge= (LinearLayout) findViewById(R.id.ly_image);
                                ImageView imageView =new ImageView(getContext());
                                ly_iamge.addView(imageView);
                                Glide.with(getContext()).load(attachmentfile.getFile_path())
                                        .placeholder(R.mipmap.loading)
                                        .error(R.mipmap.icon_close)
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .override(300,100)
                                        .into(imageView);

                            } else if (attachmentfile.getFile_type().equals("application/pdf")) {
                                View pdfView = mlflater.inflate(R.layout.detailpdflayout, null);
                                TextView tv_fileName = pdfView.findViewById(R.id.tv_title);
                                tv_fileName.setText(attachmentfile.getFile_name());
                                lyRequestList.addView(pdfView);
                                ImageView downPfd = pdfView.findViewById(R.id.download);
                                downPfd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showLoadingView();
                                        opnPdf(attachmentfile.getFile_path(),attachmentfile.getFile_name());
                                    }
                                });

                            } else if (attachmentfile.getFile_type().equals("audio/mp3")||attachmentfile.getFile_type().equals("audio/wav")
                                    ||attachmentfile.getFile_type().equals("application/octet-stream")) {
                                View voiceView = mlflater.inflate(R.layout.detailvoicelayout, null);
                                AudioImageActivity audioObject =new AudioImageActivity(voiceView,attachmentfile.getFile_path(),ExploreDetailActivity.this);
                                TextView tv_tittle = voiceView.findViewById(R.id.tv_title);
                                tv_tittle.setText(attachmentfile.getFile_name());
                                lyRequestList.addView(voiceView);
                            }

                        }


                    } else {
                        View requestView = mlflater.inflate(R.layout.detailrequestlayout, null);
                        TextView requestTittle = requestView.findViewById(R.id.tv_requestittle);
                        requestTittle.setText(reqeustData.getQuestion_title());
                        TextView requestAnswer = requestView.findViewById(R.id.tv_requestanswer);
                        requestAnswer.setText(reqeustData.getValue());
                        lyRequestList.addView(requestView);
                    }
                }


            }

            @Override
            public void onResponseError(BaseBean errorMsg, boolean isNetError) {
                dismissLoadingView();
            }
        });
    }
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case DOWNLOAD_SUCCESS:
                    dismissLoadingView();
                    File file = (File) msg.obj;
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType (Uri.fromFile(file), "application/pdf");
                    startActivity(Intent.createChooser(intent, "标题"));
                    finish();
                    break;
                case DOWNLOAD_ERROR:
                    dismissLoadingView();
                    Toast.makeText(getApplicationContext(), "下载失败！", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    private void opnPdf(final String url, String pdfName){
        final File file1 = new File(Environment.getExternalStorageDirectory(), pdfName);
        new Thread() {
            public void run() {

                File haha = new File( file1.getAbsolutePath());
                //判断是否有此文件
                if (haha.exists()) {
                    //有缓存文件,拿到路径 直接打开
                    Message msg = Message.obtain();
                    msg.obj = haha;
                    msg.what = DOWNLOAD_SUCCESS;
                    handler.sendMessage(msg);
                    return;
                }
//    本地没有此文件 则从网上下载打开
                File downloadfile = downLoad(url, file1.getAbsolutePath());
//    Log.i("Log",file1.getAbsolutePath());
                Message msg = Message.obtain();
                if (downloadfile != null) {
                    // 下载成功,安装....
                    msg.obj = downloadfile;
                    msg.what = DOWNLOAD_SUCCESS;
                } else {
                    // 提示用户下载失败.
                    msg.what = DOWNLOAD_ERROR;
                }
                handler.sendMessage(msg);

            };
        }.start();
    }
    public static File downLoad(String serverpath, String savedfilepath) {
        try {
            URL url = new URL(serverpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                int max = conn.getContentLength();
                InputStream is = conn.getInputStream();
                File file = new File(savedfilepath);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                int total = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                }
                fos.flush();
                fos.close();
                is.close();
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getFileName(String serverurl) {
        return serverurl.substring(serverurl.lastIndexOf("/") + 1);
    }
}
