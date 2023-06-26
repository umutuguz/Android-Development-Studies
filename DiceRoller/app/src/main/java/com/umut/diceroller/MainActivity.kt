package com.umut.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Dice(private val sideNumber: Int) {
    fun roll(): Int {
        return (1..sideNumber).random()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("uguz", "Create 1")
        setContentView(R.layout.activity_main)
        Log.e("uguz", "Create 2")
        val rollButton: Button = findViewById(R.id.button)
        val resultTextView: TextView = findViewById(R.id.textView)
        rollButton.setOnClickListener {
            resultTextView.text = rollDice()
        }
    }

    private fun rollDice() = Dice(6).roll().toString()

//    override fun onResume() {
//        super.onResume()
//        val rollButton: Button = findViewById(R.id.button)
//        rollButton.setOnClickListener {
//
//            val resultTextView: TextView = findViewById(R.id.textView)
//            resultTextView.text = rollDice()
//        }
//
//    }

}