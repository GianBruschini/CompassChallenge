package com.compass.compasschallenge.data.feature.users

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class ContentLocalDataSource(context: Context, private val gson: Gson) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            PREF_FILE_NAME,
            MODE,
        )

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }



    private var wordCount: String?
        get() = preferences.getString(PREF_WORD_COUNT, null)
        set(value) {
            preferences.edit {
                it.putString(PREF_WORD_COUNT, value)
            }
        }

    private var every10thCharacter: String?
        get() = preferences.getString(PREF_EVERY_10TH_CHAR, null)
        set(value) {
            preferences.edit {
                it.putString(PREF_EVERY_10TH_CHAR, value)
            }
        }



    fun saveWordCount(wordCount: String) {
        this.wordCount = wordCount
    }

    fun getWordCounts(): String? {
        return try {
            wordCount
        } catch (e: Exception) {
            null
        }
    }

    fun saveEvery10thCharacter(every10thCharacter: String) {
        this.every10thCharacter = every10thCharacter
    }

    fun getEvery10thCharacters(): String? {
        return try {
            every10thCharacter
        } catch (e: Exception) {
            null
        }
    }

    fun clear() {
        wordCount = null
        every10thCharacter = null
    }

    companion object {
        private const val MODE = Context.MODE_PRIVATE
        private const val PREF_FILE_NAME = "userLocalDataSource"
        private const val PREF_WORD_COUNT = "wordCount"
        private const val PREF_EVERY_10TH_CHAR = "every10thCharacter"
    }
}
