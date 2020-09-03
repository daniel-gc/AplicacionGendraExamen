package com.gendra.appgendraexamen.ui.mapfragment;


import com.gendra.appgendraexamen.data.model.FormDescCorrdinates;
import com.gendra.appgendraexamen.data.repository.ServicesDataSource;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

public class MapPresenterImpl implements MapContract.Presenter{

    public static final String TAG = MapPresenterImpl.class.getSimpleName();

    private MapContract.View view;
    private ServicesDataSource userRepository;
    private AppSharePreferencesManager appSharePreferences;

    public MapPresenterImpl(MapContract.View view, ServicesDataSource userRepository,
                            AppSharePreferencesManager appSharePreferences) {
        this.view = view;
        this.userRepository = userRepository;
        this.appSharePreferences = appSharePreferences;
    }

    @Override
    public String validNumCdPostal(String cdPostale){

        if(cdPostale != null && cdPostale.length() > 0){
            return cdPostale.length() + "/5";
        }else{
            return "Código postal no valido";
        }
    }

    @Override
    public void getDescCoordinatesPolygon(String cdPostal) {
        if(cdPostal == null || cdPostal.trim().isEmpty()){
            view.showSnackBar("Debe introducir un codigo postal correcto.");
        }

        view.showSnackBar("Buscando detalle de coordenadas");
        userRepository.setLoadLocalData(false);
        userRepository.getDescPolygon(cdPostal,new BaseCallback<ResponseDesc>(){

            @Override
            public void onSuccess(ResponseDesc responseDesc) {

                if(responseDesc == null){
                    view.showSnackBar("No se encontraron resultados");
                    return;
                }

                view.showSnackBar("Si se encontraron datos success");


                FormDescCorrdinates result=new FormDescCorrdinates();
                result.setCity(responseDesc.getLocality() == null ? "Not found" : responseDesc.getLocality());
                result.setColony((responseDesc.getSettlements() == null || responseDesc.getSettlements().size() == 0) ? "Not found" :  responseDesc.getSettlements().get(0).getName());
                result.setCountry("México");
                result.setEntity(responseDesc.getFederalEntity() == null ? "Not found" : responseDesc.getFederalEntity().getName());
                result.setMunicipality(responseDesc.getMunicipality() == null ? "Not found" : responseDesc.getMunicipality().getName());
                view.showDescPolygonCoordinates(result);
            }

            @Override
            public void onFailure(String message) {
                view.showSnackBar(message);
            }
        });
    }

    @Override
    public void getCoordinatesPolygon(String cdPostal) {

        if(cdPostal == null || cdPostal.trim().isEmpty()){
            view.showSnackBar("Debe introducir un codigo postal correcto.");
        }

        view.showSnackBar("Buscando detalle de coordenadas");
        userRepository.setLoadLocalData(false);
        userRepository.getCoordinatesPolygon(cdPostal,new BaseCallback<ResponsePolygon>(){

            @Override
            public void onSuccess(ResponsePolygon responsePolygon) {

                if(responsePolygon == null || responsePolygon.getGeometry() == null || responsePolygon.getGeometry().getCoordinates() == null
                        || responsePolygon.getGeometry().getCoordinates().size() == 0){
                    view.showSnackBar("No se encontraron resultados");
                    return;
                }

                view.showSnackBar("Si se encontraron datos success");
                view.buildPolygons(responsePolygon.getGeometry().getCoordinates());
            }

            @Override
            public void onFailure(String message) {
                view.showSnackBar(message);
            }
        });
    }
}
