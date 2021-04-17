package com.example.segundapractica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VerImagenes extends AppCompatActivity {
    Button bCargarfoto;
    ImageView imageViewCargar;
    EditText editTextVerfoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagenes);

        editTextVerfoto= findViewById(R.id.editTextImageName);
        bCargarfoto = findViewById(R.id.downloadBtn);
        imageViewCargar = findViewById(R.id.imageView3);


        bCargarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage.getReference();

                    StorageReference pathReference = storageRef.child(editTextVerfoto.getText().toString());

                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getApplicationContext()).load(uri).into(imageViewCargar);
                        }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), "No existe esa foto", Toast.LENGTH_LONG).show();
                        }
                    });



                }catch (Exception e){

                    Toast.makeText(getApplicationContext(), "No existe esa foto", Toast.LENGTH_LONG).show();

                }


            }
        });
    }
}