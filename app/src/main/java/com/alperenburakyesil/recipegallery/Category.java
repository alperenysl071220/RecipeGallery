package com.alperenburakyesil.recipegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        open_main();
        open_soup();
    }

    private void open_main(){
        ImageButton back_category = (ImageButton) findViewById(R.id.return_category);

        back_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category.this, MainActivity.class));
            }
        });
    }

    private void open_soup(){

        ImageButton soup = (ImageButton) findViewById(R.id.soup);

        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Category.this, Soup.class));
            }
        });

    }
}