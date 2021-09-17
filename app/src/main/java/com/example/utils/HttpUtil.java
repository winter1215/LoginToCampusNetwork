package com.example.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Base64.Encoder encoder = Base64.getEncoder();


    public static void loginToNetwork(Context context,String username, String password) throws UnsupportedEncodingException {
        //发送http请求
        FormBody formBody = new FormBody.Builder()
                .add("action","login")
                .add("username",username+"@jg")
                .add("password","{B}" + encoder.encodeToString(password.getBytes("UTF-8")))
                .add("ac_id","1")
                .add("user_ip","")
                .add("nas_ip","")
                .add("user_mac","")
                .add("save_me","1")
                .add("ajax","1")
                .build();


        Request request = new Request.Builder()
                .url("http://192.168.10.76:804/include/auth_action.php")
                .post(formBody)
                .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36")
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(context,"请先连接校园网！",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.body().string().contains("login_ok")){
                    //解决在子线程中调用Toast的异常情况处理
                    Looper.prepare();
                    Toast.makeText(context,"登录成功！",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }else {
                    //解决在子线程中调用Toast的异常情况处理
                    Looper.prepare();
                    Toast.makeText(context,"密码错误！",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
        Toast.makeText(context,"登录成功！",Toast.LENGTH_SHORT);

    }

}
