package com.gendra.appgendraexamen.ui;

import com.gendra.appgendraexamen.application.ActivityScope;
import com.gendra.appgendraexamen.application.PublicSessionManager;
import com.gendra.appgendraexamen.dagger.modules.RepositoryModule;
import com.gendra.appgendraexamen.data.repository.ServicesRepository;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = RepositoryModule.class)
public class MainModule {

    MainContract.View view;

    public MainModule(MainContract.View view){
        this.view=view;
    }

    @Provides
    @ActivityScope
    public MainContract.Presenter providePresenter(ServicesRepository templateDataSource,
                                                   AppSharePreferencesManager appSharePreferences) {
        return new MainPresenterImpl(view, templateDataSource,appSharePreferences);
    }

}
