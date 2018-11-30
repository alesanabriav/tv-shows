package com.developersoul.yt

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.show_episode.view.*

class ShowEpisodesAdapter(val episodes: List<Episode>) : RecyclerView.Adapter<ShowEpisodeHolder>() {
    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ShowEpisodeHolder {
        val lf = LayoutInflater.from(parent.context)
        val v = lf.inflate(R.layout.show_episode, parent, false)
        return ShowEpisodeHolder(v)
    }

    override fun onBindViewHolder(holder: ShowEpisodeHolder, position: Int) {
        val episode = episodes[position]
        holder.view.titleTextView.text = episode.name
        holder.view.summaryTextView.text = episode.summary
        Picasso.get().load(episode.image?.medium).into(holder.view.imageView)
    }

}

class ShowEpisodeHolder(val view: View) : RecyclerView.ViewHolder(view) {

}