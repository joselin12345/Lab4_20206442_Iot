package com.example.lab4_20206442.Model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20206442.Data.ClimaData;

import java.util.ArrayList;
import java.util.List;

public class ClimaModel extends ViewModel {

    // se trato de usar ayuda de ChatGpt para resolver esta parte

    private MutableLiveData<List<ClimaData>> listaClima;

    public ClimaModel() {
        listaClima = new MutableLiveData<>();
        listaClima.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<ClimaData>> getClimaList() {
        return listaClima;
    }
    public void addClima(ClimaData clima) {
        List<ClimaData> currentList = listaClima.getValue();
        currentList.add(clima);
        listaClima.postValue(currentList);
    }

}
