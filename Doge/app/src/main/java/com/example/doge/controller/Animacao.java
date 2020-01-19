package com.example.doge.controller;

import android.view.View;

import java.util.ArrayList;

public class Animacao {

    private ArrayList<View> views;
    private int time;

    public Animacao() {

        views = new ArrayList<View>();
        time = 300;
    }

    public void addView(View view) {

        this.views.add(view);
    }

    public void delView(View view) {

        this.views.remove(view);
    }

    public void fade(View view, int time) {

        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);

        view.animate()
                .alpha(1f)
                .setDuration(time)
                .setListener(null);
    }

    public void fade(ArrayList<View> views, int time, int incremento) {

        for (View view : views) {

            fade(view, time);
        }
    }

    public void sumir() {

        for (View view : this.views) {

            view.setVisibility(View.INVISIBLE);
        }
    }

    public void reaparecer() {

        for (View view : this.views) {

            view.setVisibility(View.VISIBLE);

            fade(view, time);
        }
    }

    public ArrayList<View> getViews() {
        return views;
    }

    public void setViews(ArrayList<View> views) {
        this.views = views;
    }
}
