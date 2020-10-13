package wanda.weiss.kumuchallenge.model.observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import wanda.weiss.kumuchallenge.BR

class ItunesObservable: BaseObservable(){

    @get:Bindable
    var searchEmpty: Boolean = true
        set(searchEmpty) {
            field = searchEmpty
            notifyPropertyChanged(BR.searchEmpty)
        }

}