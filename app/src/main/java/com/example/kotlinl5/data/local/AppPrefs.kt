package com.example.kotlinl5.data.local

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefsModule = module {
    single { AppPrefs(androidContext()) }
}

class AppPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("youtube_api", Context.MODE_PRIVATE)

    var isOnBoard: Boolean
    get() = prefs.getBoolean("onBoard", false)
    set(value) = prefs.edit().putBoolean("onBoard", value).apply()
}