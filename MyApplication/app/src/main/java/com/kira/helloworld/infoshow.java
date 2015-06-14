package com.kira.helloworld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class infoshow extends Activity {
    private EditText netname;
    private EditText username;
    private EditText school;
    private EditText contactinfo;
    private EditText signature;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        EnableWebService.enableWebService();

        netname= (EditText)findViewById(R.id.netname);
        username=(EditText)findViewById(R.id.username);
        school=(EditText)findViewById(R.id.school);
        contactinfo=(EditText)findViewById(R.id.contactinfo);
        signature=(EditText)findViewById(R.id.signature);

        Button login_button=(Button)findViewById(R.id.modifybutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(infoshow.this, infomodify.class);
                startActivity(intent);

            }
        });

        HttpURLConnection urlConnection = null;
        try {
            String str_url = "http://ibookshare.sinaapp.com/ibs/info?";
            URL url = new URL(str_url);
            urlConnection = (HttpURLConnection) url.openConnection();
            String  response_code = urlConnection.getResponseMessage();
            Log.v("debug",response_code);
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally {
        if(urlConnection != null)
            urlConnection.disconnect();
        }

        netname.setText("");
        username.setText("");
        school.setText("");
        contactinfo.setText("");
        signature.setText("");

    }

}
