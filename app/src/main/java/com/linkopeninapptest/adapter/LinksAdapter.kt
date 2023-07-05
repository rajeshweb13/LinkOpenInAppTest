package com.linkopeninapptest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.linkopeninapptest.R
import com.linkopeninapptest.data.RecentLink
import com.linkopeninapptest.data.TopLink
import com.squareup.picasso.Picasso


class LinksAdapter(val context: Context, val topLink: List<TopLink>, val recentLink: List<RecentLink>, var isRecentLink:Boolean):
    RecyclerView.Adapter<LinksAdapter.ViewHolder>() {

    var itemClickTopLinkListner:((TopLink) -> Unit)? = null
    var itemClickRecentLinkListner:((RecentLink) -> Unit)? = null
    var isRec:Boolean = false

    companion object {
        var isRecentLinkFromMain = false
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.link_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (isRecentLinkFromMain) {
            recentLink.size
        } else {
            topLink.size
        }

    }

    fun setIsRecentLink(boolean: Boolean){
        isRecentLink = boolean
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (isRecentLinkFromMain) {

                var data = recentLink[position]
                holder.name.text = data.title
                holder.date.text = data.created_at
                holder.price.text = data.total_clicks.toString()
                holder.originaLink.text = data.web_link
                Picasso.get().load(data.original_image).centerCrop().resize(20, 20)
                    .into(holder.linkLogo);

                holder.nameLayout.setOnClickListener {
                    itemClickRecentLinkListner?.invoke(data)
                }
            } else {
                var data = topLink[position]
                holder.name.text = data.title
                holder.date.text = data.created_at
                holder.price.text = data.total_clicks.toString()
                holder.originaLink.text = data.web_link
                Picasso.get().load(data.original_image).centerCrop().resize(20, 20)
                    .into(holder.linkLogo);

                holder.nameLayout.setOnClickListener {
                    itemClickTopLinkListner?.invoke(data)
                }
            }

    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

        val linkLogo: AppCompatImageView = itemView.findViewById(R.id.logo)
        val name: AppCompatTextView = itemView.findViewById(R.id.linkName)
        val date: AppCompatTextView = itemView.findViewById(R.id.linkDate)
        val price: AppCompatTextView = itemView.findViewById(R.id.price)
        val click : AppCompatTextView = itemView.findViewById(R.id.clickk)
        val originaLink: AppCompatTextView = itemView.findViewById(R.id.originalLink)
        val copy: AppCompatImageView = itemView.findViewById(R.id.copy)
        val nameLayout: LinearLayout = itemView.findViewById(R.id.linkeLayout)
    }

}