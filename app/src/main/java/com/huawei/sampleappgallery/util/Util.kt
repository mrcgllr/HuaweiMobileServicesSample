package com.huawei.sampleappgallery.util

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

object Util {

    fun isHmsAvailable(context: Context): Boolean {
        val isAvailable :Boolean
        val result: Int = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context)
        isAvailable = com.huawei.hms.api.ConnectionResult.SUCCESS == result
        return isAvailable
    }

    fun isGmsAvailable(context: Context): Boolean {
        val isAvailable :Boolean
        val result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        isAvailable = ConnectionResult.SUCCESS == result
        return isAvailable
    }
}