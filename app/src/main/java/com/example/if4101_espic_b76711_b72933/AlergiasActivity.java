package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Adapter.AlergiaAdapter;
import com.example.if4101_espic_b76711_b72933.Adapter.VacunaAdapter;
import com.example.if4101_espic_b76711_b72933.Respuesta.Alergia;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlergiasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlergiaAdapter alergiaAdapter;

    private TextView tv_AlergiaTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alergias);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAlergias);
        alergiaAdapter = new AlergiaAdapter(this);
        recyclerView.setAdapter(alergiaAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        obtenerVacunas();
    }

    private void obtenerVacunas(){

        tv_AlergiaTitulo = (TextView) findViewById(R.id.tv_AlergiaTitulo);

        Bundle datos = getIntent().getExtras();

        tv_AlergiaTitulo.setText("Alergias: " + datos.getInt("Cedula"));

        Call<ArrayList<Alergia>> alergiasRespuestaCall = ESPIC_API.getSolicitudes().alergias(datos.getInt("Cedula"));

        alergiasRespuestaCall.enqueue(new Callback<ArrayList<Alergia>>() {
            @Override
            public void onResponse(Call<ArrayList<Alergia>> call, Response<ArrayList<Alergia>> response) {
                if(response.isSuccessful()){
                    ArrayList<Alergia> listaAlergias = response.body();
                    if(listaAlergias.size() != 0){
                        alergiaAdapter.adicionarListaAlergias(listaAlergias, datos.getInt("Cedula"));
                    } else {
                        tv_AlergiaTitulo.setText("No hay registros para este usuario.");
                    }
                } else {
                    Toast.makeText(AlergiasActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Alergia>> call, Throwable t) {
                Toast.makeText(AlergiasActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });
    }
}
