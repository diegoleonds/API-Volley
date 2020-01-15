package com.example.doge;

import android.content.Context;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Conexao {

    private final String url = "https://dog.ceo/api/breeds/list/all";
    private ArrayList<String> dados;

    private RequestQueue queue;
    private Context context;


    public Conexao(Context context) {

        this.context = context;
        queue = Volley.newRequestQueue(context);

        dados = new ArrayList<String>();
    }

    public void passarParaTela(final TextView tv) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tv.setText("Response is: " + response.toString());

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                tv.setText("Deu ruim");
            }
        });

        queue.add(stringRequest);
    }

    public void atualizarAdapter(final AdapterCachorros adapterCachorros) {

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            /*

                            ArrayList<String> auxList = new ArrayList<String>();
                            JSONArray aux = response.getJSONArray("message");
                             */

                            JSONObject jsonObject = response.getJSONObject("message");
                            Iterator<String> keys = jsonObject.keys();

                            while (keys.hasNext()){

                                String key = keys.next();

                                if (jsonObject.get(key) instanceof JSONArray){

                                    JSONArray raca = (JSONArray) jsonObject.get(key);
                                    adapterCachorros.getCachorrinhos().add(new Cachorrinho(key));

                                    Log.e("Key: ", key);

                                    if (raca.length() > 0){

                                        for (int i = 0; i < raca.length(); i++)

                                            adapterCachorros.getCachorrinhos().
                                                    add(new Cachorrinho(raca.get(i).toString()));
                                    }

                                }
                            }

                            adapterCachorros.notifyDataSetChanged();

                            /*
                            for (int i = 0; i < aux.length(); i++) {

                                adapterCachorros.getCachorrinhos().
                                        add(new Cachorrinho(aux.get(i).toString()));

                                //Log.e("Raças: ", cachorrinhos.get(i).toString());
                            }

                            adapterCachorros.notifyDataSetChanged();
                             */

                        } catch (JSONException e) {

                            Log.e("erro", e.toString());
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                        Log.e("erro", error.toString());
                    }
                });

        queue.add(jsonObjectRequest);
    }

    private String capitalize(String s) {

        if (s == null || s.isEmpty())
            return s;

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public ArrayList<String> getDados() {
        return dados;
    }
}
