package com.example.if4101_espic_b76711_b72933;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Respuesta.Alergia;
import com.example.if4101_espic_b76711_b72933.Solicitud.BuscarAlergia;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleAlergiaActivity extends AppCompatActivity {

    private TextView tv_Nombre;
    private TextView tv_Descripcion;
    private TextView tv_Fecha;
    private TextView tv_Centro_Salud;
    private TextView tv_Medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallealergia);

        tv_Nombre = findViewById(R.id.tv_NombreAlergiaDetalle);
        tv_Descripcion = findViewById(R.id.tv_DescripcionAlergiaDetalleFinal);
        tv_Fecha = findViewById(R.id.tv_FechaAlergiaDetalle);
        tv_Centro_Salud = findViewById(R.id.tv_CentroSaludAlergiaDetalle);
        tv_Medicamento = findViewById(R.id.tv_MedicamentoAlergiaDetalle);

        Bundle datos = getIntent().getExtras();
        BuscarAlergia buscarAlergia = new BuscarAlergia();
        buscarAlergia.setId_Alergia(datos.getInt("idAlergia"));
        buscarAlergia.setCedula(datos.getInt("Cedula"));

        Call<Alergia> alergiaRespuestaCall = ESPIC_API.getSolicitudes().detalleAlergias(buscarAlergia);

        alergiaRespuestaCall.enqueue(new Callback<Alergia>() {
            @Override
            public void onResponse(Call<Alergia> call, Response<Alergia> response) {
                if(response.isSuccessful()){
                    tv_Nombre.setText(response.body().getNombre());
                    tv_Descripcion.setText(response.body().getDescripcion());
                    tv_Fecha.setText(response.body().getFecha());
                    tv_Centro_Salud.setText(response.body().getCentro_Salud());
                    tv_Medicamento.setText(response.body().getMedicamento());
                }else{
                    Toast.makeText(DetalleAlergiaActivity.this, "Se ha producido un error obteniendo los datos (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Alergia> call, Throwable t) {
                Toast.makeText(DetalleAlergiaActivity.this, "Se ha producido un error obteniendo los datos (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

}
