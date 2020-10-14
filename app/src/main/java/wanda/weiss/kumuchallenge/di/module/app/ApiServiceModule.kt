package wanda.weiss.kumuchallenge.di.module.app

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import wanda.weiss.kumuchallenge.di.AppScope
import wanda.weiss.kumuchallenge.model.network.ApiConfiguration
import wanda.weiss.kumuchallenge.model.network.ApiService

@Module(includes = [(NetworkModule::class), (DataModule::class)])
class ApiServiceModule {

    //Providing api service class
    @Provides
    @AppScope
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //Providing retrofit instance
    @Provides
    @AppScope
    fun getRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        apiConfiguration: ApiConfiguration
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(apiConfiguration.baseUrl)
            .build()
    }

}