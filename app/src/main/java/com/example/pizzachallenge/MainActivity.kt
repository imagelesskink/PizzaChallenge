package com.example.pizzachallenge

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { Dep.provideViewModel(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
    }

    private fun setObservers()  {
        viewModel.uiState.observe(this) {
            when(it) {
                is ResponseState.INPROGRESS -> {
                    Log.d("Output", "Loading...")
                }
                is ResponseState.ERROR -> {
                    // show error text
                    Log.d("Output", it.err)
                }
                is ResponseState.SUCCESS -> {
                    calculatePrice(it.order.order)
                }
            }
        }
    }

    private fun calculatePrice(orders: List<Order>) {
        var total = 0

        for(i in orders) {
            when(i.size) {
                SMALL -> {
                    total += 4
                }
                MEDIUM -> {
                    total += 8
                }
                LARGE -> {
                    total += 15
                }
            }
        }
        Log.d("Output", "Total is ${total}€")
        findViewById<TextView>(R.id.output).text = "Your total is " +total.toString() + "€"
    }
}