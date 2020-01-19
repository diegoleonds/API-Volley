package com.example.doge.model;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doge.R;
import com.example.doge.controller.AdapterCachorros;
import com.example.doge.controller.Animacao;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Conexao {

    private final String url = "https://dog.ceo/api/breeds/list/all";
    private final String urlImg = "https://dog.ceo/api/breed/";
    private final String finalLinkImagem = "/images/random";

    private ArrayList<String> dados;

    private RequestQueue queue;
    private Context context;

    public Conexao(Context context) {

        this.context = context;
        queue = Volley.newRequestQueue(context);

        dados = new ArrayList<String>();
    }

    public void getDogs(final AdapterCachorros adapterCachorros) {

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("message");
                            Iterator<String> keys = jsonObject.keys();

                            ArrayList<Dog> adapterList =
                                    adapterCachorros.getDogs();

                            while (keys.hasNext()) {

                                String key = keys.next();

                                if (jsonObject.get(key) instanceof JSONArray) {

                                    JSONArray raca = (JSONArray) jsonObject.get(key);

                                    Dog aux = new Dog(key,
                                            raca.length() > 0);

                                    adapterList.add(aux);

                                    Log.e("Key: ", key + ", " + aux.isTemSubRacas());
                                }
                            }

                            adapterCachorros.notifyDataSetChanged();

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

    public void getSubDogs(final AdapterCachorros adapterCachorros, final String subDog) {

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("message");
                            Iterator<String> keys = jsonObject.keys();

                            ArrayList<Dog> adapterList =
                                    adapterCachorros.getDogs();

                            while (keys.hasNext()) {

                                String key = keys.next();
                                Log.e("key: ", key);

                                if (key.equals(subDog)) {

                                    JSONArray raca = (JSONArray) jsonObject.get(key);

                                    for (int i = 0; i < raca.length(); i++) {

                                        Dog d = new Dog(raca.get(i).toString());
                                        d.setPai(subDog);

                                        adapterList.add(d);
                                        Log.e("Subraça: ", adapterList.get(i).getRaca());
                                    }
                                }
                            }
                            adapterCachorros.notifyDataSetChanged();

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

    public void setImg(final ImageView imageView, String raca, String subraca){

        String url = urlImg;

        if (subraca.equals("")){

            url += raca + finalLinkImagem;

            /*
              private final String urlImg = "https://dog.ceo/api/breed/";
              private final String finalLinkImagem = "/images/random";
             */

        } else  {

            //https://dog.ceo/api/breed/australian/shepherd/images/random
            url += raca + "/" + subraca + finalLinkImagem;

            Log.e("Link da subraça: ", url);
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String url = response.getString("message");

                            Picasso.get().load(url).
                                    resize(300, 300).
                                    error(R.drawable.crashou).
                                    into(imageView,
                                    new Callback.EmptyCallback(){

                                        @Override
                                        public void onSuccess(){

                                        }
                                    });


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

    public void setImg(final ImageView imageView, String raca, String subraca,
                            final Animacao a){

        String url = urlImg;

        if (subraca.equals("")){

            url += raca + finalLinkImagem;

            /*
              private final String urlImg = "https://dog.ceo/api/breed/";
              private final String finalLinkImagem = "/images/random";
             */

        } else  {

            //https://dog.ceo/api/breed/australian/shepherd/images/random
            url += raca + "/" + subraca + finalLinkImagem;

            Log.e("Link da subraça: ", url);
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String url = response.getString("message");

                            Picasso.get().load(url).
                                    resize(300, 300).
                                    into(imageView,
                                            new Callback.EmptyCallback(){

                                                @Override
                                                public void onSuccess(){

                                                    //a.fade(a.getViews(), 300, 250);
                                                    a.reaparecer();
                                                }
                                            });


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

    public ArrayList<String> getDogs() {
        return dados;
    }
}
