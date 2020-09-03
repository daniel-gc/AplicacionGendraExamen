package com.gendra.appgendraexamen.ui;

import com.gendra.appgendraexamen.application.ActivityScope;
import com.gendra.appgendraexamen.dagger.component.PublicSessionComponent;
import com.gendra.appgendraexamen.data.repository.ServicesDataSource;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = PublicSessionComponent.class,
        modules = MainModule.class
)
public interface MainComponent {

    void inject(MainActivity mainActivity);

    MainContract.Presenter getPresenter();
}
