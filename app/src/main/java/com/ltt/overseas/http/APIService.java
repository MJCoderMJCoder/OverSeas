package com.ltt.overseas.http;
import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.model.CompanyBean;
import com.ltt.overseas.model.CreateResponseBean;
import com.ltt.overseas.model.CreateResponseBodyBean;
import com.ltt.overseas.model.ExploreResponseDataBean;
import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.List_request_centerDataBean;
import com.ltt.overseas.model.LoginBean;
import com.ltt.overseas.model.MessageListBean;
import com.ltt.overseas.model.MyRequestDetailListBean;
import com.ltt.overseas.model.PWBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.PreferenceListBean;
import com.ltt.overseas.model.RequestListBean;
import com.ltt.overseas.model.ResponseListBean;
import com.ltt.overseas.model.SectionListBean;
import com.ltt.overseas.model.SignTokenBean;
import com.ltt.overseas.model.TypeListBean;
import com.ltt.overseas.model.UpdateCompany;
import com.ltt.overseas.model.UpdatePWBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.model.UserProfileBean;
import com.ltt.overseas.model.ViewRequestBean;
import com.ltt.overseas.model.updateUserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface APIService {
    //social_auth/facebook/callback social_auth/facebook
    @GET("social_auth/facebook/callback")
    Call<GsonUserBean> callback(@Query("access_token") String access_token);

    //Login
    @POST("auth/login")
    Call<GsonUserBean> login(@Body LoginBean userParams);

    @GET("service/message")
    Call<SignTokenBean> getSignToken();


    @POST("auth/register")
    Call<GsonUserBean> register(@Body UserBean userParams);

    //Get profile
    @GET("user")
    Call<GsonUserBean> getProfile();

    //Update profile
    @POST("user/update_profile")
    Call<String> modifyProfile(@Body UserBean userParams);

    //Get Country id
    @GET("country/phone_list")
    Call<PhoneListBean> getCountryIds();

    @POST("auth/forget")
    Call<String> forgetPwd(@Body UserBean userParams);

    //GET List all type
    @GET("service/main/list_type")
    Call<TypeListBean> getTypeList();

    @GET("service/main/list_request_center")
    Call<List_request_centerDataBean> getListRequestCentre(@Header("Authorization") String authorization);

    @GET("service/main/list_request_center?sort=older")
    Call<List_request_centerDataBean> getListRequestCentrebysection(@Query("section_id") String section_id,@Header("Authorization") String authorization);
    //Get Country id
    @GET("service/main/list_section/{type_id}")
    Call<SectionListBean> getSectionList(@Path("type_id") String typeId);

    //Get Country id
    @GET("service/main/view_request/{request_id}")
    Call<ExploreResponseDataBean> getQuestions(@Path("request_id") String sectionId);

    @GET("service/main/view_request/{section_id}")
    Call<ViewRequestBean> getQuestionss(@Path("section_id") String sectionId);

    @GET("service/service_provider/response/list_response")
    Call<ResponseListBean> getResponseList(@Query("page") String page, @Header("Authorization") String authorization);

    @GET("service/service_provider/response/list_response?{request_id}&page=1")
    Call<ResponseListBean> getResponseDetail(@Query("page") String request_id, @Header("Authorization") String authorization);

    @POST("service/service_provider/response/create")
    Call<CreateResponseBean> postCreateResponse(@Body CreateResponseBodyBean responseParams, @Header("Authorization") String authorization);

    @GET("service/user/request")
    Call<RequestListBean> getRequestList(@Query("page") String page, @Header("Authorization") String authorization);

    @GET("service/main/view_request/{section_id}")
    Call<MyRequestDetailListBean> getRequestDetail(@Path("section_id") String sectionId);

    //Login
    @POST("auth/login")
    Call<UserBean> loginTest(@Body UserBean userParams);

    //Get message list
    @GET("service/message/service_provider")
    Call<MessageListBean> getMessageLists(@Query("page") int page);
    //
    //    //
    //    @PUT("users/changePwd")
    //    Call<String> changePwd(@Body UserParams userParams);

    //    //
    //    @DELETE("address/{addressId}")
    //    Call<String> delAdddress(@Path("addressId") String addressId);
    @GET("user/list_preference")
    Call<PreferenceListBean> getPreferenceLists();

    @POST("user/change_password")
    Call<UpdatePWBean> updatePW(@Body PWBean pwParams);

    @GET("user")
    Call<UserProfileBean> getUserProfileLists();

    @POST("user/update_company")
    Call<UpdateCompany> updateCompany(@Body CompanyBean companyParams);

    @POST("user/update_profile")
    Call<BaseBean> updateUserProfileLists(@Body updateUserBean userParams);

    @GET("social_auth/google")
    Call<GsonUserBean> googlelogin(@Query("id_token") String id_token);
}