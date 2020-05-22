package com.lantian.lib_network.retrofit2;

import com.google.gson.internal.LinkedTreeMap;
import com.lantian.lib_base.entity.PersonalInfo;
import com.lantian.lib_base.entity.module.response.adress.Adress;
import com.lantian.lib_base.entity.module.response.breeds.AddBreed;
import com.lantian.lib_base.entity.module.response.breeds.BreedClassFind;
import com.lantian.lib_base.entity.module.response.breeds.BreedFind;
import com.lantian.lib_base.entity.module.response.breeds.BreedIndex;
import com.lantian.lib_base.entity.module.response.breeds.Breeds;
import com.lantian.lib_base.entity.module.response.breeds.BreedsOfUser;
import com.lantian.lib_base.entity.module.response.breeds.CountBreedTure;
import com.lantian.lib_base.entity.module.response.breeds.CountBreeddatas;
import com.lantian.lib_base.entity.module.response.breeds.CountCompensate;
import com.lantian.lib_base.entity.module.response.breeds.EarTag;
import com.lantian.lib_base.entity.module.response.breeds.InOut;
import com.lantian.lib_base.entity.module.response.breeds.Laiyuan;
import com.lantian.lib_base.entity.module.response.breeds.Pinzhong;
import com.lantian.lib_base.entity.module.response.breeds.addEarTag;
import com.lantian.lib_base.entity.module.response.breeds.editEarTag;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmInCome;
import com.lantian.lib_base.entity.module.response.farmer.farmdatas.FarmSumData;
import com.lantian.lib_base.entity.module.response.farmer.farmhuku.HukuFind;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.FarmListResponse;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.PersonList;
import com.lantian.lib_base.entity.module.response.farmer.farmmsg.FarmMsgResponse;
import com.lantian.lib_base.entity.module.response.farmer.plan.CaoyuanList;
import com.lantian.lib_base.entity.module.response.farmer.plan.countPlantsum;
import com.lantian.lib_base.entity.module.response.farmer.butie.ButieList;
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
import retrofit2.http.Headers;
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
     * 激活用户
     * @param userid
     * @return
     */
    @GET("Api/User/appact_type")
    Call<BasicResponse<String>> activateUser(@Query("user_id") String userid);

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
    Call<BasicResponse<RegistResponse>> singn(@Field("username") String user,
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

    @FormUrlEncoded
    @POST("Api/Hukou/editis")
    Call<BasicResponse<HukuFind>> editBaseFarmMsg(@Field("user_id") String user_id,
                                                  @Field("name") String username,
                                                  @Field("relations") String relations,
                                                  @Field("tel") String tel,
                                                  @Field("labour_type") String labour_type,
                                                  @Field("id") String id);

    @FormUrlEncoded
    @POST("Api/Hukou/edit")
    Call<BasicResponse<HukuFind>> editFarmMsg(@Field("id") String id,
                                              @Field("idcard_front") String idcardf,
                                              @Field("idcard_side") String idcards,
                                              @Field("idcard_name")String username,
                                              @Field("idcard_gender") String sex,
                                              @Field("birth_date") String birthday,
                                              @Field("idcard") String idcardnum,
                                              @Field("sheng") String sheng,
                                              @Field("shi") String shi,
                                              @Field("xian") String xian,
                                              @Field("xiangxi") String xiangggxi,
                                              @Field("age") String age);

    @GET("Api/Hukou/find")
    Call<BasicResponse<HukuFind>> getHukuFind(@Query("id") String id);

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
    Call<BreedsOfUser>  getBreeds(@Query("user_id") String userid);

    /**
     * 养殖种类分类
     * @param userid
     * @return
     */
    @GET("Api/Breed/statistics")
    Call<BasicResponse<ArrayList<Breeds>>>  getBreedss(@Query("user_id") String userid);

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

    /**
     * 获取牲畜的信息
     * @param id
     * @param user_id
     * @return
     */
    @GET("Api/Breed/find")
    Call<BasicResponse<BreedFind>> getBreedFind(
            @Query("id") String id,
            @Query("user_id") String user_id);

    /**
     * 养殖种类数量
     * @param id
     * @return
     */
    @GET("Api/Public/breedclassfind")
    Call<BasicResponse<BreedClassFind>> getBreedClassFind(@Query("id") String id);

    /**
     * 养殖种类
     * @return
     */
    @GET("Api/Public/breedclass")
    Call<BasicResponse<ArrayList<BreedClassFind>>> getBreedClass();

    /**
     * 品种
     * @return
     */
    @GET("Api/Public/variety")
    Call<BasicResponse<ArrayList<Pinzhong>>> getPinZhong();

    /**
     * 购买梁道
     * @return
     */
    @GET("Api/Public/supplier")
    Call<BasicResponse<ArrayList<Laiyuan>>> getLaiYuan();

    /**
     * 耳标
     * @param userid
     * @param type
     * @return
     */
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

    @GET("Api/Twophasecount/countBreedture")
    Call<BasicResponse<CountBreedTure>> getCountBreedture(@Query("user_id") String userid);

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


    /**
     * 获取成员列表
     * @param userid
     * @return
     */
    @GET("Api/Personnel/index")
    Call<BasicResponse<ArrayList<PersonList>>> getPersenList(@Query("user_id") String userid);

    /**
     * 添加成员
     * @param userid
     * @param img
     * @param call
     * @param name
     * @param relations
     * @param tel
     * @param labourType
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Personnel/add")
    Call<BasicResponse<PersonList>> addPersenList(@Field("user_id") String userid,
                                                  @Field("img") String img,
                                                  @Field("call") String call,
                                                  @Field("name") String name,
                                                  @Field("per_relations") String relations,
                                                  @Field("tel") String tel,
                                                  @Field("labour_type") String labourType);

    /**
     * 修改成员信息
     * @param userid
     * @param img
     * @param call
     * @param name
     * @param relations
     * @param tel
     * @param labourType
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Personnel/editis")
    Call<BasicResponse<PersonList>> editPersenList(@Field("user_id") String userid,
                                                   @Field("img") String img,
                                                   @Field("call") String call,
                                                   @Field("name") String name,
                                                   @Field("per_relations") String relations,
                                                   @Field("tel") String tel,
                                                   @Field("labour_type") String labourType,
                                                   @Field("id") String id);

    /**
     * 删除成员
     * @param id
     * @return
     */
    @GET("Api/Personnel/DelCar")
    Call<BasicResponse<String>> delpersenList(@Query("id") String id);


    /**
     * 获取草原列表
     * @param userid
     * @return
     */
    @GET("Api/Steppearea/index")
    Call<BasicResponse<ArrayList<CaoyuanList>>> getCaoyuanList(@Query("user_id") String userid);


    /**
     * 修改草原信息
     * @param userid
     * @param name
     * @param area
     * @param areaType
     * @param steType
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Steppearea/editis")
    Call<BasicResponse<CaoyuanList>> editCaoyuanList(@Field("user_id") String userid,
                                                     @Field("name") String name,
                                                     @Field("area") String area,
                                                     @Field("area_type") String areaType,
                                                     @Field("ste_type") String steType,
                                                     @Field("id") String id);

    /**
     * 提交养殖档案
     *
     * @param userId
     * @param breedclass_id
     * @param number
     * @param acquisition_time
     * @param become_time
     * @param title
     * @param age
     * @param price
     * @param img
     * @return
     */

    @GET("Api/Breed/add")
    Call<BasicResponse<AddBreed>> addBreed(@Query("user_id") String userId,
                                           @Query("breedclass_id") String breedclass_id,
                                           @Query("number") String number,
                                           @Query("acquisition_time") String acquisition_time,
                                           @Query("title") String title,
                                           @Query("age") String age,
                                           @Query("become_time") String become_time,
                                           @Query("price") String price,
                                           @Query("img") String img,
                                           @Query("sheng") String sheng,
                                           @Query("shi") String shi,
                                           @Query("xian") String xian,
                                           @Query("supplier_id") String supplier_id,
                                           @Query("dizhi") String dizhi,
                                           @Query("variety_id") String type

    );

    /**
     * 添加草原信息
     * @param userid
     * @param name
     * @param area
     * @param areaType
     * @param steType
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Steppearea/add")
    Call<BasicResponse<CaoyuanList>> addCaoyuanList(@Field("user_id") String userid,
                                                    @Field("name") String name,
                                                    @Field("area") String area,
                                                    @Field("area_type") String areaType,
                                                    @Field("ste_type") String steType);

    /**
     * 删除草原
     * @param id
     * @return
     */
    @GET("Api/Steppearea/DelCar")
    Call<BasicResponse<String>> delCaoyuanList(@Query("id") String id);
    /**
     * 获取补贴列表
     * @param userid
     * @return
     */
    @GET("Api/Compensate/index")
    Call<BasicResponse<ArrayList<ButieList>>> getButieList(@Query("user_id") String userid);


    /**
     * 添加补贴
     * @param userId
     * @param title
     * @param price
     * @param addtime
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Compensate/add")
    Call<BasicResponse<ButieList>> addButie(@Field("user_id") String userId,
                                               @Field("title") String title,
                                               @Field("price") String price,
                                               @Field("addtime") String addtime);

    /**
     * 修改补贴
     * @param userId
     * @param title
     * @param price
     * @param addtime
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Compensate/editis")
    Call<BasicResponse<ButieList>> editButie(@Field("user_id") String userId,
                                             @Field("title") String title,
                                             @Field("price") String price,
                                             @Field("addtime") String addtime,
                                             @Field("id") String id);
    /**
     * 删除补贴
     * @param id
     * @return
     */
    @GET("Api/Compensate/DelCar")
    Call<BasicResponse<String>> delButie(@Query("id") String id);


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
    @Headers("Cache-Control: public,max-age = 60")
    Call<BasicResponse<LinkedTreeMap>> getAllBreedIndex(@Query("breed_id") String breedid,
                                                        @Query("user_id") String userid);

    /**
     * 添加耳标
     * @param img
     * @param eartag_id
     * @param weight
     * @param age
     * @param time
     * @param type
     * @param id
     * @param breed_id
     * @param update_userid
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Breeddata/editis")
    Call<BasicResponse<addEarTag>> addBreedEarTag(@Field("img") String img,
                                                  @Field("eartag_id") String eartag_id,
                                                  @Field("weight") String weight,
                                                  @Field("age") String age,
                                                  @Field("time") String time,
                                                  @Field("type") String type,
                                                  @Field("id") String id,
                                                  @Field("breed_id") String breed_id,
                                                  @Field("update_userid") String update_userid);

    /**
     * 修改耳标
     * @param img
     * @param eartag_id
     * @param weight
     * @param age
     * @param time
     * @param type
     * @param id
     * @param breed_id
     * @param update_userid
     * @return
     */
    @FormUrlEncoded
    @POST("Api/Breeddata/editiser")
    Call<BasicResponse<editEarTag>> editBreedEarTag(@Field("img") String img,
                                                    @Field("eartag_id") String eartag_id,
                                                    @Field("weight") String weight,
                                                    @Field("age") String age,
                                                    @Field("time") String time,
                                                    @Field("type") String type,
                                                    @Field("id") String id,
                                                    @Field("breed_id") String breed_id,
                                                    @Field("update_userid") String update_userid);

    @GET("Api/Breed/find")
    Call<BasicResponse<BreedFind>> geBreedFind(@Query("id") String id,@Query("user_id") String userid);


    /**
     * 三级地址
     * 省份
     */
    @GET("Api/Diqu/index")
    Call<BasicResponse<ArrayList<Adress>>> getProvinces();

    /**市**/
    @GET("Api/Diqu/indexpid")
    Call<BasicResponse<ArrayList<Adress>>> getCities(@Query("pid") String pid);

    /**县**/
    @GET("Api/Diqu/indexpid")
    Call<BasicResponse<ArrayList<Adress>>> getCounty(@Query("pid") String pfid);


}
