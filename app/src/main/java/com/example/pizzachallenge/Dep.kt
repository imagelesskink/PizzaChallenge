package com.example.pizzachallenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dep {
    private val api: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    private fun provideRepository() = ApiRepository(api)

    fun provideViewModel(storeOwner: ViewModelStoreOwner) =
        ViewModelProvider(storeOwner,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return OrderViewModel(provideRepository()) as T
                }
            })[OrderViewModel::class.java]
}