package wanda.weiss.kumuchallenge.di.module.app

import android.content.Context
import com.novoda.merlin.Merlin
import com.novoda.merlin.MerlinsBeard
import dagger.Module
import dagger.Provides

@Module
class ConnectivityModule {
    //Injection for future connectivity implementation
    @Provides
    fun getMerlin(context: Context): Merlin {
        return Merlin.Builder().withAllCallbacks().build(context)
    }

    @Provides
    fun getMerlinsBeard(context: Context): MerlinsBeard {
        return MerlinsBeard.from(context)
    }
}