package wanda.weiss.kumuchallenge.di.module.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wanda.weiss.kumuchallenge.di.module.itunes.ItunesModule
import wanda.weiss.kumuchallenge.view.itunes.ItunesActivity

@Module
abstract class ActivityBuilderModule {

    //For binding modules to activity : For building activities
    @ContributesAndroidInjector(modules = [ItunesModule::class])
    abstract fun bindItunesActivity(): ItunesActivity

}