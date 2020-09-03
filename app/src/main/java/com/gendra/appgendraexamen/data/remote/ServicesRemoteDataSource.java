package com.gendra.appgendraexamen.data.remote;

import android.util.Log;

import com.gendra.appgendraexamen.commons.Constants;
import com.gendra.appgendraexamen.data.repository.ServicesDataSource;
import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.IServicesAPI;
import com.gendra.appgendraexamen.webservices.RetrofitCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ServicesRemoteDataSource implements ServicesDataSource {

    public static final String TAG = ServicesRemoteDataSource.class.getSimpleName();

    private Retrofit retrofitPolygon;
    private Retrofit retrofitDesc;

    @Inject
    public ServicesRemoteDataSource(@Named(Constants.PUBLIC_SERVICE_POLYGON) Retrofit retrofitPolygon,
                                    @Named(Constants.PUBLIC_SERVICE_DESC) Retrofit retrofitDesc) {
        this.retrofitPolygon = retrofitPolygon;
        this.retrofitDesc = retrofitDesc;
    }

    @Override
    public void getCoordinatesPolygon(String cdPostal, BaseCallback<ResponsePolygon> callback) {
        Log.d(TAG, "getCoordinatesPolygon retrofit call");
        IServicesAPI service = this.retrofitPolygon.create(IServicesAPI.class);
        Call<ResponsePolygon> userCall = service.getCoordPolygon(cdPostal);
        userCall.enqueue(new RetrofitCallback<>(callback));

    }

    @Override
    public void getDescPolygon(String cdPostal, BaseCallback<ResponseDesc> callback) {
        Log.d(TAG, "getDescPolygon retrofit call");
        IServicesAPI service = this.retrofitDesc.create(IServicesAPI.class);
        Call<ResponseDesc> userCall = service.getDescPolygon(cdPostal);
        userCall.enqueue(new RetrofitCallback<>(callback));

    }

    @Override
    public void setLoadLocalData(boolean loadLocalData) {}
}
