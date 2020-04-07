package com.lantian.lib_commin_ui.indicator;

/**
 * Created by Sherlock·Holmes on 2020-03-27
 */
public enum CHANNEL {

    FARM_MSG("户主信息", 0x01),

    COM_MSG("完善信息", 0x02),

    HOME("首页", 0x03),

    PASTURE("我的牧场", 0x04),

    MINE("个人中心", 0x05),

    YERS("年",0x06),

    MONTH("月",0x07),

    CUSTOM("自定义",0x08),

    CENGYUAN("成员信息",0x09),

    SHOUZHI("收入支出",0x10),

    CAOYUAN("草原面积",0x11),

    MUHUBUTIE("牧户补贴",0x12);


    /**
     * 主页
     */
    public static final int HOME_PAGE_ID = 0x03;
    /**
     * 我的牧场
     */
    public static final int MY_PASTURE_ID = 0x04;
    /**
     * 个人中心
     */
    public static final int MINE_CENTER_ID = 0x05;

    /**
     * 户主信息
     */
    public static final int FARM_MSG_ID = 0x01;
    /**
     * 完善信息
     */
    public static final int COM_MSG_ID = 0x02;

    public static  final int YERS_ID = 0x06;

    public static final int MONTH_ID = 0x07;

    public static  final  int CUSTOM_ID = 0x08;

    public static  final  int CENGYUAN_ID = 0x09;

    public static final int SHOUZHI_ID = 0x10;

    public static final int CAOYUAN_ID = 0x11;

    public static final int MUHUBUTIE_ID = 0x12;

    private final String key;
    private final int value;

    /**
     * @param key
     * @param value
     */
    CHANNEL(String key, int value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * @return
     */
    public String getKey() {
        return key;
    }
}