package com.gendra.appgendraexamen.ui.mapfragment;

import android.content.Context;

import com.gendra.appgendraexamen.application.ActivityScope;
import com.gendra.appgendraexamen.dagger.modules.RepositoryModule;
import com.gendra.appgendraexamen.data.repository.ServicesRepository;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = RepositoryModule.class)
public class MapModule {

    MapContract.View view;
    Context context;

    public MapModule(MapContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    public MapContract.Presenter providePresenter(ServicesRepository templateDataSource,
                                                  AppSharePreferencesManager appSharePreferences) {
        return new MapPresenterImpl(view, templateDataSource, appSharePreferences);
    }

}
