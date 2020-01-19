package com.example.doge.controller;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.doge.model.Dog;
import com.example.doge.model.Model;
import com.example.doge.model.ServerCallBack;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Controller {

    private Model model;
    private Context context;

    public Controller(Context context){

        this.context = context;
        model = new Model(context);
    }

    public void getDogs(final AdapterCachorros adapterCachorros){

        model.getDados(new ServerCallBack() {

            @Override
            public void onSucess(JSONObject result) {

                try {

                    JSONObject jsonObject = result.getJSONObject("message");
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
                        }
                    }

                    adapterCachorros.notifyDataSetChanged();

                } catch (JSONException e) {

                    Log.e("erro", e.toString());
                }
            }
        });
    }

    public void getSubDogs(final AdapterCachorros adapterCachorros, final String subDog){

        model.getDados(new ServerCallBack() {
            @Override
            public void onSucess(JSONObject result) {

                try {
                    JSONObject jsonObject = result.getJSONObject("message");
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
        });
    }

    public void setImg(final ImageView imageView, String raca, String subRaca,
                final Animacao a){

        String url = "https://dog.ceo/api/breed/";;
        String finalLinkImagem = "/images/random";

        if (subRaca.equals("")){

            url += raca + finalLinkImagem;

            /*
              private final String urlImg = "https://dog.ceo/api/breed/";
              private final String finalLinkImagem = "/images/random";
             */

        } else  {

            //https://dog.ceo/api/breed/australian/shepherd/images/random
            url += raca + "/" + subRaca + finalLinkImagem;

            Log.e("Link da subraça: ", url);
        }

        model.getImg(new ServerCallBack() {
            @Override
            public void onSucess(JSONObject result) {

                try {

                    String url = result.getString("message");
                    Log.e("url", url);

                    Picasso.get().load(url).
                            resize(300, 300).
                            into(imageView,
                                    new Callback.EmptyCallback(){

                                        @Override
                                        public void onSuccess(){

                                            //a.fade(a.getViews(), 300, 250);

                                        }
                                    });


                } catch (JSONException e) {

                    Log.e("erro", e.toString());
                }
            }
        }, url);
    }
}
