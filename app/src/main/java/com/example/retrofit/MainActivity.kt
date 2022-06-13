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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiClient.getClient()?.create(ApiService::class.java)?.getUsersByName("Game")
            ?.enqueue(object : Callback,
                retrofit2.Callback<List<String>> {

                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>,
                ) {
                    if (response.isSuccessful) {
                        Log.d("res", "Response = ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {

                }

            })
    }
}