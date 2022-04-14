package com.example.pizzachallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OrderViewModel(private val repo: ApiRepository) : ViewModel() {

    private val _state = MutableLiveData<ResponseState>()
    val uiState: LiveData<ResponseState>
        get() = _state

    init {
        getOrders()
    }

    private fun getOrders() {
        CoroutineScope(Dispatchers.IO).launch {
            _state.postValue(ResponseState.INPROGRESS)
            repo.retrieveOrders()
                .catch {
                    _state.postValue(ResponseState.ERROR("Failed network call"))
                }
                .collect {
                    _state.postValue(it)
                }
        }
    }
}