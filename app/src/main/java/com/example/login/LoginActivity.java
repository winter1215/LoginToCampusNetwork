package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.utils.ConfigUtil;
import com.example.utils.HttpUtil;

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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

//    jump to another view
    public void goConfigView(View view) {
        Intent intent = new Intent(this,ConfActivity.class);
        startActivity(intent);
        Toast.makeText(this, "请填写你的配置，下次默认记住！", Toast.LENGTH_SHORT).show();
    }

//  login to the campus network
    public void login(View view) throws UnsupportedEncodingException {
        String username = ConfigUtil.getConfByName(this,"username");
        String password = ConfigUtil.getConfByName(this,"password");

        if (username == "0"||password == "0"){
            Toast.makeText(this,"配置信息有误！",Toast.LENGTH_SHORT).show();
            //todo: 可能有问题
            goConfigView(view);
        }else {
//            //发送http请求
//            FormBody formBody = new FormBody.Builder()
//                    .add("action","login")
//                    .add("username",username+"@jg")
//                    .add("password","{B}" + encoder.encodeToString(password.getBytes("UTF-8")))
//                    .add("ac_id","1")
//                    .add("user_ip","")
//                    .add("nas_ip","")
//                    .add("user_mac","")
//                    .add("save_me","1")
//                    .add("ajax","1")
//                    .build();
//
//
//            Request request = new Request.Builder()
//                    .url("http://192.168.10.76:804/include/auth_action.php")
//                    .post(formBody)
//                    .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36")
//                    .build();
//
//            Call call = okHttpClient.newCall(request);
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                    Log.e("winter", e.getMessage());
//                }
//
//                @Override
//                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                    if (response.body().string().contains("login_ok")){
//                        //解决在子线程中调用Toast的异常情况处理
//                        Looper.prepare();
//                        Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }else {
//                        //解决在子线程中调用Toast的异常情况处理
//                        Looper.prepare();
//                        Toast.makeText(LoginActivity.this,"密码错误！",Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//                    Log.e("winter", "onResponse: "+response.body().string());
//                }
//            });
//            Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT);
//            http工具类
            HttpUtil.loginToNetwork(this,username,password);
        }
    }

//    菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //    菜单响应事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.chat_me) {
//   跳转 到
            Intent intent = new Intent(this,WithmeActivity.class);
            startActivity(intent);
        }
        return true;
    }


}