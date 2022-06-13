package com.example.retrofit

import com.google.gson.annotations.SerializedName

/**
 * @Author: Temur
 * @Date: 13/06/2022
 */


data class AccountBaseResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<GithubAccount>,
)

data class GithubAccount(
    val login: String,
    val id: Int,
    @SerializedName(value = "avatar_url")
    val avatarUrl: String,
)