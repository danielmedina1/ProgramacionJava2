package es.riberadeltajo.pratica3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import es.riberadeltajo.pratica3.databinding.FragmentItemBinding;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Bike> mValues;
    Context mContext;

    public MyItemRecyclerViewAdapter(ArrayList<Bike> bikeList, Context context) {
        mValues = bikeList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mCiudad.setText(mValues.get(position).city);
        holder.mDescripcion.setText(mValues.get(position).description);
        holder.mLocalizacion.setText(mValues.get(position).location);
        holder.mDueño.setText(mValues.get(position).owner);
        holder.mImagen.setImageBitmap(mValues.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mCiudad;
        public final TextView mDueño;
        public final TextView mLocalizacion;
        public final TextView mDescripcion;
        public final ImageView mImagen;
        ImageButton ib;
        public Bike mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mCiudad = binding.txtCity;
            mDueño= binding.txtOwner;
            mLocalizacion = binding.txtLocation;
            mDescripcion = binding.txtDescription;
            mImagen = binding.imgPhoto;
            ib = binding.btnMail;

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCiudad.getText() + "'";
        }
    }
}