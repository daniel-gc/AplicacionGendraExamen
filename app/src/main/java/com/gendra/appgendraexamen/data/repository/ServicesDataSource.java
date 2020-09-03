package com.gendra.appgendraexamen.data.repository;

import com.gendra.appgendraexamen.webservices.BaseCallback;
import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

public interface ServicesDataSource {

    void getCoordinatesPolygon(String cdPostal, BaseCallback<ResponsePolygon> callback);

    void getDescPolygon(String cdPostal, BaseCallback<ResponseDesc> callback);

    void setLoadLocalData(boolean loadLocalData);

}
