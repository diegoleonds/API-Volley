package com.example.doge;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CachorrinhoActivity extends AppCompatActivity {

    private TextView nomeDog;
    private ImageView fotoDog;

    private Conexao conexao;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.cachorrinho);

        nomeDog = (TextView) findViewById(R.id.nome_dog);
        fotoDog = (ImageView) findViewById(R.id.foto_dog);

        conexao = new Conexao(this);
        Bundle aux = getIntent().getExtras();
        String raca = aux.getString("raca", "null pointer");

        nomeDog.setText(raca);
        conexao.setImg(fotoDog, raca, "");
    }
}
