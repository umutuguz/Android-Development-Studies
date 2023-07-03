package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

const val TAG = "GameFragment"

class GameViewModel : ViewModel() {

    private lateinit var currentWord: String
    private var wordList: MutableList<String> = mutableListOf()

    init {
        Log.d(TAG, "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel destroyed!")
    }

    private var _score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord: String

    val currentScrambledWord: String
        get() = _currentScrambledWord

    val score: Int
        get() = _score

    val currentWordCount
        get() = _currentWordCount

    private fun getNextWord() {

        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (tempWord.toString() == currentWord) {
            tempWord.shuffle()
        }

        if (allWordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = tempWord.toString()
            ++_currentWordCount
            wordList.add(currentWord)
        }


    }

    fun nextWord(): Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        }else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

}



