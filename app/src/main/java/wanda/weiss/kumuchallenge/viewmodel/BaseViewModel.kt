package wanda.weiss.kumuchallenge.viewmodel

import androidx.lifecycle.AndroidViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import wanda.weiss.kumuchallenge.App
import java.util.concurrent.TimeUnit

open class BaseViewModel(app: App) : AndroidViewModel(app) {
    fun configureInterceptor(
        autoCompletePublishSubject: PublishSubject<String>,
        timeout: Long
    ): Observable<String> {
        return autoCompletePublishSubject
            .debounce(timeout, TimeUnit.MILLISECONDS)
            .filter { text -> text.isNotEmpty() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
}