package com.wolf.rxshopper.data.preferences

import android.content.SharedPreferences
import com.wolf.rxshopper.data.preferences.PreferencesKeys.Default
import com.wolf.rxshopper.data.preferences.PreferencesKeys.Sensitive

interface PreferencesRepository {
    var username: String
    var email: String
    var password: String
}

class PreferencesRepositoryImpl(
    private val default: SharedPreferences,
    private val encrypted: SharedPreferences
): PreferencesRepository {
    override var username: String
        get() = default.getString(Default.USER_NAME, PreferencesConstants.DEFAULT_STRING)!!
        set(value) {
            default.edit().putString(Default.USER_NAME, value).apply()
        }
    override var email: String
        get() = encrypted.getString(Sensitive.EMAIL, PreferencesConstants.DEFAULT_STRING)!!
        set(value) {
            encrypted.edit().putString(Sensitive.EMAIL, value).apply()
        }
    override var password: String
        get() = encrypted.getString(Sensitive.PASSWORD, PreferencesConstants.DEFAULT_STRING)!!
        set(value) {
            encrypted.edit().putString(Sensitive.PASSWORD, value).apply()
        }
}