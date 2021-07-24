package com.example.if4101_espic_b76711_b72933.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if4101_espic_b76711_b72933.DetalleAlergiaActivity;
import com.example.if4101_espic_b76711_b72933.R;
import com.example.if4101_espic_b76711_b72933.Respuesta.Alergia;

import java.util.ArrayList;

public class AlergiaAdapter extends RecyclerView.Adapter<AlergiaAdapter.ViewHolder>{

    private ArrayList<Alergia> dataset;

    private Context context;

    private View.OnClickListener listener;

    private int cedula;

    public AlergiaAdapter(Context context){
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
        Alergia alergia = dataset.get(position);
        holder.nombreAlergia.setText(alergia.getNombre());
        holder.detalleAlergia.setText(alergia.getFecha());

        holder.setOnclickListener(alergia.getId_Alergia());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaAlergias(ArrayList<Alergia> listaAlergias, int cedula) {
        dataset.addAll(listaAlergias);
        this.cedula = cedula;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nombreAlergia;
        public TextView detalleAlergia;
        public ConstraintLayout contenedor;
        public int idAlergia;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreAlergia = (TextView) itemView.findViewById(R.id.tv_NombreLista);
            detalleAlergia = (TextView) itemView.findViewById(R.id.tv_DetalleLista);
            contenedor = (ConstraintLayout) itemView.findViewById(R.id.cl_lista);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetalleAlergiaActivity.class);
            intent.putExtra("idAlergia", this.idAlergia);
            intent.putExtra("Cedula", cedula);
            view.getContext().startActivity(intent);

        }

        public void setOnclickListener(int idAlergia){
            contenedor.setOnClickListener(this);
            this.idAlergia = idAlergia;
        }
    }
}
