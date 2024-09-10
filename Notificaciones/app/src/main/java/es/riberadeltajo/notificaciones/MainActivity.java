package es.riberadeltajo.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.btnNotificar);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarNotificacion();
            }
        });
    }

    private void enviarNotificacion() {
        NotificationCompat.Builder miConstructor = new NotificationCompat.Builder(this, "notificacionesListaCompra");
        miConstructor.setSmallIcon(android.R.drawable.star_on);
        miConstructor.setContentTitle("Notificación de Hacienda");
        miConstructor.setContentText("Ha recibido talk");

        Intent apertura = new Intent(this, MainActivity2.class);
        TaskStackBuilder pila = TaskStackBuilder.create(this);
        pila.addParentStack(MainActivity2.class);
        pila.addNextIntent(apertura);

        PendingIntent resultadoPendingIntent = pila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        miConstructor.setContentIntent(resultadoPendingIntent);

        NotificationManager notificador = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        NotificationChannel miCanal = new NotificationChannel("notificacionesListaCompra", "Agencia Tributaria", NotificationManager.IMPORTANCE_DEFAULT);
        notificador.createNotificationChannel(miCanal);

        int idNotificador = 1;
        notificador.notify(idNotificador, miConstructor.build());
    }
}