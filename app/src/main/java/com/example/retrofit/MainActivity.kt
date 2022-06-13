package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: AccountsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        rvAdapter = AccountsRecyclerAdapter()
        binding.rv.adapter = rvAdapter

        ApiClient.getClient()?.create(ApiService::class.java)?.getUsersByName("Game")
            ?.enqueue(object : Callback,
                retrofit2.Callback<AccountBaseResponse> {
                override fun onResponse(
                    call: Call<AccountBaseResponse>,
                    response: Response<AccountBaseResponse>) {

                    response.body()?.items?.let {
                        buildRecyclerView(it)
                    }
                }

                override fun onFailure(call: Call<AccountBaseResponse>, t: Throwable) {

                }
            })
    }

    private fun buildRecyclerView(accounts: List<GithubAccount>) {
        rvAdapter.submitData(accounts)
    }
}