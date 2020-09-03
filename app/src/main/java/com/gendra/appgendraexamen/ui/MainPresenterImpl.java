package com.gendra.appgendraexamen.ui;

import com.gendra.appgendraexamen.application.PublicSessionManager;
import com.gendra.appgendraexamen.commons.Constants;
import com.gendra.appgendraexamen.data.repository.ServicesDataSource;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

import javax.inject.Inject;
import javax.inject.Named;

public class MainPresenterImpl implements MainContract.Presenter{

    private MainContract.View view;
    private AppSharePreferencesManager appSharePreferences;
    ServicesDataSource userRepository;

    @Inject
    public MainPresenterImpl(MainContract.View view, ServicesDataSource userRepository,
                                  AppSharePreferencesManager appSharePreferences) {
        this.view = view;
        this.userRepository = userRepository;
        this.appSharePreferences=appSharePreferences;
    }

    @Override
    public void initFragment() {

        view.showSnackBar("Buscando detalle de coordenadas");
        userRepository.setLoadLocalData(false);
        String coordinatesDefault = "14268";
        userRepository.getCoordinatesPolygon(coordinatesDefault,new BaseCallback<ResponsePolygon>(){

            @Override
            public void onSuccess(ResponsePolygon responsePolygon) {

                if(responsePolygon == null || responsePolygon.getGeometry() == null || responsePolygon.getGeometry().getCoordinates().size() == 0){
                    view.showSnackBar("No se encontraron resultados");
                    return;
                }

                view.showSnackBar("Si se encontraron datos success");
            }

            @Override
            public void onFailure(String message) {
                view.showSnackBar(message);
            }
        });


    }

}
