package com.example.covidstate.networks;

import com.example.covidstate.models.CovidResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CovidServiceApi {
    @GET
    Call<CovidResponseModel> getCurrentData(@Url String endUrl);
}
