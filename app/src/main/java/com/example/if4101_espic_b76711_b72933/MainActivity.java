package com.example.if4101_espic_b76711_b72933;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.if4101_espic_b76711_b72933.API.ESPIC_API;
import com.example.if4101_espic_b76711_b72933.Solicitud.Login;
import com.example.if4101_espic_b76711_b72933.Respuesta.LoginRespuesta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText et_Cedula;
    private EditText et_Contrasenna;
    private Button btn_IniciarSesion;
    private Button btn_Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Cedula = findViewById(R.id.et_Cedula);
        et_Contrasenna = findViewById(R.id.et_Contrasenna);
        btn_IniciarSesion = findViewById(R.id.btn_IniciarSesion);
        btn_Registro = findViewById(R.id.btn_Registrarse);

        btn_IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_Cedula.getText().toString()) || TextUtils.isEmpty(et_Contrasenna.getText().toString())){
                    Toast.makeText(MainActivity.this, "Ingrese una Cédula o Contraseña válidas", Toast.LENGTH_LONG).show();
                }else{
                    iniciarSesion();
                }
            }
        });

        btn_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });

    }

    public void iniciarSesion(){
        Login login = new Login();
        login.setCedula(Integer.parseInt(et_Cedula.getText().toString()));
        login.setContrasenna(et_Contrasenna.getText().toString());

        Call<LoginRespuesta> loginRespuestaCall = ESPIC_API.getSolicitudes().login(login);

        loginRespuestaCall.enqueue(new Callback<LoginRespuesta>() {
            @Override
            public void onResponse(Call<LoginRespuesta> call, Response<LoginRespuesta> response) {
                if(response.isSuccessful()){
                    if(response.body().getEstado().equalsIgnoreCase("Logueado")){
                        mostrarOpcionesView();
                    }else{
                        Toast.makeText(MainActivity.this, "Cédula o contraseña incorrecta.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Se ha producido un error (onResponse).", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRespuesta> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Se ha producido un error (onFailure).", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void registrarse(){

        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);

    }

    public void mostrarOpcionesView(){
        Intent intent = new Intent(this, OpcionesActivity.class);
        intent.putExtra("Cedula", Integer.parseInt(et_Cedula.getText().toString()));
        et_Cedula.setText("");
        et_Contrasenna.setText("");
        startActivity(intent);
    }

}