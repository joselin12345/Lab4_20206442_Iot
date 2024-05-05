package com.example.lab4_20206442.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20206442.Data.ClimaData;
import com.example.lab4_20206442.Data.GeoData;
import com.example.lab4_20206442.R;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolder>{

    private List<ClimaData> listaClima;

    public ClimaAdapter(List<ClimaData> listaClima){
        this.listaClima = listaClima;
    }

    @NonNull
    @Override
    public ClimaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clima_item, parent, false);
        return new ClimaAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ClimaAdapter.ViewHolder holder, int position) {
        ClimaData climaData = listaClima.get(position);
        String nombreCiudad = climaData.getName();
        if (nombreCiudad != null) {
            holder.ciudad.setText(nombreCiudad);
        } else {
            holder.ciudad.setText("Desconocido");
        }
        holder.minimo.setText("Min: " + climaData.getMain().getTemp_min() + "K");
        holder.maximo.setText("Min: " + climaData.getMain().getTemp_max() + "K");
        holder.temperatura.setText("Min: " + climaData.getMain().getTemp() + "K");


    }

    @Override
    public int getItemCount() {
        return listaClima.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ciudad;
        TextView minimo;
        TextView maximo;
        TextView temperatura;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ciudad = itemView.findViewById(R.id.Ciudad);
            minimo = itemView.findViewById(R.id.minimo);
            maximo = itemView.findViewById(R.id.maximo);
            temperatura = itemView.findViewById(R.id.temperatura);

        }
    }



}
