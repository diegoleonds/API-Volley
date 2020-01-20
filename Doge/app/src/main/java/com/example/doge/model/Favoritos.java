package com.example.doge.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Favoritos {

    private String jsonFavoritos;
    private Context context;

    private final String name = "DOGS";
    private final String key = "FAVORITOS";

    public Favoritos(Context context){

        jsonFavoritos = "\"favoritos\":{";
        this.context = context;
    }

    public void addDog(Dog dog){

        jsonFavoritos += dog.toString() + ",";
    }

    public String getFavoritos(){

        return jsonFavoritos + "}";
    }

    public void salvar(){

        SharedPreferences.Editor editor = context.getSharedPreferences
                (name, MODE_PRIVATE).edit();

        editor.putString(key, getFavoritos());
        editor.commit();
    }

    public String getFavsDoJson(){

        String retorno = "";
        String aux = "";

        aux = context.getSharedPreferences
                (name, MODE_PRIVATE).getString(key, "");

        Log.e("json", aux);

        return aux;
    }

}
