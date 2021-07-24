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
import com.example.if4101_espic_b76711_b72933.Respuesta.LoginRespuesta;
import com.example.if4101_espic_b76711_b72933.Respuesta.RegistroRespuesta;
import com.example.if4101_espic_b76711_b72933.Solicitud.Paciente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity  extends AppCompatActivity {

    private EditText et_CedulaRegistro;
    private EditText et_NombreRegistro;
    private EditText et_ApellidosRegistro;
    private EditText et_TelefonoRegistro;
    private Spinner spn_EstadoCivilRegistro;
    private Spinner spn_TipoSangreRegistro;
    private EditText et_DomicilioRegistro;
    private EditText et_ContrasennaRegistro;
    private EditText et_ContrasennaConfirmarRegistro;
    private Button btn_Registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_CedulaRegistro = findViewById(R.id.et_CedulaRegistro);
        et_NombreRegistro = findViewById(R.id.et_NombreRegistro);
        et_ApellidosRegistro = findViewById(R.id.et_ApellidosRegistro);
        et_TelefonoRegistro = findViewById(R.id.et_TelefonoRegistro);
        spn_EstadoCivilRegistro = findViewById(R.id.spn_EstadoCivilRegistro);
        spn_TipoSangreRegistro = findViewById(R.id.spn_TiposSangreRegistro);
        et_DomicilioRegistro = findViewById(R.id.et_DomicilioRegistro);
        et_ContrasennaRegistro = findViewById(R.id.et_ContrasennaRegistro);
        et_ContrasennaConfirmarRegistro = findViewById(R.id.et_ContrasennaConfirmarRegistro);
        btn_Registrarse = findViewById(R.id.btn_RegistrarseRegistro);

        ArrayAdapter<String> adapterEstadoCivil = new ArrayAdapter<>(this, R.layout.custom_spinner, getResources().getStringArray(R.array.array_EstadoCivil));
        adapterEstadoCivil.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spn_EstadoCivilRegistro.setAdapter(adapterEstadoCivil);

        ArrayAdapter<String> adapterTipoSangre = new ArrayAdapter<>(this, R.layout.custom_spinner, getResources().getStringArray(R.array.array_TiposSangre));
        adapterTipoSangre.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spn_TipoSangreRegistro.setAdapter(adapterTipoSangre);

        btn_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_CedulaRegistro.getText().toString()) || TextUtils.isEmpty(et_NombreRegistro.getText().toString()) || TextUtils.isEmpty(et_ApellidosRegistro.getText().toString()) || TextUtils.isEmpty(et_TelefonoRegistro.getText().toString()) || TextUtils.isEmpty(et_DomicilioRegistro.getText().toString()) || TextUtils.isEmpty(et_ContrasennaRegistro.getText().toString()) || TextUtils.isEmpty(et_ContrasennaConfirmarRegistro.getText().toString()) || spn_EstadoCivilRegistro.getSelectedItem().toString().equals("Estado Civil") || spn_TipoSangreRegistro.getSelectedItem().toString().equals("Tipo de Sangre")){
                        Toast.makeText(RegistroActivity.this, "Todos los campos son obligatorios.", Toast.LENGTH_LONG).show();
                }else{
                    if(et_ContrasennaRegistro.getText().toString().equals(et_ContrasennaConfirmarRegistro.getText().toString())){
                        registrarse();
                    }else{
                        Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void registrarse(){
        Paciente registro = new Paciente();
        registro.setNombre(et_NombreRegistro.getText().toString());
        registro.setCedula(Integer.parseInt(et_CedulaRegistro.getText().toString()));
        registro.setApellido(et_ApellidosRegistro.getText().toString());
        registro.setTipo_Sangre(spn_TipoSangreRegistro.getSelectedItem().toString());
        registro.setEstado_Civil(spn_EstadoCivilRegistro.getSelectedItem().toString());
        registro.setTelefono(et_TelefonoRegistro.getText().toString());
        registro.setDomicilio(et_DomicilioRegistro.getText().toString());
        registro.setContrasenna(et_ContrasennaRegistro.getText().toString());

        Call<RegistroRespuesta> registroRespuestaCall = ESPIC_API.getSolicitudes().registro(registro);

        registroRespuestaCall.enqueue(new Callback<RegistroRespuesta>() {
            @Override
            public void onResponse(Call<RegistroRespuesta> call, Response<RegistroRespuesta> response) {
                if(response.isSuccessful()){
                    if(response.body().getRespuesta().equalsIgnoreCase("Registrado")){
                        mostrarMainView();
                        Toast.makeText(RegistroActivity.this, "Registrado correctamente.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RegistroActivity.this, "Este usuario ya existe.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(RegistroActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegistroRespuesta> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void mostrarMainView(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
