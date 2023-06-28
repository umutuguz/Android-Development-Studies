package com.umut.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umut.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener() // gerek kalmadi
        { view, keyCode, _ ->
            handleKeyEvent(view, keyCode)
        }
    }


    private fun calculateTip() {

        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val tipAmount: Double
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            resetTip()
            Toast.makeText(
                this, "You can only enter numbers. Also you cannot leave empty.", Toast.LENGTH_SHORT
            ).show()
        } else {
            val tipPercentage = when (binding.tipSelection.checkedRadioButtonId) {
                R.id.amazing_radiobutton -> 0.2
                R.id.good_radiobutton -> 0.175
                else -> 0.15
            }

            tipAmount = if (binding.roundSwitch.isChecked) {
                ceil(cost * tipPercentage)
            } else {
                (cost * tipPercentage)
            }
            setTip(tipAmount)
        }

    }

    private fun resetTip() {
        binding.textTipResult.text =
            getString(R.string.tip_amount_text, NumberFormat.getCurrencyInstance().format(0.0))
    }

    private fun setTip(tipAmount: Double) {
        binding.textTipResult.text = getString(
            R.string.tip_amount_text, NumberFormat.getCurrencyInstance().format(tipAmount)
        )
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}