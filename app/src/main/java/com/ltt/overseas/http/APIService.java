package com.ltt.overseas.http;

import com.ltt.overseas.base.BaseBean;
import com.ltt.overseas.model.ExploreResponseDataBean;
import com.ltt.overseas.model.GsonUserBean;
import com.ltt.overseas.model.List_request_centerDataBean;
import com.ltt.overseas.model.LoginBean;
import com.ltt.overseas.model.MessageListBean;
import com.ltt.overseas.model.MyRequestDetailListBean;
import com.ltt.overseas.model.PhoneListBean;
import com.ltt.overseas.model.PreferenceListBean;
import com.ltt.overseas.model.RequestListBean;
import com.ltt.overseas.model.ResponseListBean;
import com.ltt.overseas.model.SectionListBean;
import com.ltt.overseas.model.TypeListBean;
import com.ltt.overseas.model.UserBean;
import com.ltt.overseas.model.UserProfileBean;
import com.ltt.overseas.model.ViewRequestBean;
import com.ltt.overseas.model.updateUserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/5/20.
 */
public interface APIService {

    //Login
    @POST("auth/login")
    Call<GsonUserBean> login(@Body LoginBean userParams);

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
    Call<List_request_centerDataBean> getListRequestCentre();

    //Get Country id
    @GET("service/main/list_section/{type_id}")
    Call<SectionListBean> getSectionList(@Path("type_id") String typeId);

    //Get Country id
    @GET("service/main/view_request/{section_id}")
    Call<ExploreResponseDataBean> getQuestions(@Path("section_id") String sectionId);

    @GET("service/main/view_request/{section_id}")
    Call<ViewRequestBean> getQuestionss(@Path("section_id") String sectionId);

    @GET("service/service_provider/response/list_response")
    Call<ResponseListBean> getResponseList(@Query("page") String page, @Header("Authorization") String authorization);

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


    @GET("user")
    Call<UserProfileBean> getUserProfileLists();

    @POST("user/update_profile")
    Call<BaseBean> updateUserProfileLists(@Body updateUserBean userParams);
}