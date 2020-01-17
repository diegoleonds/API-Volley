package com.example.doge;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CachorrinhoActivity extends AppCompatActivity {

    private TextView nomeDog;
    private CircleImageView fotoDog;

    private Conexao conexao;

    private Animacao a;
    private ArrayList<View> views;

    private Texto texto;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.cachorrinho);

        nomeDog = findViewById(R.id.nome_dog);
        fotoDog = findViewById(R.id.foto_dog);

        conexao = new Conexao(this);
        Bundle aux = getIntent().getExtras();

        String raca = aux.getString("raca");
        String pai = aux.getString("pai", "false");

        if (pai.equals("false")) {

        nomeDog.setText(raca);
        conexao.setImg(fotoDog, raca, "");

        } else {

            nomeDog.setText(raca);
            conexao.setImg(fotoDog, pai, raca);

        }

        a = new Animacao();
        views = new ArrayList<View>();

        views.add(fotoDog);
        views.add(nomeDog);

        texto = new Texto();
    }

    @Override
    protected void onResume(){
        super.onResume();

        texto.deixarPrimeiraLetaMaiuscula(nomeDog);
        a.fade(views, 650, 120);
    }
}
