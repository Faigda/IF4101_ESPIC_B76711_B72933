package com.example.if4101_espic_b76711_b72933;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpcionesActivity extends AppCompatActivity {

    private Button btn_Datos;
    private Button btn_Vacunas;
    private Button btn_Citas;
    private Button btn_Alergias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        btn_Datos = findViewById(R.id.btn_Datos);
        btn_Vacunas = findViewById(R.id.btn_Vacunas);
        btn_Citas = findViewById(R.id.btn_Citas);
        btn_Alergias = findViewById(R.id.btn_Alergias);


        Bundle datos = getIntent().getExtras();

        TextView textView = findViewById(R.id.textView);
        textView.setText("Consultas: " + datos.getInt("Cedula"));

        btn_Datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarActualizarDatosView();
            }
        });

        btn_Vacunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVacunasView();
            }
        });

        btn_Citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCitasView();
            }
        });

        btn_Alergias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAlergiasView();
            }
        });

    }

    public void mostrarActualizarDatosView(){
        Intent intent = new Intent(this, DatosActivity.class);
        Bundle datos = getIntent().getExtras();
        intent.putExtra("Cedula", datos.getInt("Cedula"));

        startActivity(intent);
    }

    public void mostrarVacunasView(){
        Intent intent = new Intent(this, VacunasActivity.class);
        Bundle datos = getIntent().getExtras();
        intent.putExtra("Cedula", datos.getInt("Cedula"));

        startActivity(intent);
    }

    public void mostrarCitasView(){
        Intent intent = new Intent(this, CitasActivity.class);
        Bundle datos = getIntent().getExtras();
        intent.putExtra("Cedula", datos.getInt("Cedula"));

        startActivity(intent);
    }

    public void mostrarAlergiasView(){
        Intent intent = new Intent(this, AlergiasActivity.class);
        Bundle datos = getIntent().getExtras();
        intent.putExtra("Cedula", datos.getInt("Cedula"));

        startActivity(intent);
    }

}
