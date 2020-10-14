package wanda.weiss.kumuchallenge.di.module.itunes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.db.AppDatabase
import wanda.weiss.kumuchallenge.model.observable.ItunesObservable
import wanda.weiss.kumuchallenge.model.repository.ItunesRepo
import wanda.weiss.kumuchallenge.view.itunes.ItunesActivity
import wanda.weiss.kumuchallenge.viewmodel.ItunesVM

@Module
class ItunesModule {

    //Providing repository for handling api calls
    @Provides
    fun provideRepo(app: App, db: AppDatabase): ItunesRepo {
        return ItunesRepo(app, db)
    }

    //Providing observable for handling UI and Variable States
    @Provides
    fun provideObservable():ItunesObservable{
        return ItunesObservable()
    }

    //Providing Room DB for handling local storage
    @Provides
    fun provideAppDB(app: App): AppDatabase {
        return AppDatabase.getDatabase(app)
    }

    //Providing View Model Class for handling logic outside the UI schema
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
