package com.example.doge.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doge.ui.uteis.AdapterCachorros;
import com.example.doge.ui.uteis.Animacao;
import com.example.doge.dados.Conexao;
import com.example.doge.R;
import com.example.doge.ui.uteis.Texto;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class listaSubClassesActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private Conexao conexao;
    private String raca;

    private TextView nomePai;
    private CircleImageView imgPai;

    private Animacao a;
    private ArrayList<View> views;

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
        conexao.atualizarAdapter(adapterCachorros, raca);
        conexao.setImg(imgPai, raca, "");

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        a = new Animacao();
        views = new ArrayList<View>();

        views.add(nomePai);
        views.add(imgPai);
        views.add(rv);

        texto = new Texto();
    }

    @Override
    protected void onResume(){
        super.onResume();

        texto.deixarPrimeiraLetaMaiuscula(nomePai);

    }
}
