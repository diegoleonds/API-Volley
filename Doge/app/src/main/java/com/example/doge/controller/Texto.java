package com.example.doge.controller;

import android.widget.TextView;

public class Texto {

    public Texto(){

    }

    public void deixarPrimeiraLetaMaiuscula(TextView textView){

        String s = (String) textView.getText();
        s = s.substring(0,1).toUpperCase().concat(s.substring(1));

        textView.setText(s);
    }
}
