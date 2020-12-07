package tech.ru1t3rl.madlevel6task2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import tech.ru1t3rl.madlevel6task2.model.Movie
import tech.ru1t3rl.madlevel6task2.R

class MovieAdapter(private val movies: List<Movie>, private val onClick: (Int) -> Unit) :
        RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {

            //Click Listener for views
            itemView.setOnClickListener { onClick(adapterPosition) }
        }

        fun bind(movie: Movie) {

            //Set the ranking
            itemView.tvNum.text =
                    itemView.resources.getString(R.string.tv_number, adapterPosition + 1)

            //Set the covers
            Glide.with(context).load(movie.getPosterUrl()).into(itemView.ivPoster)
        }
    }
}