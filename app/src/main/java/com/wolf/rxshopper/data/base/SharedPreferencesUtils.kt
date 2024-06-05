package com.wolf.rxshopper.data.base

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SharedPreferencesUtils {
    const val ENCRYPTED_SHARED_PREFERENCES = "sensitive"
    const val SHARED_PREFERENCES = "default"

    private val masterKeyAlias by lazy {
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    fun Context.newEncryptedSharedPreferences(
        fileName: String = ENCRYPTED_SHARED_PREFERENCES
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            fileName,
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun Context.newSharedPreferences(fileName: String = SHARED_PREFERENCES): SharedPreferences {
        return getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }
}