package com.example.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.retrofit.*
import com.example.retrofit.data.network.ApiClient
import com.example.retrofit.data.network.ApiService
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(), OnUserDetailViewClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: AccountsRecyclerAdapter
    private val repository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        rvAdapter = AccountsRecyclerAdapter(this)
        binding.rv.adapter = rvAdapter

        binding.appCompatEditText.addTextChangedListener {
            searchUser(it.toString())
        }
    }

    private fun searchUser(username: String) {
        resetUi()

        repository.getUsersByUsername(username,
            onSuccess = {
                buildRecyclerView(it)
                binding.resultsCountTv.text = it.size.toString()
            }, onFail = {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }, validationError = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
    }

    private fun resetUi() {
        rvAdapter.submitData(emptyList())
        binding.progress.isVisible = true
        binding.resultsCountTv.text = null
    }

    private fun buildRecyclerView(accounts: List<GithubAccount>) {
        binding.progress.isVisible = false
        rvAdapter.submitData(accounts)

    }

    override fun on(githubAccount: GithubAccount) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("username", githubAccount.login)
        startActivity(intent)
    }
}
