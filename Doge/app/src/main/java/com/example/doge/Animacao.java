package com.example.doge;

import android.view.View;

import java.util.ArrayList;

public class Animacao {

    public void fade(View view, int time){

        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);

        view.animate()
                .alpha(1f)
                .setDuration(time)
                .setListener(null);
    }

    public void fade(ArrayList<View> views, int time, int decremento){

        for (View view : views) {

            fade(view, time);
            time -= decremento;
        }
    }
}
