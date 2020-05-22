package com.lantian.lib_docs.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.lantian.lib_base.MyApp;
import com.lantian.lib_base.database.greendao.AdressDao;
import com.lantian.lib_base.entity.items.Item;
import com.lantian.lib_base.entity.module.response.adress.Adress;
import com.lantian.lib_base.entity.module.response.farmer.farmlist.HuzhuList;
import com.lantian.lib_base.entity.module.response.userinfo.UserInfo;
import com.lantian.lib_base.utils.BaseUtils;
import com.lantian.lib_commin_ui.spinner.SpinnerAdapter;
import com.lantian.lib_docs.R;
import com.lantian.lib_network.retrofit2.MyCallBack;
import com.lantian.lib_network.retrofit2.RetrofitHelper;
import com.lantian.lib_network.utils.NetworkUtils;

import java.util.ArrayList;

/**
 * Created by Sherlock·Holmes on 2020/4/22
 */
public class AdressUtils {

    private AdressDao adressDao = ((MyApp) BaseUtils.getContext()).getDaoSession().getAdressDao();
    private ArrayList<Adress> arrayList;

    /**
     * 三级地址封装
     * @param context
     * @param obj
     * @param sIdProvinces
     * @param sIdCity
     * @param sIdSeat
     */
    public static void bindAddressSpinners(final Context context, final Object obj, final Spinner sIdProvinces, final Spinner sIdCity, final Spinner sIdSeat) {
        if (NetworkUtils.isConnected()){
            RetrofitHelper.getApiService().getProvinces().enqueue(new MyCallBack<ArrayList<Adress>>() {
                @Override
                public void success(ArrayList<Adress> adresses) {
                    /**添加数据库**/
                    ArrayList<Item> items = new ArrayList<>();
                    for (Adress adress : adresses){
                        items.add(new Item(adress.getTitle(),adress.getId()));
                    }
                    sIdProvinces.setAdapter(new SpinnerAdapter(context, R.layout.spinner_item,items));

                    //动态获取省份
                    String sheng = (obj instanceof HuzhuList) ?
                            ((HuzhuList) obj).getSheng() :
                            ((UserInfo) obj).getSheng();

                    //选定选项
                    sIdProvinces.setSelection(((SpinnerAdapter) sIdProvinces.getAdapter())
                            .findItemPostion(sheng));
                }
                @Override
                public void failure(String msg) {

                }
            });

            /**
             * 二级地址
             * 当省份已被选定时，加载下一级地址数据
             */
            sIdProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    String pid = ((Item) sIdProvinces.getAdapter().getItem(position)).getValue();
                    RetrofitHelper.getApiService().getCities(pid).enqueue(new MyCallBack<ArrayList<Adress>>() {
                        @Override
                        public void success(ArrayList<Adress> adresses) {
                            ArrayList<Item> items = new ArrayList<>();
                            for (Adress adress:adresses){
                                items.add(new Item(adress.getTitle(),adress.getId()));
                            }
                            sIdCity.setAdapter(new SpinnerAdapter(context,R.layout.spinner_item, items));
                            ((SpinnerAdapter) sIdCity.getAdapter()).notifyDataSetChanged();

                            //动态获取市
                            String shi = (obj instanceof HuzhuList) ?
                                    ((HuzhuList) obj).getShi() :
                                    ((UserInfo) obj).getShi();
                            //选定选项
                            sIdCity.setSelection(((SpinnerAdapter) sIdCity.getAdapter())
                                    .findItemPostion(shi));
                        }
                        @Override
                        public void failure(String msg) {

                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            /**
             * 获取三级地址
             * 当市已被选定时，加载下一级地址信息
             */
            sIdCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    final String sid = ((Item) sIdCity.getAdapter().getItem(position)).getValue();
                    RetrofitHelper.getApiService().getCounty(sid+ "fid="+ ((Item) sIdProvinces.getSelectedItem()).getValue()).enqueue(new MyCallBack<ArrayList<Adress>>() {
                        @Override
                        public void success(ArrayList<Adress> adresses) {
                            ArrayList<Item> items = new ArrayList<>();
                            for (Adress adress:adresses){
                                items.add(new Item(adress.getTitle(),adress.getId()));
                            }
                            sIdSeat.setAdapter(new SpinnerAdapter(context,R.layout.spinner_item, items));
                            ((SpinnerAdapter) sIdSeat.getAdapter()).notifyDataSetChanged();
                            //动态获取县
                            String xian = (obj instanceof HuzhuList) ?
                                    ((HuzhuList) obj).getXian() :
                                    ((UserInfo) obj).getXian();
                            //选定选项
                            sIdSeat.setSelection(((SpinnerAdapter) sIdSeat.getAdapter())
                                    .findItemPostion(xian));
                        }
                        @Override
                        public void failure(String msg) {

                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    /**
     * 获取地址
     * @param context
     * @param sIdProvinces
     * @param sIdCity
     * @param sIdSeat
     */
    public static void getAdress(final Context context, final Spinner sIdProvinces, final Spinner sIdCity, final Spinner sIdSeat){
        RetrofitHelper.getApiService().getProvinces().enqueue(new MyCallBack<ArrayList<Adress>>() {
            @Override
            public void success(ArrayList<Adress> adresses) {

                ArrayList<Item> items = new ArrayList<>();
                for (Adress adress : adresses){
                    items.add(new Item(adress.getTitle(),adress.getId()));
                }
                sIdProvinces.setAdapter(new SpinnerAdapter(context, R.layout.spinner_item,items));

            }
            @Override
            public void failure(String msg) {

            }
        });

        /**
         * 二级地址
         * 当省份已被选定时，加载下一级地址数据
         */
        sIdProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pid = ((Item) sIdProvinces.getAdapter().getItem(position)).getValue();
                RetrofitHelper.getApiService().getCities(pid).enqueue(new MyCallBack<ArrayList<Adress>>() {
                    @Override
                    public void success(ArrayList<Adress> adresses) {
                        ArrayList<Item> items = new ArrayList<>();
                        for (Adress adress:adresses){
                            items.add(new Item(adress.getTitle(),adress.getId()));
                        }
                        sIdCity.setAdapter(new SpinnerAdapter(context,R.layout.spinner_item, items));
                        ((SpinnerAdapter) sIdCity.getAdapter()).notifyDataSetChanged();

                    }
                    @Override
                    public void failure(String msg) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * 获取三级地址
         * 当市已被选定时，加载下一级地址信息
         */
        sIdCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String sid = ((Item) sIdCity.getAdapter().getItem(position)).getValue();
                RetrofitHelper.getApiService().getCounty(sid+ "fid="+ ((Item) sIdProvinces.getSelectedItem()).getValue()).enqueue(new MyCallBack<ArrayList<Adress>>() {
                    @Override
                    public void success(ArrayList<Adress> adresses) {
                        ArrayList<Item> items = new ArrayList<>();
                        for (Adress adress:adresses){
                            items.add(new Item(adress.getTitle(),adress.getId()));
                        }
                        sIdSeat.setAdapter(new SpinnerAdapter(context,R.layout.spinner_item, items));
                        ((SpinnerAdapter) sIdSeat.getAdapter()).notifyDataSetChanged();
                        //选定选项
                    }
                    @Override
                    public void failure(String msg) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 导入数据库
     * @param context
     * @return
     */
    public AdressUtils initData(final Context context){
        arrayList = new ArrayList<>();
        RetrofitHelper.getApiService().getProvinces().enqueue(new MyCallBack<ArrayList<Adress>>() {
            @Override
            public void success(ArrayList<Adress> adresses) {
                arrayList.addAll(adresses);
                for (Adress adress:adresses){
                    RetrofitHelper.getApiService().getCities( adress.getId()).enqueue(new MyCallBack<ArrayList<Adress>>() {
                        @Override
                        public void success(ArrayList<Adress> adresses1) {
                            arrayList.addAll(adresses1);
                            for (Adress adress1:adresses1){
                                RetrofitHelper.getApiService().getCounty(adress1.getId() + "}").enqueue(new MyCallBack<ArrayList<Adress>>() {
                                    @Override
                                    public void success(ArrayList<Adress> adresses2) {
                                     arrayList.addAll(adresses2);
                                    }
                                    @Override
                                    public void failure(String msg) {

                                    }
                                });
                            }

                        }
                        @Override
                        public void failure(String msg) {

                        }
                    });
                }
            }
            @Override
            public void failure(String msg) {

            }
        });
        adressDao.insertOrReplaceInTx(arrayList);
        return this;
    }

}
