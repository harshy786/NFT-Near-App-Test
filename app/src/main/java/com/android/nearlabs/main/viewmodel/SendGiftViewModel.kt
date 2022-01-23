package com.android.nearlabs.main.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.nearlabs.model.User
import com.android.nearlabs.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendGiftViewModel @Inject constructor(private val repository: Repository, @ApplicationContext private val context: Context) : ViewModel(){
    private val usersLiveData = MutableLiveData<List<User>?>()

    fun getUsers() = usersLiveData

    fun loadUserList(){
        viewModelScope.launch {
            val users = repository.getUsers()
            when(users.isSuccessful) {
                true -> {
                    with(users.body().orEmpty()) {
                        usersLiveData.postValue(users.body())
                    }
                }
                else -> {
                    Log.d("User List", users.message())
                }
            }
        }
    }
}