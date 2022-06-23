package com.example.retrofit

import retrofit2.http.*


/**
 * @Author: Temur
 * @Date: 12/06/2022
 */

interface ApiService {


    @GET("search/users")
    fun getUsersByName(@Query("q") query: String): retrofit2.Call<AccountBaseResponse>


    @GET("users/{username}")
    fun getInfoByUserName(@Path("username") username: String): retrofit2.Call<GithubAccount>


    @GET("users/{username}/followers")
    fun getFollowersByUserName(@Path("username") username: String): retrofit2.Call<List<GithubAccount>>


}