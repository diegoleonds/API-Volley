package com.example.doge;

import java.util.ArrayList;

class Dog {

    private String raca;
    private boolean temSubRacas;

    public Dog(String raca) {

        this.raca = raca;
    }

    public Dog(String raca, boolean temSubRacas) {

        this.raca = raca;
        this.temSubRacas = temSubRacas;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public boolean isTemSubRacas() {
        return temSubRacas;
    }

    public void setTemSubRacas(boolean temSubRacas) {
        this.temSubRacas = temSubRacas;
    }
}
