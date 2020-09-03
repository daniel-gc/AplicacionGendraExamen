package com.gendra.appgendraexamen.data.model;

import com.google.gson.annotations.SerializedName;

public class Settlement {
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("zone_type")
    private String zoneType;

    private SettlementType settlementType;

    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setZone_type(String zoneType){
        this.zoneType = zoneType;
    }
    public String getZoneType(){
        return this.zoneType;
    }
    public void setSettlementType(SettlementType settlementType){
        this.settlementType = settlementType;
    }
    public SettlementType getSettlementType(){
        return this.settlementType;
    }

}
