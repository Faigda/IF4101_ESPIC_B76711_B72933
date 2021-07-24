package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarVacuna;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleVacunaActivity extends AppCompatActivity {

    private TextView tv_Nombre;
    private TextView tv_Descripcion;
    private TextView tv_Fecha_Vacuna;
    private TextView tv_Fecha_Siguiente;
    private TextView tv_centro_Salud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallevacuna);

        tv_Nombre = findViewById(R.id.tv_NombreVacunaDetalle);
        tv_Descripcion = findViewById(R.id.tv_DescripcionVacunaDetalleFinal);
        tv_Fecha_Vacuna = findViewById(R.id.tv_FechaVacunaDetalle);
        tv_Fecha_Siguiente = findViewById(R.id.tv_FechaSiguienteDetalle);
        tv_centro_Salud = findViewById(R.id.tv_CentroSaludVacunaDetalle);

        Bundle datos = getIntent().getExtras();
        BuscarVacuna buscarVacuna = new BuscarVacuna();
        buscarVacuna.setId_Vacuna(datos.getInt("idVacuna"));
        buscarVacuna.setCedula(datos.getInt("Cedula"));

        Call<Vacuna> vacunaRespuestaCall = ESPIC_API.getSolicitudes().detalleVacunas(buscarVacuna);

        vacunaRespuestaCall.enqueue(new Callback<Vacuna>() {
            @Override
            public void onResponse(Call<Vacuna> call, Response<Vacuna> response) {
                if(response.isSuccessful()){
                        tv_Nombre.setText(response.body().getNombre());
                        tv_Descripcion.setText(response.body().getDescripcion());
                        tv_Fecha_Vacuna.setText(response.body().getFecha_Vacuna());
                        tv_Fecha_Siguiente.setText(response.body().getFecha_Siquiente());
                        tv_centro_Salud.setText(response.body().getCentro_Salud());
                }else{
                    Toast.makeText(DetalleVacunaActivity.this, "Se ha producido un error obteniendo los datos (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Vacuna> call, Throwable t) {
                Toast.makeText(DetalleVacunaActivity.this, "Se ha producido un error obteniendo los datos (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

}
