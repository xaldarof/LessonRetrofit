package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.databinding.ActivityUserDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.extras?.getString("username")

        val retrofit = ApiClient.getClient()?.create(ApiService::class.java)

        if (username != null) {
            retrofit?.getInfoByUserName(username)?.enqueue(object : Callback,
                retrofit2.Callback<GithubAccount> {
                override fun onResponse(
                    call: Call<GithubAccount>,
                    response: Response<GithubAccount>,
                ) {
                    response.body()?.let { initViews(it) }
                }

                override fun onFailure(call: Call<GithubAccount>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    private fun initViews(githubAccount: GithubAccount) {
        Picasso.get().load(githubAccount.avatarUrl)
            .into(binding.avatar)
        binding.usernameTv.text = githubAccount.login
    }
}