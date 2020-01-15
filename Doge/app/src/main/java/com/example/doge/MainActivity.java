package com.example.doge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Conexao conexao;
    private RecyclerView rv;
    private AdapterCachorros ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        ac = new AdapterCachorros(this);

        conexao = new Conexao(this);
        conexao.atualizarAdapter(ac);

        rv.setAdapter(ac);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

    }
}
