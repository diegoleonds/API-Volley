package com.example.doge.model;

import android.content.Context;

import com.example.doge.controller.AdapterCachorros;

public class Model {

    private Conexao conexao;

    public Model(Context context) {

        this.conexao = new Conexao(context);
    }

    public void getDados(ServerCallBack serverCallBack){

        conexao.getDados(serverCallBack);
    }

    public void getImg(ServerCallBack serverCallBack, String url){

        conexao.getImg(serverCallBack, url);
    }
}
