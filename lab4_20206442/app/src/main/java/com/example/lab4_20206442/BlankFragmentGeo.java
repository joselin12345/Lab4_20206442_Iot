package com.example.lab4_20206442;

import android.content.Intent;
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

import com.example.lab4_20206442.Adapter.GeoAdapter;
import com.example.lab4_20206442.Data.GeoData;
import com.example.lab4_20206442.Service.GeoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BlankFragmentGeo extends Fragment {

    private ConstraintLayout clima;

    private RecyclerView recyclerView;
    private GeoAdapter geoAdapter;
    private GeoService geoService;
    private List<GeoData> listaGeo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // fragmento
        View view = inflater.inflate(R.layout.fragment_blank_geo, container, false);

        clima = view.findViewById(R.id.clima);

        configuracion();

        // recycler

        recyclerView = view.findViewById(R.id.recycler_view_geo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       listaGeo = new ArrayList<>();

        geoAdapter = new GeoAdapter(listaGeo);


        recyclerView.setAdapter( geoAdapter);

        //conexión

        geoService = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeoService.class);

        // busqueda

        EditText editText = view.findViewById(R.id.ciudad);
        ConstraintLayout buttonBuscar = view.findViewById(R.id.buscar);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editText.getText().toString().trim();
                if (!userInput.isEmpty()) {

                    fetchProfileFromWs(userInput);
                }
            }
        });

        return view;

    }

    private void configuracion() {


        clima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavController navController = NavHostFragment.findNavController(BlankFragmentGeo.this);

                navController.navigate(R.id.action_blankFragmentGeo_to_blankFragmentClima);
            }
        });


    }

    public void fetchProfileFromWs(String userInput){

        Log.d("msg-test-ws-profile","entra al metodo " );

        Call<List<GeoData>> call = geoService.getGeo(userInput, 1, "8dd6fc3be19ceb8601c2c3e811c16cf1");

        call.enqueue(new Callback<List<GeoData>>() {
            @Override
            public void onResponse(Call<List<GeoData>> call, Response<List<GeoData>> response) {
                if (response.isSuccessful()) {
                    List<GeoData> data = response.body();
                    Log.d("msg-test-ws-profile",data.get(0).getState( ));

                    boolean encontrado = false;
                    for (GeoData geoData : listaGeo) {
                        if (geoData.getState().equals(data.get(0).getState())) {
                            encontrado = true;
                            break;
                        }
                    }

                    if (encontrado) {
                        showToastWithDelay("Ya está disponible", 3000);
                        Log.d("msg-test-ws-profile", "Es igual");
                    } else {
                        listaGeo.add(new GeoData(data.get(0).getState(), data.get(0).getLat(), data.get(0).getLon()));
                        geoAdapter.notifyDataSetChanged();
                        Log.d("msg-test-ws-profile", "No es igual");
                    }

                } else {
                    Log.d("msg-test-ws-profile","no hay respuesta " );
                }
            }

            @Override
            public void onFailure(Call<List<GeoData>> call, Throwable t) {
                t.printStackTrace();
                Log.d("msg-test-ws-profile","hay un error " );
            }
        });




    }

    private void showToastWithDelay(String message, int duration) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, duration);
    }



}