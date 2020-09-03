package com.gendra.appgendraexamen.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.gendra.appgendraexamen.application.AppInit;

public class ApplicationLifecycleObserver implements LifecycleObserver {

    private AppSharePreferencesManager sharePrefs;

    public ApplicationLifecycleObserver(AppSharePreferencesManager sharePrefs){
        this.sharePrefs = sharePrefs;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppStop() {
        //logica para terminar la aplicacion logueada
        AppInit.IN_APP = false;
    }

}
