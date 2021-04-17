package com.example.segundapractica;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Looper.prepare();
        Handler handler = new Handler();

        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             Toast.makeText(getBaseContext(), "Titulo: "+remoteMessage.getNotification().getTitle()+ ", Mensaje: "+remoteMessage.getNotification().getBody(), Toast.LENGTH_LONG).show();
                         }

                         });


        Looper.loop();
    }

}
