package com.example.doge.model;

import android.content.Context;

import com.example.doge.controller.AdapterCachorros;

public class Model {

    public static Dog ultimoDog;
    private Conexao conexao;

    public Model(Context context) {

        this.conexao = new Conexao(context);
        ultimoDog = null;
    }

    public void getDados(ServerCallBack serverCallBack){

        conexao.getDados(serverCallBack);
    }

    public void getImg(ServerCallBack serverCallBack, String url){

        conexao.getImg(serverCallBack, url);
    }


}
