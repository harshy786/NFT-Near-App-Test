package com.android.nearlabs.main.view.send_gift

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.nearlabs.adapter.UserGiftAdapter
import com.android.nearlabs.databinding.FragmentSendGiftBinding
import com.android.nearlabs.main.viewmodel.SendGiftViewModel
import com.android.nearlabs.model.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SendGiftFragment : Fragment() {
    private lateinit var binding: FragmentSendGiftBinding
    private val viewModel : SendGiftViewModel by viewModels()
    private var userList:List<User> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSendGiftBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadUserList()
        binding.apply {
            topBarInclude.titleBar.text = "Gift an NFT"
            topBarInclude.backButton.setOnClickListener { findNavController().popBackStack() }
            userListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = UserGiftAdapter()
            }
            searchInputEditText.doOnTextChanged { text, start, before, count ->
                if(text.toString().isNotEmpty()){
                    var list = mutableListOf<User>()
                    for (user in userList) {
                        if(user.name.contains(text.toString(),true)){
                            list.add(user)
                        }
                    }
                    showUserList(list)
                }else{
                    showUserList(userList)
                }
            }
            sendGiftButton.setOnClickListener {
                val action = SendGiftFragmentDirections.actionSendGiftFragmentToHomeFragment(
                    userList.toTypedArray()
                )
                findNavController().navigate(action)
            }
        }

        viewModel.getUsers().observe(this as LifecycleOwner, { users ->
            users?.let {
                userList = it
                showUserList(it)
            }
        })

    }

    fun showUserList(list:List<User>){
        binding.userListRecyclerView.apply {
            with(adapter as UserGiftAdapter) {
                userList = list
                notifyDataSetChanged()
            }
        }
    }

}