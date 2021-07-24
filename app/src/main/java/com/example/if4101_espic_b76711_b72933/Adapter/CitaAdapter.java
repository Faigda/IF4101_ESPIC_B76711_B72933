package com.example.if4101_espic_b76711_b72933.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if4101_espic_b76711_b72933.DetalleCitaActivity;
import com.example.if4101_espic_b76711_b72933.MainActivity;
import com.example.if4101_espic_b76711_b72933.R;
import com.example.if4101_espic_b76711_b72933.Respuesta.Cita;

import java.util.ArrayList;

public class CitaAdapter  extends RecyclerView.Adapter<CitaAdapter.ViewHolder>{

    private ArrayList<Cita> dataset;

    private Context context;

    private View.OnClickListener listener;

    private int cedula;

    public CitaAdapter(Context context){
        dataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitaAdapter.ViewHolder holder, int position) {
        Cita cita = dataset.get(position);
        holder.fechaCita.setText(cita.getFecha());
        holder.detalleCita.setText(cita.getCentro_Salud());

        holder.setOnclickListener(cita.getId_Cita());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaCitas(ArrayList<Cita> listaCitas, int cedula) {
        dataset.addAll(listaCitas);
        this.cedula = cedula;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView fechaCita;
        public TextView detalleCita;
        public ConstraintLayout contenedor;
        public int idCita;

        public ViewHolder(View itemView) {
            super(itemView);

            fechaCita = (TextView) itemView.findViewById(R.id.tv_NombreLista);
            detalleCita = (TextView) itemView.findViewById(R.id.tv_DetalleLista);
            contenedor = (ConstraintLayout) itemView.findViewById(R.id.cl_lista);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetalleCitaActivity.class);
            intent.putExtra("idCita", this.idCita);
            intent.putExtra("Cedula", cedula);
            view.getContext().startActivity(intent);

        }

        public void setOnclickListener(int idVacuna){
            contenedor.setOnClickListener(this);
            this.idCita = idVacuna;
        }
    }
}
