package com.developersoul.yt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    fun fetchJson() {
        val url = "https://api.tvmaze.com/schedule?country=US"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val gson = Gson()
                val schedule: List<Schedule> = gson.fromJson(body,  object : TypeToken<List<Schedule>>(){}.type)

                runOnUiThread {
                    myRecyclerView.adapter = MainAdapter(schedule)
                }

                schedule.forEach { Log.e("show", it.show.image.medium) }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("err fetchJson", "", e)
            }
        })

    }
}

