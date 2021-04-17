package com.example.segundapractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SubirFoto extends AppCompatActivity {

    final int CODIGO_GALERIA = 1;
    Button bSubirImagen, bGaleriaFoto ;
    ImageView imageViewSubir;
    Uri fotoAsubir;
    EditText editTextSubirNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_foto);

        bSubirImagen = findViewById(R.id.sendBtn);
        bGaleriaFoto = findViewById(R.id.chooseBtn);
        editTextSubirNombre = findViewById(R.id.editTextNombreFoto);

        imageViewSubir = findViewById(R.id.imageView2);


        bGaleriaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent elIntentGal = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(elIntentGal, CODIGO_GALERIA);

            }
        });

        bSubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("codigoImagen",fotoAsubir.toString());
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference spaceRef = storageRef.child(editTextSubirNombre.getText().toString());

                spaceRef.putFile(fotoAsubir);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_GALERIA && resultCode == RESULT_OK) {
            fotoAsubir = data.getData();
            imageViewSubir.setImageURI(fotoAsubir);
        }


    }
}