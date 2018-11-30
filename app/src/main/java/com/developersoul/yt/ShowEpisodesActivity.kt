package com.developersoul.yt

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_show_episodes.*
import okhttp3.*
import java.io.IOException

class ShowEpisodesActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_episodes)
        episodesRecyclerView.layoutManager = LinearLayoutManager(this)
        supportActionBar?.title = intent.getStringExtra("name")

    }

    override fun onStart() {
        super.onStart()
        val id = intent.getStringExtra("id")
        getEpisodes(id)
    }

    fun getEpisodes(id: String?) {
        val url = "http://api.tvmaze.com/shows/$id/episodes"
        val client = OkHttpClient()
        val req = Request.Builder().url(url).build()
        client.newCall(req).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = Gson()
                val episodes: List<Episode> = gson.fromJson(body,  object : TypeToken<List<Episode>>(){}.type)

                runOnUiThread {
                    episodesRecyclerView.adapter = ShowEpisodesAdapter(episodes)
                }

            }

            override fun onFailure(call: Call, e: IOException) {

            }
        })
    }
}