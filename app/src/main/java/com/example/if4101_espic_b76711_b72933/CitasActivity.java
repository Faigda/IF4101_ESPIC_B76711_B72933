package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Adapter.CitaAdapter;
import com.example.if4101_espic_b76711_b72933.Respuesta.Cita;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CitaAdapter citaAdapter;

    private TextView tv_CitaTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCitas);
        citaAdapter = new CitaAdapter(this);
        recyclerView.setAdapter(citaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerCitas();
    }

    private void obtenerCitas(){

        tv_CitaTitulo = (TextView) findViewById(R.id.tv_CitasTitulo);

        Bundle datos = getIntent().getExtras();

        tv_CitaTitulo.setText("Citas: " + datos.getInt("Cedula"));

        Call<ArrayList<Cita>> citasRespuestaCall = ESPIC_API.getSolicitudes().citas(datos.getInt("Cedula"));

        citasRespuestaCall.enqueue(new Callback<ArrayList<Cita>>() {
            @Override
            public void onResponse(Call<ArrayList<Cita>> call, Response<ArrayList<Cita>> response) {
                if(response.isSuccessful()){
                    ArrayList<Cita> listaCitas = response.body();
                    if(listaCitas.size() != 0){
                        citaAdapter.adicionarListaCitas(listaCitas, datos.getInt("Cedula"));
                    } else {
                        tv_CitaTitulo.setText("No hay registros para este usuario.");
                    }

                } else {
                    Toast.makeText(CitasActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Cita>> call, Throwable t) {
                Toast.makeText(CitasActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });
    }
}
