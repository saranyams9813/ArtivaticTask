package com.example.artivaticTask.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.artivaticTask.R
import com.example.artivaticTask.data.model.Feed
import com.example.artivaticTask.databinding.ItemFeedLytBinding


class FeedsListAdapter(var feeds:ArrayList<Feed>?):RecyclerView.Adapter<FeedsListAdapter.ListViewHolder>(){
    inner class ListViewHolder(var binding: ItemFeedLytBinding):RecyclerView.ViewHolder(binding.root)

    fun updateData(updatedFeeds:ArrayList<Feed>?){
        feeds=updatedFeeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemFeedLytBinding.inflate(LayoutInflater.from(parent.context))
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return feeds?.size?:0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item = feeds?.get(position)
        holder.binding?.feedTitle?.text=item?.title
        holder.binding?.feedDesc?.text=item?.description
        if(!item?.imageHref.isNullOrBlank()){
            holder.binding.feedImg.visibility=VISIBLE
            Log.d("ggggg",item?.imageHref?:"null")
            Glide.with(holder.binding.feedImg.context)
                .load(item?.imageHref)
                .placeholder(R.drawable.img_place_holder)
                .error(R.drawable.img_error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(holder.binding.feedImg)


        }
        else
            holder.binding.feedImg.visibility=GONE
    }
}

