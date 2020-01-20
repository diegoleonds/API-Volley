package com.example.doge.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doge.controller.Controller;
import com.example.doge.R;
import com.example.doge.controller.AdapterCachorros;
import com.example.doge.controller.Animacao;
import com.example.doge.controller.Texto;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterCachorros adapterCachorros;
    private Controller controller;

    private TextView nomeUltimoDog;
    private CircleImageView fotoUltimoDog;

    private Animacao a;
    private ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        controller = new Controller(this);

        adapterCachorros = new AdapterCachorros(this, 150,
                controller.clickMostraDog(this));

        rv.setAdapter(adapterCachorros);
        rv.setLayoutManager(new LinearLayoutManager(this));

        nomeUltimoDog = findViewById(R.id.nome_ultimo_dog);
        fotoUltimoDog = findViewById(R.id.foto_ultimo_dog);

        a = new Animacao();

        controller.getDogs(adapterCachorros);
        controller.setUltimoDogNaTela(fotoUltimoDog, a, nomeUltimoDog);
    }

    @Override
    protected void onResume(){
        super.onResume();

        controller.setUltimoDogNaTela(fotoUltimoDog, a, nomeUltimoDog);
    }

}
