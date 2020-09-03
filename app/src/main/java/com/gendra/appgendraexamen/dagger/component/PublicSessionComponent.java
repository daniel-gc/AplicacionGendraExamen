package com.gendra.appgendraexamen.dagger.component;

import android.content.Context;

import com.gendra.appgendraexamen.commons.Constants;
import com.gendra.appgendraexamen.dagger.modules.PublicSessionModule;
import com.gendra.appgendraexamen.dagger.scopes.PublicSessionScope;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.google.gson.Gson;

import javax.inject.Named;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {PublicSessionModule.class}, dependencies= ApplicationComponent.class)
@PublicSessionScope
public interface PublicSessionComponent {

    @Named(Constants.PUBLIC_SERVICE_POLYGON)
    Retrofit getPublicServicePoligon();

    @Named(Constants.PUBLIC_SERVICE_DESC)
    Retrofit getPublicServiceDesc();

    Context getContext();

    Gson getGson();

    AppSharePreferencesManager getSharedPreference();
}
