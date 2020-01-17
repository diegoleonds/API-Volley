package com.example.doge.dados;

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

    public Dog getUltimoDog() {
        return ultimoDog;
    }

    public void setUltimoDog(Dog ultimoDog) {
        this.ultimoDog = ultimoDog;
    }
}
