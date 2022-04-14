package com.example.pizzachallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface ApiReposInt {
    suspend fun retrieveOrders(): Flow<ResponseState>
}

class ApiRepository(private val api: ApiInterface): ApiReposInt {
    override suspend fun retrieveOrders(): Flow<ResponseState> = flow { emit(api.retrieveOrders()) }
        .onStart { ResponseState.INPROGRESS }
        .map { response ->
            response.body()?.let {
                println(it.order)
                ResponseState.SUCCESS(it)
            } ?: ResponseState.ERROR("Empty Response")
        }
        .flowOn(Dispatchers.IO)
        .conflate()
}