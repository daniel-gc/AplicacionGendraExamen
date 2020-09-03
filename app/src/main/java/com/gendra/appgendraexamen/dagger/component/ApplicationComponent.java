package com.gendra.appgendraexamen.dagger.component;

import android.app.Application;
import android.content.Context;

import com.gendra.appgendraexamen.application.PublicSessionManager;
import com.gendra.appgendraexamen.dagger.modules.ApplicationModule;
import com.gendra.appgendraexamen.dagger.modules.ConfigServiceModule;
import com.gendra.appgendraexamen.ui.MainComponent;
import com.gendra.appgendraexamen.ui.MainModule;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ConfigServiceModule.class})
public interface ApplicationComponent {

    Context getContext();

    Application getApplication();

    Gson getGson();

    PublicSessionManager getPublicMSSessionManager();

    AppSharePreferencesManager getAppSharePreference();
}
