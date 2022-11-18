package com.alperenburakyesil.recipegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        open_Back();
    }

    private void open_Back(){
        ImageButton back_Favorite = (ImageButton) findViewById(R.id.return_category);

        back_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Favorites.this, MainActivity.class));
            }
        });
    }
}