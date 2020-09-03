package com.gendra.appgendraexamen.dagger.modules;

import com.gendra.appgendraexamen.commons.Constants;
import com.gendra.appgendraexamen.dagger.scopes.PublicSessionScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class PublicSessionModule {

    public PublicSessionModule(){
    }

    @Provides
    @Named(Constants.PUBLIC_SERVICE_POLYGON)
    @PublicSessionScope
    public static Retrofit providePublicSessionRetrofit(@Named(Constants.PUBLIC_SERVICE_POLYGON_NAME) String baseUrl){

        Gson gson =  new GsonBuilder().setLenient().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpClientBuilder
                .readTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false).build();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Named(Constants.PUBLIC_SERVICE_DESC)
    @PublicSessionScope
    public static Retrofit providePublicSessionRetrofitDesc(@Named(Constants.PUBLIC_SERVICE_DESC_NAME) String baseUrl){

        Gson gson =  new GsonBuilder().setLenient().create();
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpClientBuilder
                .readTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false).build();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @PublicSessionScope
    @Named(Constants.PUBLIC_SERVICE_POLYGON_NAME)
    String provideMSBaseUrl(){
        return Constants.URL_POLYGON;
    }

    @Provides
    @PublicSessionScope
    @Named(Constants.PUBLIC_SERVICE_DESC_NAME)
    String provideMSBaseUrlDesc(){
        return Constants.URL_DESC;
    }

}
