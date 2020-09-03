package com.gendra.appgendraexamen.application;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.gendra.appgendraexamen.dagger.component.ApplicationComponent;
import com.gendra.appgendraexamen.dagger.component.DaggerApplicationComponent;
import com.gendra.appgendraexamen.dagger.modules.ApplicationModule;
import com.gendra.appgendraexamen.dagger.modules.ConfigServiceModule;
import com.gendra.appgendraexamen.utils.ApplicationLifecycleObserver;

public class AppInit extends MultiDexApplication {

    private static ApplicationComponent component;
    public static boolean IN_APP;

    @Override
    public void onCreate() {
        super.onCreate();
        configDagger();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationLifecycleObserver(component.getAppSharePreference()));
        Utils.init(this);
    }

    private void configDagger() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .configServiceModule(new ConfigServiceModule())
                .build();
    }

    public static ApplicationComponent getAppComponent() {
        return component;
    }

}
