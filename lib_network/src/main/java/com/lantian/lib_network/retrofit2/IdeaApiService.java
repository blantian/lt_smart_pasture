package com.lantian.lib_network.retrofit2;

import com.google.gson.internal.LinkedTreeMap;
import com.lantian.lib_base.entity.PersonalInfo;
import com.lantian.lib_base.entity.module.response.breeds.BreedClassFind;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.Breeds;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.breeds.CountBreeddatas;
import com.lantian.lib_base.entity.module.response.breeds.CountCompensate;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.InOut;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmInCome;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmSumData;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_base.entity.module.response.farmer.plan.addPlan;
import com.lantian.lib_base.entity.module.response.farmer.plan.countPlantsum;
import com.lantian.lib_base.entity.module.response.farmer.subsidy.addSubsidy;
import com.lantian.lib_base.entity.module.response.login.LoginResponse;
import com.lantian.lib_base.entity.module.response.picture.UplodPic;
import com.lantian.lib_base.entity.module.response.product.ProductResponse;
import com.lantian.lib_base.entity.module.response.regist.CheckPhoneResponse;
import com.lantian.lib_base.entity.module.response.regist.CheckUsernameResponse;
import com.lantian.lib_base.entity.module.response.regist.RegistResponse;
import com.lantian.lib_base.entity.module.response.remakepass.GetMessageResponse;
import com.lantian.lib_base.entity.module.response.remakepass.RemakePassWord;
import com.lantian.lib_base.entity.module.response.userinfo.UserInfo;
import com.lantian.lib_network.common.BasicResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Sherlock·Holmes on 2020-03-06
 */
public interface IdeaApiService {

    /**
     * 登录
     * @param user
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("Api/ApiLogin/login")
    Observable<BasicResponse<LoginResponse>> login(@Field("username") String user, @Field("password") String password);

    /**
     * 判断新旧用户
     * @param userId
     * @return
     */
    @GET("Api/Hukou/userhukou")
    Call<BasicResponse<String>> checkUser(@Query("user_id") String userId);

    /**
     * 获取首页
     * @param UserId
     * @return
     */
    @FormUrlEncoded
    @POST("Api/ApiDevices/product_index")
    Call<BasicResponse<ArrayList<ProductResponse>>> getHome(@Field("user_id") String UserId);

    /**
     * 注册
     * @param user
     * @param password
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("Api/User/store")
    Observable<BasicResponse<RegistResponse>> singn(@Field("username") String user,
                                                    @Field("passwprd") String password,
                                                    @Field("phone") String phone);
    /**
     * 注册检查用户名
     * @param username
     * @return
     */
    @GET("Api/User/username")
    Call<CheckUsernameResponse> getUsername(@Query("username") String username);

    /**
     * 检查手机号吗能不能用
     * @param phone
     * @return
     */
    @GET("Api/User/phone")
    Call<CheckPhoneResponse> getPhone(@Query("phone") String phone);

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @GET("Api/Alisms/code.html")
    Call<GetMessageResponse> getMessage(@Query("phone") String phone);

    /**
     * 重置密码
     * @param phone
     * @param password
     * @return
     */
    @Multipart
    @POST("Api/ApiLogin/resetpassword")
    Call<BasicResponse<RemakePassWord>> remakePass(@Part("phone") RequestBody phone, @Part("password") RequestBody password);

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    @GET("Api/ApiUser/info")
    Call<BasicResponse<PersonalInfo>> getUser(@Query("user_id") String userId);

    /**
     * 获取已打标的牧户群体
     * @param userid
     * @return
     */
    @GET("Api/User/userlist")
    Call<BasicResponse<ArrayList<FarmListResponse>>> getFarmList(@Query("user_id") String userid);


    @FormUrlEncoded
    @POST("Api/Hukou/add")
    Call<BasicResponse<FarmMsgResponse>> farmMsgResponse(@Field("user_id") String user_id,
                                                               @Field("name") String username,
                                                               @Field("relations") String relations,
                                                               @Field("tel") String tel,
                                                               @Field("labour_type") String labour_type);
    /**
     * 图片上传
     */
    @Multipart
    @POST("Api/Img/tupian")
    Call<BasicResponse<UplodPic>> uploadImg(@Part MultipartBody.Part file);


    /**
     * 牧户档案总会数据
     * @param userid
     * @return
     */
    @GET("Api/Twophasecount/hukou")
    Call<BasicResponse<FarmSumData>> getFarmSum(@Query("user_id") String userid);

    /**
     * 牧户档案收入支出总汇
     * @param userid
     * @param typetime
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("Api/Twophasecount/incomeexpenditure")
    Call<BasicResponse<FarmInCome>> getFarmIncome(@Query("user_id") String userid,
                                                  @Query("typetime") String typetime,
                                                  @Query("starttime") String startTime,
                                                  @Query("endtime") String endTime);

    /**
     * 检查用户时候有养殖
     * @param userid
     * @return
     */
    @GET("Api/Twophasecount/countBreedture")
    Call<BasicResponse<CountBreedTure>> getCountBreed(@Query("user_id") String userid);

    /**
     * 养殖种类分类
     * @param userid
     * @return
     */
    @GET("Api/Breed/statistics")
    Call<BasicResponse<ArrayList<Breeds>>>  getBreeds(@Query("user_id") String userid);

    /**
     * 养殖列表索引
     * @param userid
     * @param breedclassId
     * @param p
     * @return
     */
    @GET("Api/Breed/index")
    Call<BasicResponse<ArrayList<BreedIndex>>> getBreedIndex(@Query("user_id") String userid,
                                                             @Query("breedclass_id") String breedclassId,
                                                             @Query("p") String p);

    @GET("Api/Public/breedclassfind")
    Call<BasicResponse<BreedClassFind>> getBreedClassFind(@Query("id") String id);

    @GET("Api/Public/eartag")
    Call<BasicResponse<ArrayList<EarTag>>> getEarTag(@Query("user_id") String userid,@Query("type") String type);


    /**
     * 养殖档案分类下的数据统计
     *
     * @param userId
     * @param typeTime
     * @param startTime
     * @param endTime
     * @return
     */
    @GET("Api/Breed/statistics")
    Call<BasicResponse<ArrayList<Breeds>>> getBreedStatistics(
            @Query("user_id") String userId,
            @Query("typetime") String typeTime,
            @Query("starttime") String startTime,
            @Query("endtime") String endTime
    );

    /**
     * 收入支出
     * @param userId
     * @param typeTime
     * @param startTime
     * @param endTime
     * @param time
     * @return
     */
    @GET("Api/Twophasecount/incomeexpenditure")
    Call<BasicResponse<InOut>> countInOut(
            @Query("user_id") String userId,
            @Query("typetime") String typeTime,
            @Query("starttime") String startTime,
            @Query("endtime") String endTime,
            @Query("time") String time
    );

    /**
     * 交易数量
     * @param userId
     * @return
     */
    @GET("Api/Twophasecount/countBreeddatas")
    Call<BasicResponse<CountBreeddatas>> getBreedDatas(@Query("user_id") String userId);

    /**
     * 获取经营草原总数
     * @param userid
     * @param typetime
     * @param starttime
     * @param endtime
     * @return
     */
    @GET("Api/Twophasecount/countPlantclass")
    Call<BasicResponse<countPlantsum>> getPlanSum(@Query("user_id") String userid,
                                                  @Query("typetime") String typetime,
                                                  @Query("starttime") String starttime,
                                                  @Query("endtime") String endtime);

    /**
     * 牧户补贴
     * @param userid
     * @param typetime
     * @param starttime
     * @param endtime
     * @return
     */
    @GET("Api/Twophasecount/countCompensate")
    Call<BasicResponse<CountCompensate>> getCompensate(@Query("user_id") String userid,
                                                       @Query("typetime") String typetime,
                                                       @Query("starttime") String starttime,
                                                       @Query("endtime") String endtime);
    /**
     * 户主信息list
     * @param userId
     * @return
     */
    @GET("Api/Hukou/hukoulist")
    Call<BasicResponse<ArrayList<HuzhuList>>> getHuZhu(@Query("user_id") String userId);

    //增
    @FormUrlEncoded
    @POST("Api/Steppearea/add")
    Call<BasicResponse<addPlan>> addSte(
            @Field("user_id") String userId,
            @Field("name") String name,
            @Field("area") String area,
            @Field("area_type") String areaType,
            @Field("ste_type") String steType
    );

    //删
    @GET("Api/Steppearea/DelCar")
    Call<BasicResponse<String>> delSte(@Query("id") String id);

    @FormUrlEncoded
    @POST("Api/Compensate/add")
    Call<BasicResponse<addSubsidy>> addSubsidy(@Field("user_id") String userId,
                                               @Field("title") String title,
                                               @Field("price") String price,
                                               @Field("addtime") String addtime);

    //删
    @GET("Api/Compensate/DelCar")
    Call<BasicResponse<String>> delCompensate(@Query("id") String id);


    /**
     * 获取个人信息
     *
     * @param userId
     * @return
     */
    @GET("Api/ApiUser/info")
    Call<BasicResponse<UserInfo>> getUserInfo(@Query("user_id") String userId);


    /**
     * 养殖牲畜全部信息列表
     * @param userid
     * @param breedid
     * @return
     */
    @GET("Api/Breeddata/indexlist")
    Call<BasicResponse<LinkedTreeMap>> getAllBreedIndex(@Query("breed_id") String breedid,
                                                                   @Query("user_id") String userid);


}
