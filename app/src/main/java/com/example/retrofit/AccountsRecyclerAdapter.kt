package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemBinding
import com.squareup.picasso.Picasso

/**
 * @Author: Temur
 * @Date: 13/06/2022
 */

class AccountsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = ArrayList<GithubAccount>()

    fun submitData(list: List<GithubAccount>) {
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Vh).onBind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}

private class Vh(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(githubAccount: GithubAccount) {
        binding.nameTv.text = githubAccount.login
        Picasso.get().load(githubAccount.avatarUrl).into(binding.image)
    }
}