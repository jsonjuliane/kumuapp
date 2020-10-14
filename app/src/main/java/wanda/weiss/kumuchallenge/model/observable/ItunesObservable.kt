package wanda.weiss.kumuchallenge.model.observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import wanda.weiss.kumuchallenge.BR

class ItunesObservable: BaseObservable(){

    //For handling observable fields to update UI and variables
    @get:Bindable
    var searchEmpty: Boolean = false
        set(searchEmpty) {
            field = searchEmpty
            notifyPropertyChanged(BR.searchEmpty)
        }

}