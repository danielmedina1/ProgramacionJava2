package es.riberadeltajo.sacardialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoS extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(getActivity());
        myBuilder.setTitle("Pregunta para el usuario");
        myBuilder.setMessage("¿Eres una chica?");
        myBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnRespuestaS miRespuesta = (OnRespuestaS) getActivity();
                miRespuesta.OnRespuesta(true);
            }
        });
        myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnRespuestaS miRespuesta = (OnRespuestaS) getActivity();
                miRespuesta.OnRespuesta(false);
            }
        });
        return myBuilder.create();
    }
    public interface OnRespuestaS {
        public void OnRespuesta (boolean respuesta);
    }
}
