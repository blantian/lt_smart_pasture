package com.lantian.lib_image_loader.loadpic;

import android.content.Context;
import android.os.Looper;

import com.bumptech.glide.Glide;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Sherlock·Holmes on 2020/4/9
 */
public class GlideCacheUtil {

    public static GlideCacheUtil instance;
    public static GlideCacheUtil getInstance() {
        if (instance == null){
            synchronized (GlideCacheUtil.class){
                if (instance == null){
                    instance = new GlideCacheUtil();
                }
            }
        }
        return instance;
    }
    /**
     * 获取Glide磁盘缓存大小
     * @param mContext
     * @return
     */
    public String getCacheSize(Context mContext) {
        try {
            return getFormatSize(getFolderSize(new File(mContext.getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    /**
     * 清除Glide磁盘缓存，自己获取缓存文件夹并删除方法
     * @param mContext
     * @return
     */
    public boolean cleanCatchDisk(Context mContext) {
        return deleteFolderFile(mContext.getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR, true);
    }

    /**
     * 清除图片磁盘缓存，调用Glide自带方法
     * @param mContext
     * @return
     */
    public boolean clearCacheDiskSelf(final Context mContext) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(mContext).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(mContext).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 清除Glide内存缓存
     * @param mContext
     * @return
     */
    public boolean clearCacheMemory(Context mContext) {
        try {
            //只能在主线程执行
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(mContext).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     * @param file
     * @return
     * @throws Exception
     */
    private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     * @param size
     * @return
     */
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 按目录删除文件夹文件方法
     * @param filePath
     * @param deleteThisPath
     * @return
     */
    private boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
