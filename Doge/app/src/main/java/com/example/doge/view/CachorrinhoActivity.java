package com.example.doge.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doge.controller.Controller;
import com.example.doge.model.Conexao;
import com.example.doge.R;
import com.example.doge.controller.Animacao;
import com.example.doge.controller.Texto;

import de.hdodenhof.circleimageview.CircleImageView;

public class CachorrinhoActivity extends AppCompatActivity {

    private TextView nomeDog;
    private CircleImageView fotoDog;

    private Conexao conexao;

    private Animacao a;

    private Texto texto;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.cachorrinho);

        nomeDog = findViewById(R.id.nome_dog);
        fotoDog = findViewById(R.id.foto_dog);

        Bundle aux = getIntent().getExtras();

        String raca = aux.getString("raca");
        String pai = aux.getString("pai", "false");

        conexao = new Conexao(this);
        a = new Animacao();

        a.addView(fotoDog);
        a.addView(nomeDog);

        nomeDog.setText(raca);

        Controller c = new Controller(this);

        if (pai.equals("false")) {

            c.setImg(fotoDog, raca, "", a);

        } else {

            c.setImg(fotoDog, pai, raca, a);
        }

        texto = new Texto();
        texto.deixarPrimeiraLetaMaiuscula(nomeDog);
    }

}
