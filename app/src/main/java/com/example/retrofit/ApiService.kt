package com.example.retrofit

import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @Author: Temur
 * @Date: 12/06/2022
 */

interface ApiService {


    @GET("search/users")
    fun getUsersByName(@Query("q") query: String): retrofit2.Call<List<String>>


}