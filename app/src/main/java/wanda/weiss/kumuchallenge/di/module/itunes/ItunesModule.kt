package wanda.weiss.kumuchallenge.di.module.itunes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.repository.ItunesRepo
import wanda.weiss.kumuchallenge.view.itunes.ItunesActivity
import wanda.weiss.kumuchallenge.viewmodel.ItunesVM

@Module
class ItunesModule {

    @Provides
    fun provideRepo(app: App): ItunesRepo {
        return ItunesRepo(app)
    }

    @Provides
    fun provideVM(itunesActivity: ItunesActivity, app: App, itunesRepo: ItunesRepo): ItunesVM {
        return ViewModelProvider(itunesActivity, ItunesVMFactory(app, itunesRepo)).get(ItunesVM::class.java)
    }

    inner class ItunesVMFactory(
        private val application: App,
        private val itunesRepo: ItunesRepo
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItunesVM(application, itunesRepo) as T
        }
    }
}
