package com.gendra.appgendraexamen.webservices;


import com.gendra.appgendraexamen.webservices.response.ResponseDesc;
import com.gendra.appgendraexamen.webservices.response.ResponsePolygon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IServicesAPI {

    String GET_COORD_POLYGON = "zip-codes/{code-postal}";
    String GET_DESC_POLYGON = "zip-codes/{code-postal}";


    //OBTENER COORDENADAS DE POLYGON
    @GET(GET_COORD_POLYGON)
    Call<ResponsePolygon> getCoordPolygon(@Path("code-postal") String cdPostal);

    //OBTENER DESTALLE DE POLYGON
    @GET(GET_DESC_POLYGON)
    Call<ResponseDesc> getDescPolygon(@Path("code-postal") String cdPostal);



}
