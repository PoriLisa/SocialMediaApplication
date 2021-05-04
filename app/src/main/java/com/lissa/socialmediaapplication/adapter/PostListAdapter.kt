package com.lissa.socialmediaapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lissa.socialmediaapplication.R
import com.lissa.socialmediaapplication.socialMediadataClass.PostEntityDataClass
import java.net.URI.create

class PostListAdapter :
    ListAdapter<PostEntityDataClass, PostListAdapter.PostListViewHolder>(WORDS_COMPARATOR) {
    class PostListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postText: TextView = itemView.findViewById(R.id.tv_post)
        val postedBy: TextView = itemView.findViewById(R.id.tv_posted_by)
        fun bind(current: PostEntityDataClass?) {
            postText.text = current!!.post
            postedBy.text = current!!.postedByUserName
        }

        companion object {
            fun create(parent: ViewGroup): PostListViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return PostListViewHolder(view)
            }
        }

    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<PostEntityDataClass>() {
            override fun areItemsTheSame(
                oldItem: PostEntityDataClass,
                newItem: PostEntityDataClass
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: PostEntityDataClass,
                newItem: PostEntityDataClass
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)


    }


}