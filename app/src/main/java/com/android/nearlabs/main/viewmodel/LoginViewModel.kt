package com.android.nearlabs.main.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import com.android.nearlabs.R
import com.android.nearlabs.Utility.Utility
import com.android.nearlabs.model.TuplesReturn
import com.android.nearlabs.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository, @ApplicationContext private val context: Context) : ViewModel() {

    fun checkEmailValidation(emailAddress:String) : TuplesReturn {
        return if(emailAddress.isEmpty()) {
            TuplesReturn(false, context.getString(R.string.email_address_empty))
        }else if(!Utility.isValidEmail(emailAddress)) {
            TuplesReturn(false, context.getString(R.string.email_address_wrong))
        }else {
            TuplesReturn(true,"")
        }
    }

    fun checkPhoneValidation(phoneNumber:String) : TuplesReturn {
        return if(phoneNumber.isEmpty()) {
            TuplesReturn(false, context.getString(R.string.phone_empty))
        }else if(!Utility.isValidPhone(phoneNumber)) {
            TuplesReturn(false, context.getString(R.string.phone_wrong))
        }else {
            TuplesReturn(true,"")
        }
    }
}