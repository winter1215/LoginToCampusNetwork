package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class ConfigUtil {
    private static final String PREF_NAME = "userConfig";


    //将username与password保存到sharedReference（适用存一些少量数据，登录信息，用户习惯设置，判断用户是否第一次使用软件..）里面去
    public static void saveOrChangeConfigToShared(Context context,String username, String password){
        //获取sharedPreference
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConfigUtil.PREF_NAME,MODE_PRIVATE);
        //获取edit来操作xml
        SharedPreferences.Editor edit = sharedPreferences.edit();
        //写入保存的数据
        edit.putString("username",username);
        edit.putString("password",password);
//      注！！！ 提交我靠
        edit.commit();
//        log
        Toast.makeText(context,"保存成功！",Toast.LENGTH_SHORT).show();
    }

    //获取sharedPreference里保存的信息
    public static String getConfByName(Context context,String name){
        SharedPreferences sharedPreferences = context.getSharedPreferences(ConfigUtil.PREF_NAME,MODE_PRIVATE);
        return sharedPreferences.getString(name,"");
    }
}
