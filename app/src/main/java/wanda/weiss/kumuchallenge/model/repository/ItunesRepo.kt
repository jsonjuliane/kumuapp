package wanda.weiss.kumuchallenge.model.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import wanda.weiss.kumuchallenge.App
import wanda.weiss.kumuchallenge.model.pojo.ItunesWrapper

class ItunesRepo(private val app: App) : BaseRepository() {

    private var itunesDisposable: Disposable? = null
    var itunesResult = MutableLiveData<ItunesWrapper>()

    fun getItunes(term: String): MutableLiveData<ItunesWrapper> {
        itunesDisposable = app.apiService.getItunesList(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                itunesResult.postValue(ItunesWrapper(it, null))
            }, {
                itunesResult.postValue(ItunesWrapper(null, it))
            })
        return itunesResult
    }

    fun dispose() {
        itunesDisposable?.dispose()
    }
}