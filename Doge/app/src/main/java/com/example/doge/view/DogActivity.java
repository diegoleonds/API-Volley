package com.example.doge.view;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doge.controller.Controller;
import com.example.doge.R;
import com.example.doge.controller.Animacao;
import com.example.doge.controller.Texto;
import com.example.doge.model.Dog;

import de.hdodenhof.circleimageview.CircleImageView;

public class DogActivity extends AppCompatActivity {

    private TextView nomeDog;
    private CircleImageView fotoDog;
    private ImageButton btnFav;

    private Animacao a;

    private Texto texto;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.dog);

        nomeDog = findViewById(R.id.nome_dog);
        fotoDog = findViewById(R.id.foto_dog);
        btnFav = findViewById(R.id.fav_dog);

        Bundle aux = getIntent().getExtras();

        String raca = aux.getString("raca");
        String pai = aux.getString("pai", "");

        nomeDog.setText(raca);

        Controller c = new Controller(this);
        Animacao a = new Animacao();

        c.setUltimoDog(new Dog(raca, pai));
        c.setImgFinal(fotoDog, pai, raca, new Animacao());
        c.addFavoritos(btnFav);

        texto = new Texto();
        texto.deixarPrimeiraLetaMaiuscula(nomeDog);

    }

}
