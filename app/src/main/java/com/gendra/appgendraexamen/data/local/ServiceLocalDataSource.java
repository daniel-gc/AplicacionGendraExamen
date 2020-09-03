package com.gendra.appgendraexamen.data.local;

import com.gendra.appgendraexamen.data.repository.ServicesDataSource;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

import javax.inject.Inject;

public class ServiceLocalDataSource implements ServicesDataSource {

    private AppSharePreferencesManager sharePrefs;

    @Inject
    public ServiceLocalDataSource(AppSharePreferencesManager sharePreferences){
        this.sharePrefs = sharePreferences;
    }


    @Override
    public void getCoordinatesPolygon(String cdPostal, BaseCallback<ResponsePolygon> callback) {
        // implementacion para obtener datos de local
    }

    @Override
    public void getDescPolygon(String cdPostal, BaseCallback<ResponseDesc> callback) {
        // implementacion para obtener datos de local
    }

    @Override
    public void setLoadLocalData(boolean loadLocalData) {

    }
}
