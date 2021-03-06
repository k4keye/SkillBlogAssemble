package com.example.skillblogassemble.presenter


import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.skillblogassemble.R
import com.example.skillblogassemble.domain.model.BlogItem

class ViewPagerAdapter(private val items:List<BlogItem>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {


    var onItemClickListener : ((Int)-> Unit)? = null

    inner class ViewPagerHolder(view : View) : RecyclerView.ViewHolder(view){

        var item_ground : LinearLayout = view.findViewById(R.id.item_ground)
        var item_company : TextView = view.findViewById(R.id.item_company)
        var item_title : TextView = view.findViewById(R.id.item_title)
        var item_context : TextView = view.findViewById(R.id.item_context)
        var item_icon : ImageView = view.findViewById(R.id.item_icon)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewPagerHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item,parent,false)

        return ViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {

        //기존의 drawable 을 복사한다.
        val drawable = ((holder.item_ground.background ).mutate().constantState?.newDrawable()) as GradientDrawable

        drawable.colors = intArrayOf(
            items[position].color + 0x68,
            items[position].color + 0x46,
            items[position].color
        )
        holder.item_ground.background = drawable
        holder.item_company.text = items[position].company
        holder.item_title.text = items[position].title
        holder.item_context.text = items[position].content

        holder.itemView.setOnClickListener {
            this.onItemClickListener?.invoke(position)

        }
        holder.item_icon.setImageResource(items[position].iconId)
    }

}