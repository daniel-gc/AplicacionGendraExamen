package com.gendra.appgendraexamen.data.repository;

import android.util.Log;

import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

public class ServicesRepository implements ServicesDataSource{

    public static final String TAG = ServicesRepository.class.getSimpleName();

    private ServicesDataSource localDataSource;
    private ServicesDataSource remoteDataSource;
    private boolean loadLocalData;

    public ServicesRepository(ServicesDataSource remoteDataSource, ServicesDataSource localDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.loadLocalData = true;
    }

    @Override
    public void getCoordinatesPolygon(String cdPostal, BaseCallback<ResponsePolygon> callback) {
        if(loadLocalData){
            // en caso de aplicar se obtienen datos de base local
            Log.e(TAG,"Se obtienen datos de repositorio local");
            localDataSource.getCoordinatesPolygon(cdPostal,callback);
        }else{
            // se obtienen datos de repositorio remoto
            Log.e(TAG,"Se obtienen datos de repositorio remoto");
            remoteDataSource.getCoordinatesPolygon(cdPostal,callback);
        }
    }

    @Override
    public void getDescPolygon(String cdPostal, BaseCallback<ResponseDesc> callback) {
        if(loadLocalData){
            // en caso de aplicar se obtienen datos de base local
            Log.e(TAG,"Se obtienen datos de repositorio local");
            localDataSource.getDescPolygon(cdPostal, callback);
        }else{
            // se obtienen datos de repositorio remoto
            Log.e(TAG,"Se obtienen datos de repositorio remoto");
            remoteDataSource.getDescPolygon(cdPostal, callback);
        }
    }

    @Override
    public void setLoadLocalData(boolean loadLocalData) {
        this.loadLocalData = loadLocalData;
    }
}
