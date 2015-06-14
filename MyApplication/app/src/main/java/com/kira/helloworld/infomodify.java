package com.kira.helloworld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.*;
import com.kira.extra.EnableWebService;
/**
 * Created by nobi on 5/9/15.
 */

public class infomodify extends Activity {
    private EditText netname;
    private EditText username;
    private EditText school;
    private EditText contactinfo;
    private EditText signature;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomodify);
        EnableWebService.enableWebService();
        Intent intent=getIntent();
        Button login_button=(Button)findViewById(R.id.infomodifyconfirm);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                netname= (EditText)findViewById(R.id.netname);
                username=(EditText)findViewById(R.id.username);
                school=(EditText)findViewById(R.id.school);
                contactinfo=(EditText)findViewById(R.id.contactinfo);
                signature=(EditText)findViewById(R.id.signature);



                HttpURLConnection urlConnection = null;
                try {
                    String str_url = "http://ibookshare.sinaapp.com/ibs/infomodify?" +
                            "username=" + netname.getText().toString() +
                            "&password=" + username.getText().toString() +
                            "&school=" + school.getText().toString() +
                            "&contactinfo=" + contactinfo.getText().toString() +
                            "&signature=" + signature.getText().toString() ;
                     URL url = new URL(str_url);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    int response_code = urlConnection.getResponseCode();
                    if(response_code == HttpURLConnection.HTTP_OK){
                        WarnToast("修改成功");
                    }
                    else
                        WarnToast("修改失败");
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null)
                        urlConnection.disconnect();
                }




                }
        });
    }
    //提醒信息
    private void WarnToast(String str) {
        Toast.makeText(getBaseContext(), str , Toast.LENGTH_SHORT).show();
    }
}
