package com.example.doge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Conexao conexao;
    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private DogDAO dogDAO;

    private TextView nomeUltimoDog;
    private CircleImageView fotoUltimoDog;

    private Animacao a;
    private ArrayList<View> views;

    private Texto texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = new Animacao();

        rv = findViewById(R.id.rv);
        adapterCachorros = new AdapterCachorros(this, 150);

        conexao = new Conexao(this);
        conexao.atualizarAdapter(adapterCachorros);

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        nomeUltimoDog = findViewById(R.id.nome_ultimo_dog);
        fotoUltimoDog = findViewById(R.id.foto_ultimo_dog);

        views = new ArrayList<View>();

        views.add(nomeUltimoDog);
        views.add(fotoUltimoDog);
        views.add(rv);

        texto = new Texto();
    }

    @Override
    protected void onResume(){
        super.onResume();

        dogDAO = DogDAO.getDogDAO();
        dogDAO.mostraUltimoDog(nomeUltimoDog, fotoUltimoDog);

        texto.deixarPrimeiraLetaMaiuscula(nomeUltimoDog);

        a.fade(views, 650, 120);
    }
}
