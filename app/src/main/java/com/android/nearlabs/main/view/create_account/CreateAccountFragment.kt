package com.android.nearlabs.main.view.create_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.nearlabs.R
import com.android.nearlabs.core.BaseFragment
import com.android.nearlabs.databinding.FragmentCreateAccountBinding


class CreateAccountFragment : BaseFragment() {
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            topBarInclude.backButton.setOnClickListener { findNavController().popBackStack() }
            topBarInclude.titleBar.text = "Create NEAR Account"
            createAccountButton.setOnClickListener { findNavController().navigate(R.id.action_CreateAccountFragment_to_sendGiftFragment) }
            loginButton.setOnClickListener { findNavController().navigate(R.id.action_CreateAccountFragment_to_sendGiftFragment) }
        }
    }
}