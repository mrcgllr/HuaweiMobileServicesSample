package com.huawei.sampleappgallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huawei.agconnect.auth.EmailAuthProvider
import com.huawei.agconnect.auth.VerifyCodeSettings
import java.util.*

class LoginViewModel:ViewModel(){

    private var _isSendVerify = MutableLiveData<Boolean>()
    val isSendVerify:LiveData<Boolean> get() = _isSendVerify

    private var _toastMessage= MutableLiveData<String>()
    val toastMessage:LiveData<String> get() = _toastMessage


    fun hmsSendVerify(email: String) {
        val settings = VerifyCodeSettings.newBuilder()
            .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
            .sendInterval(30)
            .locale(Locale.ENGLISH)
            .build()
        val request = EmailAuthProvider.requestVerifyCode(email,settings)
        request.addOnSuccessListener {
            _isSendVerify.value =true
            _toastMessage.value = "Send verify code your e mail address"

        }.addOnFailureListener {
            _isSendVerify.value = false
            _toastMessage.value = it.localizedMessage
        }
    }
}