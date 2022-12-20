package com.example.ejercicio1ex.retrofitejercicio.conexiones;

import com.example.ejercicio1ex.retrofitejercicio.configuraciones.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {
    public static final String BASE_URL = "https://reqres.in";

    public static Retrofit getConexion() {
        return new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
