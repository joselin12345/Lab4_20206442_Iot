package com.example.lab4_20206442;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_20206442.Adapter.ClimaAdapter;
import com.example.lab4_20206442.Adapter.GeoAdapter;
import com.example.lab4_20206442.Data.ClimaData;
import com.example.lab4_20206442.Data.Coord;
import com.example.lab4_20206442.Data.GeoData;
import com.example.lab4_20206442.Data.Main;
import com.example.lab4_20206442.Service.ClimaService;
import com.example.lab4_20206442.Service.GeoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BlankFragmentClima extends Fragment {

    private ConstraintLayout  geo;
    private RecyclerView recyclerView;
    private ClimaAdapter climaAdapter;
    private ClimaService climaService;
    private List<ClimaData> listaClima;
    private Main main;
    private Coord coord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // fragmento
        View view = inflater.inflate(R.layout.fragment_blank_clima, container, false);

        geo = view.findViewById(R.id.geo);

        configuracion();

        // recycler

        recyclerView = view.findViewById(R.id.recycler_view_clima);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       listaClima = new ArrayList<>();

        climaAdapter = new ClimaAdapter(listaClima);


        recyclerView.setAdapter( climaAdapter);


        //conexión

        climaService = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClimaService.class);

        // busqueda

        EditText latitudedit = view.findViewById(R.id.latitu);
        EditText Longitud = view.findViewById(R.id.Longitud);
        ConstraintLayout buttonBuscar = view.findViewById(R.id.buscar);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitud = latitudedit.getText().toString().trim();
                String longitud = Longitud.getText().toString().trim();
                if (!latitud.isEmpty() && !longitud.isEmpty()) {

                    fetchProfileFromWs(latitud,longitud);
                }
            }
        });



        return view;
    }

    private void configuracion() {


        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(BlankFragmentClima.this);

                navController.navigate(R.id.action_blankFragmentClima_to_blankFragmentGeo);
            }
        });

    }

    public void fetchProfileFromWs(String latitud,String longitud){

        Log.d("msg-test-ws-profile","entra al metodo " );

        Call<List<ClimaData>> call = climaService.getClima(Double.parseDouble(latitud), Double.parseDouble(longitud), "792edf06f1f5ebcaf43632b55d8b03fe");

        call.enqueue(new Callback<List<ClimaData>>() {
            @Override
            public void onResponse(Call<List<ClimaData>> call, Response<List<ClimaData>> response) {
                if (response.isSuccessful()) {

                    List<ClimaData> data = response.body();

                    Log.d("msg-test-ws-profile",data.get(0).getName( ));



                        listaClima.add(new ClimaData(data.get(0).getName(), data.get(0).getMain(), data.get(0).getCoord()));
                        climaAdapter.notifyDataSetChanged();
                        Log.d("msg-test-ws-profile", "No es igual");


                } else {
                    Log.d("msg-test-ws-profile","no hay respuesta " );
                }
            }

            @Override
            public void onFailure(Call<List<ClimaData>> call, Throwable t) {
                t.printStackTrace();
                Log.d("msg-test-ws-profile","hay un error " );
            }
        });




    }


}