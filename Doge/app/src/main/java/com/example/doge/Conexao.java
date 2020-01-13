package com.example.doge;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Conexao {

    private final String url = "https://dog.ceo/api/breeds/list/all";
    private RequestQueue queue;
    private Context context;


    public Conexao(Context context) {

        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void passarParaTela(final TextView tv) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                       tv.setText("Response is: "+ response.substring(0, 500));

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                tv.setText("Deu ruim");
            }
        });

        queue.add(stringRequest);
    }

}
