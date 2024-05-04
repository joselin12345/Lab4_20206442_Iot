package com.example.lab4_20206442.Adapter;

import android.util.Log;
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

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.ViewHolder> {

    private List<GeoData> listaGeo;

    public GeoAdapter(List<GeoData> listaGeo){
        this.listaGeo = listaGeo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.geo_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GeoData geoData = listaGeo.get(position);
        holder.ciudad.setText(geoData.getState());
        holder.longitud.setText(geoData.getLon());
        holder.latitud.setText(geoData.getLat());

    }

    @Override
    public int getItemCount() {
        return listaGeo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ciudad;
        TextView longitud;
        TextView latitud;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ciudad = itemView.findViewById(R.id.ciudad);
            longitud = itemView.findViewById(R.id.longitud);
            latitud = itemView.findViewById(R.id.latitud);

        }
    }

}
