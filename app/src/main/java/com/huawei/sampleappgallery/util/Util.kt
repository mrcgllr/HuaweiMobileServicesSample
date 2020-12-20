package com.huawei.sampleappgallery.util

import android.content.Context
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

object Util {

    fun isHmsAvailable(context: Context): Boolean {
        var isAvailable = false
        val result: Int = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context)
        isAvailable = com.huawei.hms.api.ConnectionResult.SUCCESS == result
        Log.d("RegisterActivity", "isHmsAvailable: $isAvailable")
        return isAvailable
    }

    fun isGmsAvailable(context: Context): Boolean {
        var isAvailable = false
        val result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        isAvailable = ConnectionResult.SUCCESS == result
        Log.d("RegisterActivity", "isGmsAvailable: $isAvailable")
        return isAvailable
    }
}