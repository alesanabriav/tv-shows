package com.developersoul.yt

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_item.view.*

class MainAdapter(val schedule: List<Schedule>) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return schedule.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.video_item, parent, false)
        return CustomViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val schedule = schedule[position]
        holder.bindViewContent(schedule)
    }
}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val DEBUG_TAG = "CustomViewHolder"
    var schedule: Schedule? = null

    init {
       view.setOnClickListener {
           val intent = Intent(view.context, ShowEpisodesActivity::class.java)
           Log.e("TO", this.schedule?.id)
           intent.putExtra("name", this.schedule?.show?.name)
           intent.putExtra("id", this.schedule?.show?.id)
           view.context.startActivity(intent)
       }
    }

    fun bindViewContent(schedule: Schedule) {
        this.schedule = schedule
        val text = schedule.name

        view.setBackgroundColor(Color.parseColor("#F3F3F6"))
        Picasso.get()
            .load(schedule.show.image.medium.replace("http", "https"))
            .into(view.myImageView)

        view.videoTitle.text = text
        view.videoTitle.setTextColor(Color.parseColor("#131C23"))
    }

}