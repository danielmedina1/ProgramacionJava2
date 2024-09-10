package es.riberadeltajo.fotoreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public final int CAPTURA = 1;
    public final int PERMISO_STORAGE = 2;
    public String mRutaDefinitiva;
    private final int CAPTURA_IMAGEN=10;
    Uri foto_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button foto = findViewById(R.id.hacerFoto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, PERMISO_STORAGE);
                } else {
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(i);
                }



            }
        });
    }
    public File crearFichero() throws IOException {
        String tiempo=new
                SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombre_fichero="JPEG_"+tiempo+"_";
        File directorio_almacenaje=
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
        File image=File.createTempFile(nombre_fichero,".jpg",directorio_almacenaje);
        mRutaDefinitiva=image.getAbsolutePath();
        return image;
    }

    public void onClick(View view) {
        Intent hacerFotoIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Crear un fichero y pasárselo como extra a la actividad
        File fich_foto=null;
        try {
            fich_foto=crearFichero();
        }
        catch(IOException e){
            //acción a realizar si no he podido crear el fichero
            e.printStackTrace();
        }
        if(fich_foto!=null){
            foto_uri = FileProvider.getUriForFile(this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    fich_foto);
            hacerFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto_uri);
            startActivityForResult(hacerFotoIntent,CAPTURA);
        }
    }

}