package com.example.retrofit.domain.repositories

import com.example.retrofit.AccountBaseResponse
import com.example.retrofit.GithubAccount
import com.example.retrofit.data.network.ApiClient
import com.example.retrofit.data.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * @Author: Temur
 * @Date: 04/07/2022
 */

//        service?.sendPhoto(
//            MultipartBody.Part.createFormData(
//                "file",
//                "path",
//                File("path").asRequestBody("image/jpeg".toMediaType())
//            )
//        )

class UserRepository {

    private val service = ApiClient.getClient()?.create(ApiService::class.java)

    fun getUsersByUsername(
        username: String,
        onSuccess: (List<GithubAccount>) -> Unit,
        onFail: (Throwable) -> Unit,
        validationError: (String) -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            if (username.isEmpty()) {
                validationError.invoke("Username is empty please write something...")
            } else {
                try {
                    val response = service?.getUsersByName(username)

                    response?.items?.let { onSuccess.invoke(it) }
                } catch (e: Exception) {
                    onFail.invoke(e)
                }
            }
        }
    }
}