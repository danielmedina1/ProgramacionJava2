package es.riberadeltajo.contentprovider;

import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import es.riberadeltajo.contentprovider.Contacto;
import es.riberadeltajo.contentprovider.databinding.FragmentContactoBinding;
import es.riberadeltajo.contentprovider.databinding.FragmentContactoBinding;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyContactoRecyclerViewAdapter extends RecyclerView.Adapter<MyContactoRecyclerViewAdapter.ViewHolder> {

    private final List<Contacto> mValues;
    private Context contexto;

    public MyContactoRecyclerViewAdapter(List<Contacto> items, Context context) {
        mValues = items;
        contexto = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentContactoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }
    public Bitmap getFoto(long id) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream in = ContactsContract.Contacts.openContactPhotoInputStream(contexto.getContentResolver(), contactUri, true);

        return BitmapFactory.decodeStream(in);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).tiene_telefono+"");
        holder.mContentView.setText(mValues.get(position).nombre);
        holder.mFoto.setImageBitmap(getFoto(mValues.get(position).id));
        getAsyncFoto(holder.mFoto, mValues.get(position).id);
        //holder.mFoto.setImageBitmap();


    }

    private void getAsyncFoto(ImageView mFoto, long id) {

        mFoto.setImageBitmap();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Bitmap foto = cogerFoto(id);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mFoto.setImageBitmap(cogerFoto(id));
                    }
                });
            }

            private Bitmap cogerFoto(long id) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Contacto mItem;

        public ImageView mFoto;

        public ViewHolder(FragmentContactoBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}