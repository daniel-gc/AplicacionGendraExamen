package com.gendra.appgendraexamen.dagger.modules;

import android.app.Application;
import android.content.Context;

import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides @Singleton
    AppSharePreferencesManager provideSharePreferences() {
        return new AppSharePreferencesManager(application);
    }

    @Provides @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

}
