package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.utils.ConfigUtil;

public class ConfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);
    }

    public void saveConfig(View view) {
        EditText userEditText = findViewById(R.id.editTextTextPersonName);
        EditText passEditText = findViewById(R.id.editTextTextPersonName2);

        String username = userEditText.getText().toString();
        String password = passEditText.getText().toString();

        //保存
        ConfigUtil.saveOrChangeConfigToShared(this,username,password);
    }


}