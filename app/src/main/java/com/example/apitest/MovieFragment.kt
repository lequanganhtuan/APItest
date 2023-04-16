package com.example.apitest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.URL

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    private val movies = mutableListOf<Movie>()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MovieAdapter(movies)

        Thread {
            val url = "https://api.themoviedb.org/3/list/8248497?api_key=eb82a323e426d30d552550d47bc83e2b"
            val response = URL(url).readText()
            val gson = Gson()
            val movieResponse = gson.fromJson(response, MovieResponse::class.java)
            movies.addAll(movieResponse.items)
            activity?.runOnUiThread {
                recyclerView.adapter = MovieAdapter(movies)
            }
        }.start()
    }

    data class MovieResponse(
        val create_by : String,
        val description : String,
        val favorite_count : Int,
        val id : Int,
        val items : List<Movie>
    )


}