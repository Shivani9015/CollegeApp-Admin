package com.example.admincollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText userEmail,userPass;
    private TextView tvShow;
    private RelativeLayout loginBtn;

    private  String email,pass;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences=this.getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("yes")){
            openDash();
        }

        userEmail= findViewById(R.id.user_email);
        userPass= findViewById(R.id.user_pass);
        tvShow= findViewById(R.id.txt_show);
        loginBtn= findViewById(R.id.login_btn);

        tvShow .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userPass.getInputType()==144){
                    userPass.setInputType(129);
                    tvShow.setText("Hide");
                }else{
                    userPass.setInputType(144);
                    tvShow.setText("Show");
                }
                userPass.setSelection(userPass.getText().length());

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

    }

    private void validateData() {
        email=userEmail.getText().toString();
        pass = userPass.getText().toString();

        if(email.isEmpty()){
            userEmail.setError("Required");
            userEmail.requestFocus();
        }else if(pass.isEmpty()){
            userPass.setError("Required");
            userPass.requestFocus();
        }else if(email.equals("rrsdce@gmail.com") && pass.equals("11223344")){
            editor.putString("isLogin","yes");
            editor.commit();
          openDash();
        }else{
            Toast.makeText(this, "Please Check Email And Password!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDash() {

        startActivity(new Intent(loginActivity.this,MainActivity.class));
        finish();
    }
}