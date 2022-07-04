package com.example.retrofit.data.network

import com.example.retrofit.AccountBaseResponse
import com.example.retrofit.GithubAccount
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


/**
 * @Author: Temur
 * @Date: 12/06/2022
 */

interface ApiService {


    @GET("search/users")
    suspend fun getUsersByName(@Query("q") query: String): AccountBaseResponse


    @GET("users/{username}")
    fun getInfoByUserName(@Path("username") username: String): Call<GithubAccount>


    @GET("users/{username}/followers")
    fun getFollowersByUserName(@Path("username") username: String): Call<List<GithubAccount>>

    @Multipart
    @POST("users/{username}/followers")
    fun sendPhoto(createFormData: MultipartBody.Part): Call<List<GithubAccount>>


}