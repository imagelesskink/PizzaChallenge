package com.example.pizzachallenge

data class Order(
    val type: String,
    val size: String,
    val toppings: List<String>?,
    val sauce: List<String>?
)

data class OrderResponse(
    val order: List<Order>
)