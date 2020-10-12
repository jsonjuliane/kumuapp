package wanda.weiss.kumuchallenge.di.module.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import wanda.weiss.kumuchallenge.di.AppScope
import java.text.SimpleDateFormat
import java.util.*

@Module
class DataModule {
    val gson: Gson
        @Provides
        @AppScope
        get() = GsonBuilder()
                .setPrettyPrinting()
                .create()

    val dateFormat: SimpleDateFormat
        @Provides
        @AppScope
        get() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())

}