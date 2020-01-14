package com.example.doge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Conexao conexao;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexao = new Conexao(this);

        final ArrayList<Cachorrinho> cachorrinhos = new ArrayList<Cachorrinho>();
        conexao.passarParaArray(cachorrinhos);

        Log.e("Raças: ", cachorrinhos.get(0).toString());
    }
}
