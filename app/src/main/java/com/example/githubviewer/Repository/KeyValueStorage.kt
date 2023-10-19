package com.example.githubviewer.Repository

import android.content.SharedPreferences

class KeyValueStorage(private val sharedPreferences: SharedPreferences) {
    var authToken: String?
        get() = sharedPreferences.getString("authToken", null)
        set(value) {
            sharedPreferences.edit().putString("authToken", value).apply()
        }
}
