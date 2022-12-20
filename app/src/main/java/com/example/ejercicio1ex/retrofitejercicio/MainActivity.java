package com.example.ejercicio1ex.retrofitejercicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.lights.LightsManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.example.ejercicio1ex.retrofitejercicio.adapters.UserAdapter;
import com.example.ejercicio1ex.retrofitejercicio.conexiones.ApiConexiones;
import com.example.ejercicio1ex.retrofitejercicio.conexiones.RetrofitObject;
import com.example.ejercicio1ex.retrofitejercicio.databinding.ActivityMainBinding;
import com.example.ejercicio1ex.retrofitejercicio.modelos.DataItem;
import com.example.ejercicio1ex.retrofitejercicio.modelos.Response;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contenedor;
    private Button btnPage1, btnPage2;

    private UserAdapter adapter;
    private RecyclerView.LayoutManager lm;

    private List<DataItem> users;

    private Retrofit retrofit;
    private ApiConexiones api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaVistas();
        //RecyclerView
        users = new ArrayList<>();
        adapter = new UserAdapter(users, R.layout.avatar_view_holder, this);
        lm = new LinearLayoutManager(this);
        contenedor.setLayoutManager(lm);
        contenedor.setAdapter(adapter);

        //RETROFIT
        retrofit = RetrofitObject.getConexion();
        api = retrofit.create(ApiConexiones.class);
        downloadUsers("1");
    }

    public void clickPaginas(View view){
        Button btn = (Button) view;
        String page = btn.getText().toString();
        downloadUsers(page);
    }

    private void downloadUsers(String page) {
        Call<Response> doGetUsers = api.getUsers(page);

        doGetUsers.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null) {
                    adapter.notifyItemRangeRemoved(0, users.size());
                    users.clear();
                    Response response1 = response.body();
                    ArrayList<DataItem> temp = (ArrayList<DataItem>) response1.getData();
                    users.addAll(temp);
                    adapter.notifyItemRangeInserted(0, temp.size());
                    if (response1.getPage() == 1) {
                        btnPage1.setEnabled(false);
                        btnPage2.setEnabled(true);
                    } else {
                        btnPage1.setEnabled(true);
                        btnPage2.setEnabled(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void inicializaVistas() {
        contenedor = findViewById(R.id.contenedor);
        btnPage1 = findViewById(R.id.btnPage1);
        btnPage2 = findViewById(R.id.btnPage2);
    }
}