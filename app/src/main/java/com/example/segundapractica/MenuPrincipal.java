package com.example.segundapractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button subirboton, verFotos, alarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);



        subirboton = findViewById(R.id.button);
        verFotos= findViewById(R.id.btVerfotos);
        alarma=findViewById(R.id.alarma);

        alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Alarma.class);
                startActivity(intent);
            }
        });
        subirboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SubirFoto.class);
                startActivity(intent);
            }
        });

        verFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(),VerImagenes.class);
                startActivity(intent);
            }
        });


    }
}