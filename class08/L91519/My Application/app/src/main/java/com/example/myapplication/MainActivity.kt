package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val clientId = "iUus1XfFW9mDHFp7chUz"
    val cliendPw = "rJ_ZOUhfVD"
    val adapter = MovieRecyclerViewAdpater() { link ->
        startActivity(
            Intent(applicationContext, MovieDatailActivity::class.java)
                .putExtra("link", link)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.adapter = adapter
        rv_movie_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_movie_list.hasFixedSize()

        btn_movie_search.setOnClickListener {
            findMovie(et_movie.text.toString())
        }
    }

    private fun findMovie(query: String) {
        ApiClient.getService().searchMovie(query).enqueue(object : Callback<MovieResult> {
            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                print("fail")
            }

            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                //recyclerview data init
                print("sucess")
                adapter.setItems(response.body()?.items as ArrayList<MovieResult.Item>)
            }

        })
    }
}
