package com.gendra.appgendraexamen.dagger.modules;

import com.gendra.appgendraexamen.application.ActivityScope;
import com.gendra.appgendraexamen.commons.Constants;
import com.gendra.appgendraexamen.data.local.ServiceLocalDataSource;
import com.gendra.appgendraexamen.data.remote.ServicesRemoteDataSource;
import com.gendra.appgendraexamen.data.repository.ServicesRepository;
import com.gendra.appgendraexamen.utils.AppSharePreferencesManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RepositoryModule {

    @Provides
    @ActivityScope
    public ServicesRemoteDataSource providesRemoteDataSource(@Named(Constants.PUBLIC_SERVICE_POLYGON) Retrofit retrofitPolygon,
                                                             @Named(Constants.PUBLIC_SERVICE_DESC) Retrofit retrofitDesc) {
        return new ServicesRemoteDataSource(retrofitPolygon,retrofitDesc);
    }

    @Provides
    @ActivityScope
    public ServiceLocalDataSource providesLocalDataSource(AppSharePreferencesManager sharePreferences) {
        return new ServiceLocalDataSource(sharePreferences);
    }

    @Provides
    @ActivityScope
    public ServicesRepository providesRepository(ServicesRemoteDataSource remoteDataSource, ServiceLocalDataSource localDataSource) {
        return new ServicesRepository(remoteDataSource, localDataSource);
    }

}