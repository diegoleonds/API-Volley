package com.example.doge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Conexao conexao;
    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private DogDAO dogDAO;

    private TextView nomeUltimoDog;
    private CircleImageView fotoUltimoDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        adapterCachorros = new AdapterCachorros(this, 150);

        conexao = new Conexao(this);
        conexao.atualizarAdapter(adapterCachorros);

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        nomeUltimoDog = findViewById(R.id.nome_ultimo_dog);
        fotoUltimoDog = findViewById(R.id.foto_ultimo_dog);
    }

    @Override
    protected void onResume(){
        super.onResume();

        dogDAO = DogDAO.getDogDAO();
        dogDAO.mostraUltimoDog(nomeUltimoDog, fotoUltimoDog);
    }
}
