package com.android.nearlabs.main.view.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.nearlabs.R
import com.android.nearlabs.Utility.Constant
import com.android.nearlabs.Utility.Utility
import com.android.nearlabs.core.BaseFragment
import com.android.nearlabs.databinding.FragmentNearLabsLoginBinding
import com.android.nearlabs.main.viewmodel.LoginViewModel
import com.android.nearlabs.model.TuplesReturn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NearLabsLoginFragment : BaseFragment() {
    private lateinit var binding: FragmentNearLabsLoginBinding
    private var isEmailSelected:Boolean = true
    private val longViewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNearLabsLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Utility.getLoginTermAndConditionTextView(termAndConditions,requireContext())
            groupButtonToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
                if(checkedId == R.id.emailButton) {
                    setStartEditText(true)
                }else if(checkedId == R.id.phoneButton) {
                    setStartEditText(false)
                }
            }

            getStartButton.setOnClickListener {
                lateinit var result:TuplesReturn
                val userInput =  userInputEditText.text.toString().trim()

                result = if(isEmailSelected){
                    longViewModel.checkEmailValidation(userInput)
                }else{
                    longViewModel.checkPhoneValidation(userInput)
                }

                if(result.isSuccess){
                    bundle = Bundle().apply {
                        putBoolean(Constant.SELECTED_TYPE, isEmailSelected)
                        putString(Constant.VALUE, userInput)
                    }
                    findNavController().navigate(R.id.action_userLoginFragment_to_verificationFragment,bundle)
                }else{
                    Toast.makeText(requireContext(),result.message,Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun setStartEditText(isEmail:Boolean){
        binding.apply {
            userInputEditText.setText("")
            if(isEmail) {
                isEmailSelected = true
                userInputEditText.hint = "Email Address"
                userInputEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }else{
                isEmailSelected = false
                userInputEditText.hint = "Ex. (373) 378 8383"
                userInputEditText.inputType = InputType.TYPE_CLASS_PHONE
            }
        }
    }

}