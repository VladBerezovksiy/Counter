package com.example.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val model: Model = ModelImpl
    val uiStateLiveData = MutableLiveData<UIState>(UIState.EmptyList)
    val intentLiveData = MutableLiveData<Intent>()
    private val observer = Observer<Intent> {
        addItem()
    }

    init {
        intentLiveData.observeForever(observer)
        if (model.getItems().isNotEmpty()) {
            uiStateLiveData.value = UIState.FilledList(model.getItems())
        }
    }

    private fun addItem() {
        model.addItems()
        uiStateLiveData.value = UIState.FilledList(model.getItems())
    }

    fun removeItem() {
        model.removeItems()
        uiStateLiveData.value = UIState.FilledList(model.getItems())
    }

    override fun onCleared() {
        super.onCleared()
        MyApplication.getApp().saveData(model.getItems())
        intentLiveData.removeObserver(observer)
    }

}

sealed class UIState {
    object EmptyList : UIState()
    class FilledList(val list: List<String>) : UIState()
}

sealed class Intent {
    object AddItem : Intent()
}