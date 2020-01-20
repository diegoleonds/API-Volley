package com.example.doge.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doge.model.Dog;
import com.example.doge.R;

import java.util.ArrayList;

public class AdapterCachorros extends RecyclerView.Adapter<AdapterCachorros.CachorrosViewHolder> {

    private ArrayList<Dog> dogs;
    private Context c;
    private Click click;

    private int tempoDeAnimacao;

    private Texto texto;

    public AdapterCachorros(Context c, int tempoDeAnimacao, Click click) {

        dogs = new ArrayList<Dog>();
        this.c = c;

        this.tempoDeAnimacao = tempoDeAnimacao;
        this.texto = new Texto();

        this.click = click;
    }

    @NonNull
    @Override
    public AdapterCachorros.CachorrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(
                c).inflate(R.layout.item_dog,
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

                    click.clicou(dogs.get(getAdapterPosition()));
                }
            });


        }

        public TextView getTv() {
            return tv;
        }
    }
}
