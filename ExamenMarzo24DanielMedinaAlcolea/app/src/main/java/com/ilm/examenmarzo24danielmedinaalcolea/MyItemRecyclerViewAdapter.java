package com.ilm.examenmarzo24danielmedinaalcolea;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilm.examenmarzo24danielmedinaalcolea.placeholder.PlaceholderContent.PlaceholderItem;
import com.ilm.examenmarzo24danielmedinaalcolea.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Bicho> mValues;
    MainActivity m;

    public MyItemRecyclerViewAdapter(List<Bicho> items, Context context) {
        mValues = items;
        m = new MainActivity();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mId.setText(mValues.get(position).identificador + "");
        holder.mNombre.setText(mValues.get(position).nombre);
        holder.mImagen.setImageResource(mValues.get(position).imagen);
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaBichos.contextobicho, ActividadJuego.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ListaBichos.imagenbicho = mValues.get(holder.getBindingAdapterPosition()).imagen;
                ListaBichos.contextobicho.startActivity(i);
                System.out.println("Funciona " + holder.getBindingAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mId;
        public final TextView mNombre;
        public final ImageView mImagen;
        public Bicho mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mId = binding.txtId;
            mNombre = binding.txtNombre;
            mImagen = binding.imgBicho;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}