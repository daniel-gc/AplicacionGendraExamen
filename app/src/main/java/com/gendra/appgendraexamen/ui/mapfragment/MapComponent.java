package com.gendra.appgendraexamen.ui.mapfragment;


import com.gendra.appgendraexamen.application.ActivityScope;
import com.gendra.appgendraexamen.dagger.component.PublicSessionComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = PublicSessionComponent.class,
        modules = MapModule.class
)
public interface MapComponent {

    void inject(MapFragment fragment);

    MapContract.Presenter getPresenter();

}
