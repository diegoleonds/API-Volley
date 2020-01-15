package com.example.doge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCachorros extends RecyclerView.Adapter<AdapterCachorros.CachorrosViewHolder> {

    private ArrayList<Cachorrinho> cachorrinhos;
    private Context c;

    public AdapterCachorros(Context c){

        cachorrinhos = new ArrayList<Cachorrinho>();
        this.c = c;
    }

    @NonNull
    @Override
    public AdapterCachorros.CachorrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                c).inflate(R.layout.item_cachorrinho,
                parent, false);

        return new CachorrosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCachorros.CachorrosViewHolder holder, int position) {

        if (holder != null){

            holder.tv.setText(cachorrinhos.get(position).getRaca());
        }
    }

    @Override
    public int getItemCount() { return cachorrinhos.size(); }

    public ArrayList<Cachorrinho> getCachorrinhos() {
        return cachorrinhos;
    }

    public class CachorrosViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final ImageView imageView;

        public CachorrosViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv = itemView.findViewById(R.id.nome_cachorrinho);
            this.imageView = itemView.findViewById(R.id.foto_cachorrinho);

        }

        public TextView getTv() {
            return tv;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
