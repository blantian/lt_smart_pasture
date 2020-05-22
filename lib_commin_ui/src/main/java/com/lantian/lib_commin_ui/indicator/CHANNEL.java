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

    LANHOME("设备", 0x30),

    LANMINE("我", 0x31),

    YERS("年",0x06),

    MONTH("月",0x07),

    CUSTOM("自定义",0x08),

    CENGYUAN("成员信息",0x09),

    SHOUZHI("收入支出",0x10),

    CAOYUAN("草原面积",0x11),

    MUHUBUTIE("牧户补贴",0x12),

    BAS_MSG("基础信息",0x13),

    DOC_MSG("档案信息",0x14),

    YAO_MSG("药浴信息",0x15),

    FANG_YI_MSG("防疫信息",0x16),

    CI_WEI_MSG("饲喂信息",0x17),

    YIN_SHUI_MSG("饮水信息",0x18),

    FANG_MU_MSG("放牧信息",0x19),

    FAN_ZHI_MSG("繁殖信息",0x20),

    RONG_CHAN_MSG("绒产量",0x21),

    JIAO_YI_MSG("交易信息",0x22),

    TU_ZAI_MSG("屠宰信息",0x23),

    CHU_LAN_MSG("准备出栏",0x24),

    YI_CHANG_MSG("异常信息",0x25),

    PEI_ZHONG_MSG("配种信息",0x26),

    CHAN_ROU_MSG("产肉信息",0x27),

    CHENG_ZHANG_MSG("成长信息",0x28);

    /**
     * 主页
     */
    public static final int LAN_HOME_ID = 0x20;
    /**
     * 我的牧场
     */
    public static final int LAN_MINE_ID = 0x31;



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

    public static final int BAS_MSG_ID = 0x13;

    public static  final int DOC_MSG_ID = 0x14;

    public static final int YAO_MSG_ID = 0x15;

    public static  final  int FANG_YI_MSG_ID = 0x16;

    public static  final  int CI_WEI_MSG_ID = 0x18;

    public static final int FANG_MU_MSG_ID = 0x19;

    public static final int FAN_ZHI_MSG_ID = 0x20;

    public static final int RONG_CHAN_MSG_ID = 0x21;

    public static final int JIAO_YI_MSG_ID = 0x22;

    public static  final int TU_ZAI_MSG_ID = 0x23;

    public static  final int CHU_LAN_MSG_ID = 0x24;

    public static final int YI_CHANG_MSG_ID = 0x25;

    public static final int PEI_ZHONG_MSG_ID = 0x26;

    public static final int CHAN_ROU_MSG_ID = 0x27;

    public static final int CHENG_ZHANG_MSG_ID = 0x28;

    public static final int YIN_SHUI_ID = 0x29;

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