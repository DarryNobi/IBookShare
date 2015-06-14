package com.kira.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kira.extra.Algorithm;
import com.kira.extra.EnableWebService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kira on 22/04/15.
 */
public class Login extends Activity {

    private EditText username;
    private EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EnableWebService.enableWebService();//使主程序能够使用网络通信
        Button login_button=(Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username=(EditText)findViewById(R.id.login_name_edit);
                password=(EditText)findViewById(R.id.login_pwd_edit);

                if(input_check()){
                    HttpURLConnection urlConnection = null;
                    try {
                        String str_url = "http://ibookshare.sinaapp.com/ibs/login?" +
                                "username=" + username.getText().toString() +
                                "&password=" + Algorithm.SHA(password.getText().toString());
                        URL url = new URL(str_url);
                        urlConnection = (HttpURLConnection) url.openConnection();
                        int response_code = urlConnection.getResponseCode();
                        BufferedInputStream reader = new BufferedInputStream(urlConnection.getInputStream());
                        byte[] buffer = new byte[1024];
                        reader.read(buffer);
                        if(response_code == HttpURLConnection.HTTP_OK){
                            WarnToast(new String(buffer,"utf-8"));
                        }
                        else
                            WarnToast("登录失败");
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally {
                        if(urlConnection != null)
                            urlConnection.disconnect();
                    }
                }
            }
        });
    }

    //提醒信息
    private void WarnToast(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
    }

    //检查输入是否有错
    private Boolean input_check(){
        if(username.getText().toString().length() == 0) {
            WarnToast("请输入您的用户名");
            return false;
        }

        if(password.getText().toString().length() == 0){
            WarnToast("请输入密码");
            return false;
        }

        if(password.getText().toString().length() < 6){
            WarnToast("密密码长度必须为6到16位的字符组合");
        }

        return true;

    }
}


