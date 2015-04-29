package com.kira.helloworld;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
     * Created by kira on 22/04/15.
     */
    public class Register extends ActionBarActivity {
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register);

            Button register_button=(Button)findViewById(R.id.reg_button);
            register_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText username=(EditText)findViewById(R.id.reg_name_edit);
                    EditText password=(EditText)findViewById(R.id.reg_pwd_edit);
                    EditText password2=(EditText)findViewById(R.id.reg_pwd2_edit);
                    EditText email=(EditText)findViewById(R.id.reg_email_edit);
                    URL url = null;
                    HttpURLConnection urlConnection = null;
                    String str = null;
                    try {
                        url = new URL("http://ibookshare.sinaapp.com/");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally {
                            urlConnection.disconnect();
                        }
                    }
            });
        }
    }


