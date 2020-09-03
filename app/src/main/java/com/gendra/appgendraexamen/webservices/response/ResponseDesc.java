package com.gendra.appgendraexamen.webservices.response;


import com.gendra.appgendraexamen.data.model.FederalEntity;
import com.gendra.appgendraexamen.data.model.Municipality;
import com.gendra.appgendraexamen.data.model.Settlement;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDesc{

    @SerializedName("zip_code")
    private String zipCode;
    @SerializedName("locality")
    private String locality;
    @SerializedName("federal_entity")
    private FederalEntity federalEntity;
    @SerializedName("settlements")
    private List<Settlement> settlements;
    @SerializedName("municipality")
    private Municipality municipality;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public FederalEntity getFederalEntity() {
        return federalEntity;
    }

    public void setFederalEntity(FederalEntity federalEntity) {
        this.federalEntity = federalEntity;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(List<Settlement> settlements) {
        this.settlements = settlements;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
}
