package com.example.doge;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class listaSubClasses extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private Conexao conexao;
    private String raca;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.lista_sub_racas);

        rv = findViewById(R.id.rv_sub_racas);
        adapterCachorros = new AdapterCachorros(this);

        Bundle aux = getIntent().getExtras();
        raca = aux.getString("raca");

        Log.e("Raça: ", raca);

        conexao = new Conexao(this);
        conexao.atualizarAdapter(adapterCachorros, raca);

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
