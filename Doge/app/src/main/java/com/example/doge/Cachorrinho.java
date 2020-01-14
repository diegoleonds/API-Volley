package com.example.doge;

public class Cachorrinho {

    private String raca;
    private String[] subracas;

    public Cachorrinho(String raca){

        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString(){

        return this.raca + "a";
    }
}
