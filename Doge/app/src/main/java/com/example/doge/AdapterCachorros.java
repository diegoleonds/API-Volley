package com.example.doge;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

    private int tempoDeAnimacao;

    private Texto texto;

    public AdapterCachorros(Context c, int tempoDeAnimacao) {

        dogs = new ArrayList<Dog>();
        this.c = c;

        this.tempoDeAnimacao = tempoDeAnimacao;
        this.texto = new Texto();
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

        if (holder != null) {

            holder.tv.setText(dogs.get(position).getRaca());
            texto.deixarPrimeiraLetaMaiuscula(holder.tv);
            
            animacao(holder.itemView);
        }
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }

    private void animacao(View itemView) {

        Animacao a = new Animacao();
        a.fade(itemView, tempoDeAnimacao);

        /*
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? 150 / 2 : (i * 150 / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
         */
    }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public class CachorrosViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;

        public CachorrosViewHolder(@NonNull final View itemView) {
            super(itemView);

            this.tv = itemView.findViewById(R.id.nome_cachorrinho);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dog d = dogs.get(getAdapterPosition());
                    DogDAO.getDogDAO().setUltimoDog(d);

                    Log.e("Adapter click raça: ", d.getRaca() + ", " +
                            d.isTemSubRacas());

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
                        b.putString("pai", d.getPai());

                        Log.e("Adapter raça: ", d.getRaca());

                        Intent i = new Intent(itemView.getContext(),
                                CachorrinhoActivity.class);

                        i.putExtras(b);

                        itemView.getContext().startActivity(i);
                    }
                }
            });


        }

        public TextView getTv() {
            return tv;
        }
    }
}
