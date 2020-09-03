package com.gendra.appgendraexamen.ui.mapfragment;


import com.gendra.appgendraexamen.data.model.FormDescCorrdinates;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;

import java.util.List;

public interface MapContract {

    interface View{
        void showSnackBar(String message);
        void buildPolygons(List<List<List<Double>>> coordinates);
        void showDescPolygonCoordinates(FormDescCorrdinates responseDesc);
    }

    interface Presenter{
        void getCoordinatesPolygon(String cdPostal);
        String validNumCdPostal(String cdPostale);
        void getDescCoordinatesPolygon(String cdPostale);
    }

}
