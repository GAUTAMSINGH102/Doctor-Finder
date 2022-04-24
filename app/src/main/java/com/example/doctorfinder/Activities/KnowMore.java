package com.example.doctorfinder.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.doctorfinder.R;

public class KnowMore extends AppCompatActivity {

    AppCompatButton chat, video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_more);

        video = findViewById(R.id.videoConsultation);

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowMore.this, VideoCalling.class);
                startActivity(intent);
            }
        });
    }
}