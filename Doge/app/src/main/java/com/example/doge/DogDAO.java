package com.example.doge;

import android.content.Context;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class DogDAO {

    private static DogDAO dogDAO;
    private Dog ultimoDog;

    private DogDAO() {

    }

    public static DogDAO getDogDAO() {

        if (dogDAO == null)
            dogDAO = new DogDAO();

        return dogDAO;
    }

    public void mostraUltimoDog(TextView textView, CircleImageView circleImageView) {

        if (ultimoDog != null) {

            setNome(textView);
            setImg(circleImageView, ultimoDog);
        }
    }

    private void setNome(TextView textView) {

        textView.setText(ultimoDog.getRaca());
    }

    private void setImg(CircleImageView circleImageView, Dog dog) {

        Conexao conexao = new Conexao(circleImageView.getContext());

        if (temSubRaca(dog) && temPai(dog)) {

            conexao.setImg(circleImageView, dog.getPai(), dog.getRaca());

        } else {

            conexao.setImg(circleImageView, dog.getRaca(), "");
        }
    }

    public boolean temSubRaca(Dog dog) {

        return dog.isTemSubRacas();
    }

    public  boolean temPai(Dog dog){ return dog.temPai(); }

    public Dog getUltimoDog() {
        return ultimoDog;
    }

    public void setUltimoDog(Dog ultimoDog) {
        this.ultimoDog = ultimoDog;
    }
}
