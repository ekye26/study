package com.example.http_network.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.http_network.databinding.ItemMainBinding
import com.example.http_network.model.ItemModel


class MyViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(private val context: Context, private val datas: MutableList<ItemModel>?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding

        //리사이클러뷰의 어댑터 역할을 하는 클래스
        val model=datas!![position]
        binding.itemTitle.text="제목 : ${model.title}"
        binding.itemDesc.text=model.description
        binding.itemTime.text=" 작성자 & 올린 시간 : ${model.author} & ${model.publishedAt}"

        //Glide를 이용하여 이미지를 가져와 출력
        Glide.with(context)
            .load(model.urlToImage)
            .into(binding.itemImage)


    }
}