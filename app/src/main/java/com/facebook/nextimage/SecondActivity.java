package com.facebook.nextimage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Context context = SecondActivity.this;
    private Adapter adapter;
    ArrayList<String> list;
    private String image;
    private  int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


       // btn = findViewById(R.id.setWallpaper);
        viewPager = findViewById(R.id.viewPAger);
        //get the intent got from the images

        getIntentInfo();
        adapter = new Adapter(list, context);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

    }

    private void getIntentInfo() {

        image = getIntent().getStringExtra("image");
        list = getIntent().getStringArrayListExtra("list");
        position = getIntent().getIntExtra("position", 0);

    }
}
