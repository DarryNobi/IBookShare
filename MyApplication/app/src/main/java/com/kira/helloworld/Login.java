package com.kira.helloworld;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kira on 22/04/15.
 */
public class Login extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login_button=(Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText username=(EditText)findViewById(R.id.login_name_edit);
                EditText password=(EditText)findViewById(R.id.login_pwd_edit);


            }
        });
    }
}


