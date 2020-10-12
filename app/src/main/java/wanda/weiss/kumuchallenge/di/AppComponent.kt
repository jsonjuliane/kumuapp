package wanda.weiss.kumuchallenge.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.di.module.app.*

@AppScope
@Component(
    modules = [
        ActivityBuilderModule::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApiServiceModule::class,
        AppModule::class,
        ConnectivityModule::class,
        DataModule::class,
        LogModule::class,
        NetworkModule::class]
)

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}