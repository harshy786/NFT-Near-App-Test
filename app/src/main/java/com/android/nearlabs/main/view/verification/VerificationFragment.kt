package com.android.nearlabs.main.view.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.nearlabs.R
import com.android.nearlabs.Utility.Constant
import com.android.nearlabs.databinding.FragmentVerificationBinding

class VerificationFragment : Fragment() {
    private var selectedType:Boolean = false
    private  var values:String = ""
    private lateinit var binding: FragmentVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            selectedType = it.getBoolean(Constant.SELECTED_TYPE)
            values = it.getString(Constant.VALUE, "")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVerificationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            verificationSubTitleTextView.text = values
            if(selectedType){
                verificationTitleTextView.text = requireActivity().getString(R.string.email_message)
                sendAgainTextView.text = requireActivity().getString(R.string.different_email)
            }else{
                verificationTitleTextView.text = requireActivity().getString(R.string.phone_message)
                sendAgainTextView.text = requireActivity().getString(R.string.different_phone)
            }

            topBarInclude.backButton.setOnClickListener { findNavController().popBackStack() }

            continueButton.setOnClickListener { findNavController().navigate(R.id.action_verificationFragment_to_CreateAccountFragment) }
        }
    }
}