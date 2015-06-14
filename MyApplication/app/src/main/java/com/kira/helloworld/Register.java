package com.kira.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kira.extra.EnableWebService;
import com.kira.extra.Algorithm;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.*;



/**
     * Created by kira on 22/04/15.
     */
public class Register extends Activity {

        private EditText username;
        private EditText password;
        private EditText password_again;
        private EditText email;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);
            EnableWebService.enableWebService();//使主程序能够使用网络通信
            Button register_button=(Button)findViewById(R.id.reg_button);
            register_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = (EditText)findViewById(R.id.reg_name_edit);
                    password = (EditText)findViewById(R.id.reg_pwd_edit);
                    password_again = (EditText)findViewById(R.id.reg_pwd2_edit);
                    email = (EditText)findViewById(R.id.reg_email_edit);

                    if(input_check()){
                        HttpURLConnection urlConnection = null;
                        try {
                            String str_url = "http://ibookshare.sinaapp.com/ibs/register?" +
                                    "username=" + username.getText().toString() +
                                    "&password=" + Algorithm.SHA(password.getText().toString())+
                                    "&email=" + email.getText().toString();
                            URL url = new URL(str_url);
                            urlConnection = (HttpURLConnection) url.openConnection();
                            int response_code = urlConnection.getResponseCode();
                            if(response_code == HttpURLConnection.HTTP_OK){
                                WarnToast("注册成功");
                            }
                            else
                                WarnToast("注册失败");
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
        Toast.makeText(getBaseContext(), str , Toast.LENGTH_SHORT).show();
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

        if(password_again.getText().toString().length() == 0){
            WarnToast("请再次输入密码");
            return false;
        }

        if(email.getText().toString().length() == 0){
            WarnToast("请输入您的邮箱");
            return false;
        }

        if(!password.getText().toString().equals(password_again.getText().toString())){
            WarnToast("两次输入的密码不一致");
            return false;
        }

        Pattern pattern = Pattern.compile("^([0-9a-zA-Z-])+@([0-9a-zA-Z-]+\\.)+([0-9a-zA-Z-])+$");
        Matcher matcher = pattern.matcher(email.getText().toString());
        if(!matcher.matches()){
            WarnToast("邮箱格式不对");
            return false;
        }

        return true;

    }
}


