/*
package com.wolf.rxshopper.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import com.wolf.rxshopper.presentation.utils.Event
import java.lang.IllegalStateException
import kotlin.jvm.Throws

object NetworkConnectivity {
    private lateinit var connectivityManager: ConnectivityManager
    val networkStatus= MutableLiveData<Event<NetworkStatus>>()

    private var isRegistered: Boolean = false

    fun initialize(context: Context) {
        connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        register()
    }

    fun register() {
        if (!isRegistered) {
            isRegistered = true
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
                .build()

            connectivityManager.requestNetwork(request, NetworkCallback)
        }
    }

    fun unregister() {
        if (this::connectivityManager.isInitialized) {
            isRegistered = false
            connectivityManager.unregisterNetworkCallback(NetworkCallback)
        }
    }

    @Throws(IllegalStateException::class)
    fun isConnected(): Boolean {
        if (this::connectivityManager.isInitialized) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                return hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        } else {
            throw IllegalArgumentException("NetworkConnectivity was not initialized")
        }
        return false
    }

    private object NetworkCallback: ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)

            when(networkStatus.value?.peekContent()) {
                NetworkStatus.UNAVAILABLE -> networkStatus.value = Event(NetworkStatus.CHANGED)
                else -> networkStatus.value = Event(NetworkStatus.AVAILABLE)
            }
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            networkStatus.value = Event(NetworkStatus.UNAVAILABLE)
        }
    }

    enum class NetworkStatus {
        AVAILABLE, UNAVAILABLE, CHANGED
    }
}*/
