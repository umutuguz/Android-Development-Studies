package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val TAG = "GameFragment"

class GameViewModel : ViewModel() {

    private lateinit var currentWord: String
    private var wordList: MutableList<String> = mutableListOf()

    init {
        Log.d(TAG, "GameViewModel created!")
        getNextWord()
    }

//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG, "GameViewModel destroyed!")
//    }

    private var _score = MutableLiveData(0)
    private var _currentWordCount = MutableLiveData(0)
    private val _currentScrambledWord = MutableLiveData<String>()

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    val score: LiveData<Int>
        get() = _score

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private fun getNextWord() {

        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (allWordsList.contains(currentWord)) {
//            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = _currentWordCount.value?.inc()
            wordList.add(currentWord)
        }
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        }else false
    }

    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)

    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordList.clear()
        getNextWord()
    }

}



