package com.android.nearlabs.core

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment()  {
    protected lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}