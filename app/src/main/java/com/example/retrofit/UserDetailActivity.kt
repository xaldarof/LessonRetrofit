package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.databinding.ActivityUserDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserDetailActivity : AppCompatActivity(), OnUserDetailViewClickListener {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.extras?.getString("username")

        val adapter = AccountsRecyclerAdapter(this)
        binding.followerRv.adapter = adapter
        binding.followerRv.layoutManager

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

                }
            })


            retrofit?.getFollowersByUserName(username)?.enqueue(object : Callback,
                retrofit2.Callback<List<GithubAccount>> {
                override fun onResponse(
                    call: Call<List<GithubAccount>>,
                    response: Response<List<GithubAccount>>,
                ) {
                    response.body()?.let {
                        binding.followersCountTv.text = it.size.toString()
                        adapter.submitData(it)
                    }
                }

                override fun onFailure(call: Call<List<GithubAccount>>, t: Throwable) {

                }
            })
        }
    }


    private fun initViews(githubAccount: GithubAccount) {
        Picasso.get().load(githubAccount.avatarUrl)
            .into(binding.avatar)
        binding.usernameTv.text = githubAccount.login
    }

    override fun on(githubAccount: GithubAccount) {

    }
}