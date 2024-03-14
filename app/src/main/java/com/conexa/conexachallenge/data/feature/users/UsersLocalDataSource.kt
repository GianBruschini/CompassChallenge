package com.conexa.conexachallenge.data.feature.users

import android.content.Context
import android.content.SharedPreferences
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UsersLocalDataSource(context: Context, private val gson: Gson) {

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

    private var usersList: List<UserResponse>?
        get() {
            val json = preferences.getString(PREF_USERS_LIST, null)
            return if (json != null) {
                gson.fromJson(json, object : TypeToken<List<UserResponse>>() {}.type)
            } else {
                null
            }
        }
        set(value) {
            preferences.edit {
                it.putString(PREF_USERS_LIST, gson.toJson(value))
            }
        }

    fun save(users: List<UserResponse>) {
        usersList = users
    }

    fun get(): List<UserResponse>? {
        return try {
            usersList
        } catch (e: Exception) {
            null
        }
    }

    fun clear() {
        usersList = null
    }

    companion object {
        private const val MODE = Context.MODE_PRIVATE
        private const val PREF_FILE_NAME = "userLocalDataSource"
        private const val PREF_USERS_LIST = "userList"
    }
}
