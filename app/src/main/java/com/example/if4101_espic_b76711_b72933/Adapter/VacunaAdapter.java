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

import com.example.if4101_espic_b76711_b72933.DetalleVacunaActivity;
import com.example.if4101_espic_b76711_b72933.R;
import com.example.if4101_espic_b76711_b72933.Respuesta.Vacuna;

import java.util.ArrayList;

public class VacunaAdapter extends RecyclerView.Adapter<VacunaAdapter.ViewHolder>{

    private ArrayList<Vacuna> dataset;

    private Context context;

    private View.OnClickListener listener;

    private int cedula;

    public VacunaAdapter(Context context){
        dataset = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vacuna vacuna = dataset.get(position);
        holder.nombreVacuna.setText(vacuna.getNombre() + " - " + vacuna.getFecha_Vacuna());
        holder.detalleVacuna.setText(vacuna.getCentro_Salud());

        holder.setOnclickListener(vacuna.getId_Vacuna());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaVacunas(ArrayList<Vacuna> listaVacunas, int cedula) {
        dataset.addAll(listaVacunas);
        this.cedula = cedula;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nombreVacuna;
        public TextView detalleVacuna;
        public ConstraintLayout contenedor;
        public int idVacuna;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreVacuna = (TextView) itemView.findViewById(R.id.tv_NombreLista);
            detalleVacuna = (TextView) itemView.findViewById(R.id.tv_DetalleLista);
            contenedor = (ConstraintLayout) itemView.findViewById(R.id.cl_lista);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetalleVacunaActivity.class);
            intent.putExtra("idVacuna", this.idVacuna);
            intent.putExtra("Cedula", cedula);
            view.getContext().startActivity(intent);

        }

        public void setOnclickListener(int idVacuna){
            contenedor.setOnClickListener(this);
            this.idVacuna = idVacuna;
        }
    }
}
