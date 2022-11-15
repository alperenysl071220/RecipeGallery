package com.alperenburakyesil.recipegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        open_BackScreen();
    }

    private void open_BackScreen(){
        ImageButton back_Button = (ImageButton) findViewById(R.id.back_favorite);

        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(History.this, MainActivity.class));
            }
        });
    }


}