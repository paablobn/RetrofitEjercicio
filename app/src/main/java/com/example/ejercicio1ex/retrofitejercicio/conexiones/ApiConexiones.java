package com.example.ejercicio1ex.retrofitejercicio.conexiones;


import com.example.ejercicio1ex.retrofitejercicio.modelos.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {
    @GET("/api/users?")
    Call<Response> getUsers(@Query("page") String pagina);
}
