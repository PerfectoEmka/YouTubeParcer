package com.example.kotlinl5.data.local

import android.content.Context

class AppPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("youtube_api", Context.MODE_PRIVATE)

    var isOnBoard: Boolean
    get() = prefs.getBoolean("onBoard", false)
    set(value) = prefs.edit().putBoolean("onBoard", value).apply()
}