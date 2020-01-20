package com.example.doge.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.doge.R;
import com.example.doge.model.Dog;
import com.example.doge.model.Model;
import com.example.doge.model.ServerCallBack;
import com.example.doge.view.DogActivity;
import com.example.doge.view.ListaSubRacasActivity;
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
    private Texto texto;

    public Controller(Context context){

        this.context = context;
        model = new Model(context);
        texto = new Texto();
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

                        if (key.equals(subDog)) {

                            JSONArray raca = (JSONArray) jsonObject.get(key);

                            for (int i = 0; i < raca.length(); i++) {

                                Dog d = new Dog(raca.get(i).toString());
                                d.setPai(subDog);

                                adapterList.add(d);
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

    public void setImgFinal(final ImageView imageView, String pai, String raca,
                            final Animacao a){

        if (pai.equals("")){

            setImg(imageView, raca, "", a);

        } else {

            setImg(imageView, pai, raca, a);
        }
    }

    public void setImg(final ImageView imageView, String raca, String subRaca,
                final Animacao a){

        String url = "https://dog.ceo/api/breed/";;
        String finalLinkImagem = "/images/random";

        if (subRaca.equals("")){

            url += raca + finalLinkImagem;

        } else  {

            url += raca + "/" + subRaca + finalLinkImagem;
            Log.e("Link da subraça: ", url);

        }

        model.getImg(new ServerCallBack() {
            @Override
            public void onSucess(JSONObject result) {

                try {

                    String url = result.getString("message");

                    Picasso.get().load(url).
                            resize(300, 300).
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
        }, url);
    }

    public void setUltimoDogNaTela(ImageView imageView, Animacao a, TextView textView){

        Dog aux = Model.ultimoDog;

        if (aux != null){

            if (aux.getPai().equals("")){

                setImg(imageView, aux.getRaca(), "", a);

            } else {

                setImg(imageView, aux.getPai(), aux.getRaca(), a);
            }

            textView.setText(aux.getRaca());
            texto.deixarPrimeiraLetaMaiuscula(textView);
        }
    }

    public Click clickMostraDog(final Context context){

        return new Click() {
            @Override
            public void clicou(Dog dog) {

                if (dog.isTemSubRacas()){

                    comSubRaca(context, dog);

                } else {

                    semSubRaca(context, dog);
                }
            }
        };
    }

    public Click clickSemSubRaca(final Context context){

        return new Click() {
            @Override
            public void clicou(Dog dog) {

                semSubRaca(context, dog);
            }
        };
    }

    public void addFavoritos(final ImageButton imageButton){

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageButton.setColorFilter(ContextCompat.getColor(context, R.color.corFav),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        });
    }

    private void semSubRaca(Context context, Dog dog){

        Bundle b = new Bundle();

        b.putString("raca", dog.getRaca());
        b.putString("pai", dog.getPai());

        Intent i = new Intent(context,
                DogActivity.class);

        i.putExtras(b);

        context.startActivity(i);
    }

    private void comSubRaca(Context context, Dog dog){

        Bundle b = new Bundle();
        b.putString("raca", dog.getRaca());

        Intent i = new Intent(context,
                ListaSubRacasActivity.class);

        i.putExtras(b);

        context.startActivity(i);
    }

    public void setUltimoDog(Dog ultimoDog){ Model.ultimoDog = ultimoDog; }

    public Dog getUltimoDog(){ return Model.ultimoDog; }
}
