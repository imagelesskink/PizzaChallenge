package com.example.pizzachallenge

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET(ORDER)
    suspend fun retrieveOrders(): Response<OrderResponse>
}