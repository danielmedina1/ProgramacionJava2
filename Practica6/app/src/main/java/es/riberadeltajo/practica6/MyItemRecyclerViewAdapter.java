package es.riberadeltajo.practica6;

import androidx.annotation.RawRes;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import es.riberadeltajo.practica6.databinding.FragmentItemBinding;

import java.util.ArrayList;
import java.util.List;



public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<TipoMultimedia> mValues;
    MainActivity m;
    MediaPlayer mp;

    private Context context;

    public MyItemRecyclerViewAdapter(ArrayList<TipoMultimedia> items, Context c) {
        mValues = items;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        m = new MainActivity();
        holder.mItem = mValues.get(position);
        //Titulo Inicio
        holder.mTitulo.setText(mValues.get(position).titulo);
        //Titulo Final
        //Autor Inicio
        holder.mAutor.setText(mValues.get(position).autor);
        //Autor Final
        //Portada Inicio
        if (mValues.get(position).bmp1 == null) {
            holder.mPortada.setImageResource(mValues.get(position).portada);
        } else {
            holder.mPortada.setImageBitmap(mValues.get(position).bmp1);
        }
        //Portada Final
        //Tipo Inicio
        if (mValues.get(position).tipo == 0) {
            holder.mTipo.setImageResource(R.drawable.audio);
        } else {
            if (mValues.get(position).tipo == 1) {
                holder.mTipo.setImageResource(R.drawable.video);
            } else {
                if (mValues.get(position).tipo == 2) {
                    holder.mTipo.setImageResource(R.drawable.streaming);
                }
            }
        }
        //Tipo Final
        //Boton
        holder.mBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mValues.get(position).tipo == 0) {
                    m.playAudio(mValues.get(position).uri, context);

                }
                if (mValues.get(position).tipo == 1) {

                }
                if (mValues.get(position).tipo == 2) {

                }
            }
        });


    }
    public void filtrar (int tipo) {
        ArrayList<TipoMultimedia> el = new ArrayList<>();
        for (TipoMultimedia tim : ListaMultimedia.tm) {
            if (tim.tipo != tipo) {
                el.add(tim);
            }
        }
        for (TipoMultimedia tipmu : el) {
            ListaMultimedia.tm.remove(tipmu);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitulo;
        public final TextView mAutor;
        public final ImageButton mBoton;
        public final ImageView mPortada;
        public final ImageView mTipo;
        public TipoMultimedia mItem;
        ImageButton ib;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mTitulo = binding.tituloMedia;
            mAutor = binding.autorMedia;
            mBoton = binding.botonPlay;
            mPortada = binding.imagenMedia;
            mTipo = binding.tipoMedia;
            ib = binding.botonPlay;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mAutor.getText() + "'";
        }
    }
}