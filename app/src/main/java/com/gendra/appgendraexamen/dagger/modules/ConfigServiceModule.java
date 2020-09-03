package com.gendra.appgendraexamen.dagger.modules;

import com.gendra.appgendraexamen.commons.Constants;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ConfigServiceModule {

    public ConfigServiceModule(){
    }

    @Provides
    @Singleton
    @Named(Constants.PUBLIC_SERVICE_POLYGON)
    Retrofit provideConfigRetrofit(Gson gson, @Named(Constants.PUBLIC_SERVICE_POLYGON) OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.URL_POLYGON)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named(Constants.PUBLIC_SERVICE_DESC)
    Retrofit provideConfigRetrofitDesc(Gson gson, @Named(Constants.PUBLIC_SERVICE_DESC) OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.URL_DESC)
                .client(okHttpClient)
                .build();
    }

}
