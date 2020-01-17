package com.example.doge.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doge.dados.Conexao;
import com.example.doge.R;
import com.example.doge.ui.uteis.Animacao;
import com.example.doge.ui.uteis.Texto;

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
        a.sumir();


        if (pai.equals("false")) {

            conexao.setImg(fotoDog, raca, "", a);

        } else {


        }

        texto = new Texto();
        texto.deixarPrimeiraLetaMaiuscula(nomeDog);
    }

}
