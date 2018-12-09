package com.anda.myapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class Utils @Inject constructor(val context: Context) {

    fun isOnline(): Boolean {
        var connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val networkInfo = connectivity.allNetworkInfo
        for (info in networkInfo.indices) {
            if (networkInfo[info].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }

        return false
    }
}