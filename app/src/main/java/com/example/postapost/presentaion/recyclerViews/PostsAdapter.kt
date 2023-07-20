package com.example.postapost.presentaion.recyclerViews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postapost.data.models.Post
import com.example.postapost.databinding.PostBinding

class PostsAdapter() : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    inner class PostsViewHolder(val binding: PostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsAdapter.PostsViewHolder {
        return PostsViewHolder(
            PostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: PostsAdapter.PostsViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.binding.apply {
            postTitle.text = post.title
            postBody.text = post.body
            var tags = ""
            for (i in post.tags) {
                tags += "#$i "
            }
            tags.trimEnd()
            postTags.text = tags
            postReactions.text = post.reactions.toString()
        }

    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}