package com.umut.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Dice(private val sideNumber: Int) {
    fun roll(): Int {
        return (1..sideNumber).random()
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "Create 1")
        setContentView(R.layout.activity_main)
        Log.e("MainActivity", "Create 2")
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceImage: ImageView = findViewById(R.id.DiceImage)
        val diceNumber = Dice(6).roll()
        val drawableResource = when (diceNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_1
        }
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceNumber.toString()
        Toast.makeText(this,"The lucky number is: $diceNumber", Toast.LENGTH_SHORT).show()
    }
}



