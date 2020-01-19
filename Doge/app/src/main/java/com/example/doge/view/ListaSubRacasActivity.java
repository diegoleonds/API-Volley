package com.example.doge.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doge.controller.AdapterCachorros;
import com.example.doge.controller.Animacao;
import com.example.doge.controller.Controller;
import com.example.doge.model.Conexao;
import com.example.doge.R;
import com.example.doge.controller.Texto;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaSubRacasActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private Conexao conexao;
    private String raca;

    private TextView nomePai;
    private CircleImageView imgPai;

    private ArrayList<View> views;

    private Controller controller;
    private Texto texto;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.lista_sub_racas);

        rv = findViewById(R.id.rv_sub_racas);
        nomePai = findViewById(R.id.nome_ultimo_dog);
        imgPai = findViewById(R.id.foto_pai);

        adapterCachorros = new AdapterCachorros(this, 600);

        Bundle aux = getIntent().getExtras();
        raca = aux.getString("raca");

        nomePai.setText(raca);

        conexao = new Conexao(this);
        controller = new Controller(this);

        controller.getSubDogs(adapterCachorros, raca);
        controller.setImg(imgPai, raca, "", new Animacao());

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        texto = new Texto();
    }

    @Override
    protected void onResume(){
        super.onResume();

        texto.deixarPrimeiraLetaMaiuscula(nomePai);

    }
}
