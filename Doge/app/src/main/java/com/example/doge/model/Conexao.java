package com.example.doge.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class Conexao {

    private final String url = "https://dog.ceo/api/breeds/list/all";
    private final String urlImg = "https://dog.ceo/api/breed/";
    private final String finalLinkImagem = "/images/random";

    private ArrayList<String> info;

    private RequestQueue queue;
    private Context context;

    public Conexao(Context context) {

        this.context = context;
        queue = Volley.newRequestQueue(context);

        info = new ArrayList<String>();
    }

    public void getDados(final ServerCallBack callBack) {

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        callBack.onSucess(response);
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

    public void getImg(final ServerCallBack callBack, String url){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        callBack.onSucess(response);
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

    public ArrayList<String> getInfo() {
        return info;
    }
}
