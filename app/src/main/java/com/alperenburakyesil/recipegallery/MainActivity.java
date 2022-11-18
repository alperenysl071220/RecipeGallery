package com.alperenburakyesil.recipegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        open_Camera();
        open_History();
        open_Category();
        open_Favorite();
    }

    private void open_Favorite(){
        ImageButton favorite_button = (ImageButton) findViewById(R.id.favorite);

        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Favorites.class));
            }
        });
    }

    private void open_History(){
        ImageButton history_button = (ImageButton)findViewById(R.id.history);

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, History.class));
            }
        });
    }

    private void open_Category(){
        ImageButton category_button = (ImageButton)findViewById(R.id.category);

        category_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Category.class));
            }
        });
    }

    private void open_Camera(){
        ImageButton camera_button = (ImageButton) findViewById(R.id.camera);
        camera_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        }));
    }
}