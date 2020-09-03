package com.gendra.appgendraexamen.application;

import com.gendra.appgendraexamen.dagger.component.DaggerPublicSessionComponent;
import com.gendra.appgendraexamen.dagger.component.PublicSessionComponent;
import com.gendra.appgendraexamen.dagger.modules.PublicSessionModule;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PublicSessionManager {

    private PublicSessionComponent publicSessionComponent;

    @Inject
    public PublicSessionManager() {
    }

    public void createMSSession(){
        publicSessionComponent = DaggerPublicSessionComponent.builder()
                .applicationComponent(AppInit.getAppComponent())
                .publicSessionModule(new PublicSessionModule())
                .build();
    }

    public PublicSessionComponent getPublicMSComponent(){
        return publicSessionComponent;
    }

    public boolean isAuthenticated(){
        return publicSessionComponent !=null;
    }

}
