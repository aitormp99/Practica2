package com.example.segundapractica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class MainActivity extends AppCompatActivity {
    TextView registro;
    EditText textUsername, textPassword;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registro= findViewById(R.id.registro);

        textUsername=findViewById(R.id.edtNombre);
        textPassword=findViewById(R.id.edtPass);
        login= findViewById(R.id.btnLogin);

        FirebaseMessaging.getInstance().subscribeToTopic("aplicacion")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscrito Correctamente";
                        if (!task.isSuccessful()) {
                            msg = "Fallo en la subscripcion";
                        }
                        Log.d("Subscripcion", msg);

                    }
                });

        login.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {

                String  username,password;

                username = textUsername.getText().toString();
                password = textPassword.getText().toString();



                if( !username.equals("") && !password.equals("") ) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];

                            field[0] = "username";
                            field[1] = "password";


                            String[] data = new String[2];

                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("http://ec2-54-167-31-169.compute-1.amazonaws.com/amartinez390/WEB/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Logeado Correctamenete")){

                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MenuPrincipal.class);
                                        startActivity(intent);

                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Todos los campos necesarios", Toast.LENGTH_LONG).show();
                }

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intentRegistro = new Intent(v.getContext(),Registro.class);
                startActivity(intentRegistro);
            }
        });
    }

    }
