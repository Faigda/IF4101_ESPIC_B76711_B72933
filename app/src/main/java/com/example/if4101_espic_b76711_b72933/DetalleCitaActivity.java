package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Respuesta.Cita;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarCita;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarVacuna;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCitaActivity extends AppCompatActivity {

    private TextView tv_Fecha_Cita;
    private TextView tv_Centro_Salud_Cita;
    private TextView tv_Especialidad_Cita;
    private TextView tv_Detalle_Cita;
    private TextView tv_Medico_Cita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallecita);

        tv_Fecha_Cita = findViewById(R.id.tv_FechaCitaDetalle);
        tv_Centro_Salud_Cita = findViewById(R.id.tv_CentroSaludCitaDetalle);
        tv_Especialidad_Cita = findViewById(R.id.tv_EspecialidadDetalle);
        tv_Detalle_Cita = findViewById(R.id.tv_Detalle);
        tv_Medico_Cita = findViewById(R.id.tv_MedicoDetalle);

        Bundle datos = getIntent().getExtras();
        BuscarCita buscarCita = new BuscarCita();
        buscarCita.setId_Cita(datos.getInt("idCita"));
        buscarCita.setCedula(datos.getInt("Cedula"));

        Call<Cita> citaRespuestaCall = ESPIC_API.getSolicitudes().detalleCitas(buscarCita);

        citaRespuestaCall.enqueue(new Callback<Cita>() {
            @Override
            public void onResponse(Call<Cita> call, Response<Cita> response) {
                if(response.isSuccessful()){
                    tv_Fecha_Cita.setText(response.body().getFecha());
                    tv_Centro_Salud_Cita.setText(response.body().getCentro_Salud());
                    tv_Especialidad_Cita.setText(response.body().getEspecialidad());
                    tv_Detalle_Cita.setText(response.body().getDetalle());
                    tv_Medico_Cita.setText(response.body().getCodigoc_Medico() + " - " + response.body().getNombre());
                }else{
                    Toast.makeText(DetalleCitaActivity.this, "Se ha producido un error obteniendo los datos (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cita> call, Throwable t) {
                Toast.makeText(DetalleCitaActivity.this, "Se ha producido un error obteniendo los datos (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

}
