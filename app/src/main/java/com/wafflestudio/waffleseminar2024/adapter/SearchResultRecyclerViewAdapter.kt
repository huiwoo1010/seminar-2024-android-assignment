package com.wafflestudio.waffleseminar2024.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.MovieItem
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.data.database.MyEntity

class searchResultRecyclerViewAdapter(
    private val movieList: List<MyEntity>,
    private val onMovieClick: (MyEntity) -> Unit
) : RecyclerView.Adapter<searchResultRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = movieList[position]
        val imageUrl = "https://image.tmdb.org/t/p/original" + currentItem.poster_path
        holder.imageView.load(imageUrl)
        holder.itemView.setOnClickListener {onMovieClick(currentItem)}
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
