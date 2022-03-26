package com.example.covidstate.viewmodels;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covidstate.models.CovidResponseModel;
import com.example.covidstate.networks.CovidService;
import com.example.covidstate.ulits.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidViewModel extends ViewModel {
    private Location location;
    private MutableLiveData<CovidResponseModel> responseInfoLiveData = new MutableLiveData<>();

    private MutableLiveData<String> errorMessageLiveData = new MutableLiveData<>();
    private String country = "bangladesh";
    public void setCity(String city){
        this.country = city;
    }

    public void loadData(){
        fetchResponseData();
    }

    private void fetchResponseData(){
        final String endUrl = String.format("%s?yesterday=true&strict=true&query", country);
        CovidService.getService().getCurrentData(endUrl).enqueue(new Callback<CovidResponseModel>() {
            @Override
            public void onResponse(Call<CovidResponseModel> call, Response<CovidResponseModel> response) {
                if (response.code()==200){
//                    Log.e("weather_test", ""+response.code() );
                    responseInfoLiveData.postValue(response.body());
                } else if (response.code() == 404){
                    errorMessageLiveData.postValue("Country Not Found");
//                    Log.e("weather_test", ""+response.code() );
                }
            }

            @Override
            public void onFailure(Call<CovidResponseModel> call, Throwable t) {
                Log.e("weather_test", ""+t );
            }
        });

    }



    public MutableLiveData<String> getErrorMessageLiveData() {
        return errorMessageLiveData;
    }

    public MutableLiveData<CovidResponseModel> getResponseInfoLiveData() {
        return responseInfoLiveData;
    }

}
