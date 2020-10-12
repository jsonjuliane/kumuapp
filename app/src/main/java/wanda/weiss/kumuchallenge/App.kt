package wanda.weiss.kumuchallenge

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.novoda.merlin.Merlin
import com.novoda.merlin.MerlinsBeard
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import wanda.weiss.kumuchallenge.di.DaggerAppComponent
import wanda.weiss.kumuchallenge.model.network.ApiService
import java.text.SimpleDateFormat
import javax.inject.Inject

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var merlin: Merlin

    @Inject
    lateinit var merlinsBeard: MerlinsBeard

    @Inject
    lateinit var sdf: SimpleDateFormat

    @Inject
    lateinit var timberDebugTree: Timber.Tree

    private var networkListener = MutableLiveData<Boolean>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(timberDebugTree)
        registerConnectable()
    }

    private fun registerConnectable() {
        merlin.registerConnectable {
            networkListener.postValue(true)
        }
        merlin.registerDisconnectable {
            networkListener.postValue(false)
        }
        networkListener.postValue(merlinsBeard.isConnected)
    }
}