package com.example.doge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCachorros extends RecyclerView.Adapter<AdapterCachorros.CachorrosViewHolder> {

    private ArrayList<Dog> dogs;
    private Context c;

    public AdapterCachorros(Context c){

        dogs = new ArrayList<Dog>();
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

            holder.tv.setText(dogs.get(position).getRaca());
        }
    }

    @Override
    public int getItemCount() { return dogs.size(); }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public class CachorrosViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final ImageView imageView;

        public CachorrosViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv = itemView.findViewById(R.id.nome_cachorrinho);
            this.imageView = itemView.findViewById(R.id.foto_cachorrinho);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dog d = dogs.get(getAdapterPosition());

                    if (d.isTemSubRacas()) {

                        Bundle b = new Bundle();
                        b.putString("raca", d.getRaca());

                        Log.e("Raça: ", d.getRaca());

                        Intent i = new Intent(itemView.getContext(),
                                listaSubClasses.class);

                        i.putExtras(b);

                        itemView.getContext().startActivity(i);

                    } else {

                        Bundle b = new Bundle();
                        b.putString("raca", d.getRaca());
                        b.putString("subraca", "");

                        Intent i = new Intent(itemView.getContext(),
                                CachorrinhoActivity.class);

                        Log.e("Raça: ", d.getRaca());

                        i.putExtras(b);

                        itemView.getContext().startActivity(i);
                    }
                }
            });

        }

        public TextView getTv() {
            return tv;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
