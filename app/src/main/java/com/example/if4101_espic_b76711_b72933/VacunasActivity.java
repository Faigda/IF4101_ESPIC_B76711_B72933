package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Adapter.VacunaAdapter;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VacunasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VacunaAdapter vacunaAdapter;

    private TextView tv_VacunaTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewVacunas);
        vacunaAdapter = new VacunaAdapter(this);
        recyclerView.setAdapter(vacunaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerVacunas();
    }

    private void obtenerVacunas(){

        tv_VacunaTitulo = (TextView) findViewById(R.id.tv_VacunaTitulo);

        Bundle datos = getIntent().getExtras();

        tv_VacunaTitulo.setText("Vacunas: " + datos.getInt("Cedula"));

        Call<ArrayList<Vacuna>> vacunasRespuestaCall = ESPIC_API.getSolicitudes().vacunas(datos.getInt("Cedula"));

        vacunasRespuestaCall.enqueue(new Callback<ArrayList<Vacuna>>() {
            @Override
            public void onResponse(Call<ArrayList<Vacuna>> call, Response<ArrayList<Vacuna>> response) {
                if(response.isSuccessful()){
                    ArrayList<Vacuna> listaVacunas = response.body();
                    if(listaVacunas.size() != 0){
                        vacunaAdapter.adicionarListaVacunas(listaVacunas, datos.getInt("Cedula"));
                    } else {
                        tv_VacunaTitulo.setText("No hay registros para este usuario.");
                    }
                } else {
                    Toast.makeText(VacunasActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vacuna>> call, Throwable t) {
                Toast.makeText(VacunasActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });
    }

}
