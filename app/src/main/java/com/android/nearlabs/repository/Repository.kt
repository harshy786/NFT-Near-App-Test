package com.android.nearlabs.repository

import com.android.nearlabs.api.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}