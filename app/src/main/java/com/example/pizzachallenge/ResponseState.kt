package com.example.pizzachallenge

sealed class ResponseState {
    object INPROGRESS : ResponseState()
    data class ERROR(val err: String) : ResponseState()
    data class SUCCESS(val order: OrderResponse) : ResponseState()
}
