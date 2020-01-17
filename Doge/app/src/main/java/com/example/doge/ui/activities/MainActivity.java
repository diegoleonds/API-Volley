package com.example.doge.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doge.dados.Conexao;
import com.example.doge.dados.Dog;
import com.example.doge.dados.DogDAO;
import com.example.doge.R;
import com.example.doge.ui.uteis.AdapterCachorros;
import com.example.doge.ui.uteis.Animacao;
import com.example.doge.ui.uteis.Texto;

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
        Dog d = dogDAO.getUltimoDog();



        texto.deixarPrimeiraLetaMaiuscula(nomeUltimoDog);

    }
}
