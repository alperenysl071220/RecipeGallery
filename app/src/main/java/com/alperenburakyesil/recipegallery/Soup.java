package com.alperenburakyesil.recipegallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alperenburakyesil.recipegallery.Model.Soup_Data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Soup extends AppCompatActivity {

    EditText publisher;
    EditText soupName;
    EditText quantity;
    EditText cookingTime;
    EditText ingredients;
    EditText cookingType;
    Button chooseImage;
    Button uploadImage;


    //Choose Image

    public static final int PICK_IMAGE_REQUEST = 200;
    Uri saveUri;

    //Firebase

    FirebaseDatabase database;
    DatabaseReference soup_path;
    FirebaseStorage storage;
    StorageReference image_path;

    //Soup Database

    Soup_Data soup_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soup);

        database = FirebaseDatabase.getInstance();
        soup_path = database.getReference("Soup");
        storage = FirebaseStorage.getInstance();
        image_path = storage.getReference();

        return_category();

        add_soup();
    }

    private void return_category(){
        ImageButton return_category = (ImageButton) findViewById(R.id.return_category);

        return_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soup.this, Category.class));
            }
        });
    }

    private void add_soup(){
        ImageButton add_soup = (ImageButton) findViewById(R.id.add_soup);

        add_soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_addWindow();
            }
        });
    }

    private void open_addWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Soup.this);
        builder.setTitle("New Soup Add");
        builder.setMessage("Please Enter All Informations");

        LayoutInflater layoutInflater = this.getLayoutInflater();
        View add_soup = layoutInflater.inflate(R.layout.add_soup, null);

        publisher = add_soup.findViewById(R.id.publisher);
        soupName = add_soup.findViewById(R.id.soupName);
        quantity = add_soup.findViewById(R.id.quantity);
        cookingTime = add_soup.findViewById(R.id.cooking_time);
        ingredients = add_soup.findViewById(R.id.Ingredients);
        cookingType = add_soup.findViewById(R.id.howCooking);
        chooseImage = add_soup.findViewById(R.id.chooseImage);
        uploadImage = add_soup.findViewById(R.id.uploadImage);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose_Image();
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_Image();
            }
        });

        builder.setView(add_soup);
        builder.setIcon(R.mipmap.restaurant_menu);

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Soup sent to database

                if (soup_data != null){
                    soup_path.push().setValue(soup_data);
                    Toast.makeText(Soup.this, soup_data.getSoup_name() + " added ✓", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();



    }

    private void upload_Image(){
        if (saveUri != null){
            ProgressDialog mDialog = new ProgressDialog(this);
            mDialog.setMessage("Uploading...");
            mDialog.show();

            String imageName = UUID.randomUUID().toString();
            StorageReference imageFile = image_path.child("images/soup/" + imageName);
            imageFile.putFile(saveUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           mDialog.dismiss();
                            Toast.makeText(Soup.this,"Image Uploaded ✓", Toast.LENGTH_SHORT).show();
                            imageFile.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //image send database
                                    soup_data = new Soup_Data(publisher.getText().toString(), soupName.getText().toString(), quantity.getText().toString(),
                                            cookingTime.getText().toString(), ingredients.getText().toString(), cookingType.getText().toString(), uri.toString());
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(Soup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            mDialog.setMessage("% " + progress + " uploaded");
                        }
                    });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            saveUri = data.getData();
            chooseImage.setText("CHOSEN");


        }
    }

    private void choose_Image() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose Image"), PICK_IMAGE_REQUEST);

    }
}