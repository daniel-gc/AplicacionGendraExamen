package com.gendra.appgendraexamen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppSharePreferencesManager {

    private static final String FILE_NAME = "sicn_app_preferences";
    private static final String LIST_SEARCH_CODE = "LIST_SEARCH_CODE";

    private SharedPreferences preferences;

    public AppSharePreferencesManager(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveListSearchCodePostal(List<String> listWorldSearchs) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LIST_SEARCH_CODE,new Gson().toJson(listWorldSearchs));
        editor.apply();
        Log.d("Ejemplo", "saveListSearchWorld");
    }

    public List<String> getListSearchCodePostal() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        String json = preferences.getString(LIST_SEARCH_CODE, "");
        List<String> listWorldSearchs = gson.fromJson(json, type);
        if(listWorldSearchs == null)
            return  new ArrayList<>();

        return listWorldSearchs;
    }

}
