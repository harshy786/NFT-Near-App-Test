package com.android.nearlabs.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val name:String, val email:String, var isSelected:Boolean = false) : Parcelable
