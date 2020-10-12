package wanda.weiss.kumuchallenge.di.module.app

import android.annotation.SuppressLint
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.di.AppScope
import wanda.weiss.kumuchallenge.model.config.Configurator
import java.io.File
import java.util.concurrent.TimeUnit


@Module(includes = [LogModule::class, AppModule::class])
class NetworkModule {

    @SuppressLint("HardwareIds")
    @Provides
    @AppScope
    fun getInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                //Implement headers if any
            }
            chain.proceed(builder.build())
        }
    }

    @Provides
    @AppScope
    fun getCacheFile(context: Context): File {
        return File(context.cacheDir, "ok_http_cache")
    }

    @Provides
    @AppScope
    fun getCache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10 MB Cache
    }


    @Provides
    @AppScope
    fun getAuthenticator(app: App): Authenticator {
        return Authenticator { _, response ->
            when {
                response.request().header("Authorization") != null -> {
                    sessionExpired(app)
                    return@Authenticator null
                }
                else -> return@Authenticator response.request().newBuilder()
                    .header("Authorization", Configurator.ACCESS_TOKEN).build()
            }
        }
    }

    @Provides
    @AppScope
    fun getOkHttpClientBuilder(
        authenticator: Authenticator, httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache, httpApiHeaders: Interceptor
    ): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            .authenticator(authenticator)
            .addInterceptor(httpApiHeaders)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
    }

    @Provides
    @AppScope
    fun getOkHttpClient(okHttpclientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpclientBuilder.build()
    }

    private fun sessionExpired(app: App) {

    }

}