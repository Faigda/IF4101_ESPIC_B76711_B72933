package com.example.if4101_espic_b76711_b72933;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Respuesta.ActualizarRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.LoginRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.PacienteRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.RegistroRespuesta;
import com.example.if4101_espic_b76711_b72933.Solicitud.BusquedaPaciente;
import com.example.if4101_espic_b76711_b72933.Solicitud.Paciente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatosActivity   extends AppCompatActivity {

    private EditText et_CedulaActualizar;
    private EditText et_NombreActualizar;
    private EditText et_ApellidosActualizar;
    private EditText et_TelefonoActualizar;
    private Spinner spn_EstadoCivilActualizar;
    private Spinner spn_TipoSangreActualizar;
    private EditText et_DomicilioActualizar;
    private String contrasenna;
    private Button btn_Actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        et_CedulaActualizar = findViewById(R.id.et_CedulaActualizar);
        et_CedulaActualizar.setEnabled(false);
        et_NombreActualizar = findViewById(R.id.et_NombreActualizar);
        et_NombreActualizar.setEnabled(false);
        et_ApellidosActualizar = findViewById(R.id.et_ApellidosActualizar);
        et_ApellidosActualizar.setEnabled(false);
        et_TelefonoActualizar = findViewById(R.id.et_TelefonoActualizar);
        spn_EstadoCivilActualizar = findViewById(R.id.spn_EstadoCivilActualizar);
        spn_TipoSangreActualizar = findViewById(R.id.spn_TiposSangreActualizar);
        spn_TipoSangreActualizar.setEnabled(false);
        et_DomicilioActualizar = findViewById(R.id.et_DomicilioActualizar);
        btn_Actualizar = findViewById(R.id.btn_Actualizar);

        ArrayAdapter<String> adapterEstadoCivil = new ArrayAdapter<>(this, R.layout.custom_spinner, getResources().getStringArray(R.array.array_EstadoCivil));
        adapterEstadoCivil.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spn_EstadoCivilActualizar.setAdapter(adapterEstadoCivil);

        ArrayAdapter<String> adapterTipoSangre = new ArrayAdapter<>(this, R.layout.custom_spinner, getResources().getStringArray(R.array.array_TiposSangre));
        adapterTipoSangre.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spn_TipoSangreActualizar.setAdapter(adapterTipoSangre);

        Bundle datos = getIntent().getExtras();

        Call<PacienteRespuesta> pacienteRespuestaCall = ESPIC_API.getSolicitudes().datos(datos.getInt("Cedula"));

        pacienteRespuestaCall.enqueue(new Callback<PacienteRespuesta>() {
            @Override
            public void onResponse(Call<PacienteRespuesta> call, Response<PacienteRespuesta> response) {
                if(response.isSuccessful()){
                    if(response.body().getCedula() == datos.getInt("Cedula")){
                        et_CedulaActualizar.setText(String.valueOf(response.body().getCedula()));
                        et_NombreActualizar.setText(response.body().getNombre());
                        et_ApellidosActualizar.setText(response.body().getApellido());
                        et_TelefonoActualizar.setText(response.body().getTelefono());
                        for (int i = 0 ; i<6; i++){
                            if(spn_EstadoCivilActualizar.getItemAtPosition(i).toString().equalsIgnoreCase(response.body().getEstado_Civil())){
                                spn_EstadoCivilActualizar.setSelection(i);
                                break;
                            }
                        }
                        for (int i = 0 ; i<9; i++){
                            if(spn_TipoSangreActualizar.getItemAtPosition(i).toString().equalsIgnoreCase(response.body().getTipo_Sangre())){
                                spn_TipoSangreActualizar.setSelection(i);
                                break;
                            }
                        }
                        et_DomicilioActualizar.setText(response.body().getDomicilio());
                        contrasenna = response.body().getContrasenna();
                    }else{
                        Toast.makeText(DatosActivity.this, "Ocurrió un error en la obtención de datos.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(DatosActivity.this, "Se ha producido un error obteniendo los datos (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PacienteRespuesta> call, Throwable t) {
                Toast.makeText(DatosActivity.this, "Se ha producido un error obteniendo los datos (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

        btn_Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_CedulaActualizar.getText().toString()) || TextUtils.isEmpty(et_NombreActualizar.getText().toString()) || TextUtils.isEmpty(et_ApellidosActualizar.getText().toString()) || TextUtils.isEmpty(et_TelefonoActualizar.getText().toString()) || TextUtils.isEmpty(et_DomicilioActualizar.getText().toString()) || spn_EstadoCivilActualizar.getSelectedItem().toString().equals("Estado Civil") || spn_TipoSangreActualizar.getSelectedItem().toString().equals("Tipo de Sangre")){
                    Toast.makeText(DatosActivity.this, "Todos los campos son obligatorios.", Toast.LENGTH_LONG).show();
                }else{
                    actualizar();
                }
            }
        });

    }

    public void actualizar(){
        Paciente actualizar = new Paciente();
        actualizar.setNombre(et_NombreActualizar.getText().toString());
        actualizar.setCedula(Integer.parseInt(et_CedulaActualizar.getText().toString()));
        actualizar.setApellido(et_ApellidosActualizar.getText().toString());
        actualizar.setTipo_Sangre(spn_TipoSangreActualizar.getSelectedItem().toString());
        actualizar.setEstado_Civil(spn_EstadoCivilActualizar.getSelectedItem().toString());
        actualizar.setTelefono(et_TelefonoActualizar.getText().toString());
        actualizar.setDomicilio(et_DomicilioActualizar.getText().toString());
        actualizar.setContrasenna(contrasenna);

        Call<ActualizarRespuesta> actualizarRespuestaCall = ESPIC_API.getSolicitudes().actualizar(actualizar);

        actualizarRespuestaCall.enqueue(new Callback<ActualizarRespuesta>() {
            @Override
            public void onResponse(Call<ActualizarRespuesta> call, Response<ActualizarRespuesta> response) {
                if(response.isSuccessful()){
                    finish();
                    Toast.makeText(DatosActivity.this, "Actualizado correctamente.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(DatosActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ActualizarRespuesta> call, Throwable t) {
                Toast.makeText(DatosActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

}
