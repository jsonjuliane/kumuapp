package wanda.weiss.kumuchallenge.di.module.app

import android.content.Context
import dagger.Module
import dagger.Provides
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.di.AppScope


@Module
class AppModule {
    @Provides
    @AppScope
    fun provideContext(app: App) : Context {
        return app
    }
}