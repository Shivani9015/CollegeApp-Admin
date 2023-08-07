package com.example.admincollege;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


import com.example.admincollege.faculty.UpdateFaculty;
import com.example.admincollege.notice.DeleteNoticeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView uploadNotice,addGalleryImage,addEbook,faculty,deletenotice,logout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=this.getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("false")){
            openLogin();
        }

        uploadNotice = findViewById(R.id.addnotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
       deletenotice = findViewById(R.id.deletenotice);
        faculty = findViewById(R.id.faculty);
      logout = findViewById(R.id.logout);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deletenotice.setOnClickListener(this);
       logout.setOnClickListener(this);
    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this,loginActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {

            Intent intent;
            switch (v.getId()) {
                case R.id.addnotice:
                    intent = new Intent(MainActivity.this, com.example.admincollege.notice.uploadNotice.class);
                    startActivity(intent);
                    break;
                case R.id.addGalleryImage:
                    intent = new Intent(MainActivity.this, UploadImage.class);
                    startActivity(intent);
                    break;
                case R.id.addEbook:
                    intent = new Intent(MainActivity.this, UploadPdfActivity.class);
                    startActivity(intent);
                    break;
                case R.id.faculty:
                    intent = new Intent(MainActivity.this, UpdateFaculty.class);
                    startActivity(intent);
                    break;
                case R.id.deletenotice:
                    intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.logout:
                    editor.putString("isLogin","false");
                    editor.commit();
                    openLogin();
                    break;
            }

        }
    }
