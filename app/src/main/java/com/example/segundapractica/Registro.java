package com.example.segundapractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Registro extends AppCompatActivity {

    EditText user , nombre , emailtext , pass;
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        user = findViewById(R.id.edtUsername);
        nombre = findViewById(R.id.edtNombreCompleto);
        emailtext = findViewById(R.id.email);
        pass = findViewById(R.id.edtPass);
        aceptar= findViewById(R.id.btnAceptar);



        aceptar.setOnClickListener(new View.OnClickListener() {





            @Override
            public void onClick(View v) {
                String fullname, username,password,email;
                fullname = nombre.getText().toString();
                username = user.getText().toString();
                password = pass.getText().toString();
                email = emailtext.getText().toString();

                if(!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("") ) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            PutData putData = new PutData("http://ec2-54-167-31-169.compute-1.amazonaws.com/amartinez390/WEB/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){

                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Todos los campos necesarios", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
