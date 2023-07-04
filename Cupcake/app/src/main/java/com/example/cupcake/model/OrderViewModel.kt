package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//import androidx.lifecycle.Transformations

private const val PRICE_PER_CUPCAKE = 2.0
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.0

class OrderViewModel : ViewModel() {

    private val _quantity = MutableLiveData<Int>()
    private var _flavor = MutableLiveData<String>()
    private var _date = MutableLiveData<String>()
    private var _price = MutableLiveData<Double>()
    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    val quantity: LiveData<Int>
        get() = _quantity
    val flavor: LiveData<String>
        get() = _flavor
    val date: LiveData<String>
        get() = _date
    val price: LiveData<Double>
        get() = _price
//    val price: LiveData<String> = Transformations.map(_price) {
//        NumberFormat.getCurrencyInstance().format(it)
//    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * (PRICE_PER_CUPCAKE)
        if (dateOptions[0] == date.value) {
            calculatedPrice += 3
        }
        _price.value = calculatedPrice
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    private fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }
}