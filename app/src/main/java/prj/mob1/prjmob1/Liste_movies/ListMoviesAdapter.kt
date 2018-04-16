package prj.mob1.prjmob1.Liste_movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import prj.mob1.prjmob1.R

/**
 * Created by LE on 03/04/2018.
 */
class ListMoviesAdapter(private val context: Context, arrayList: ArrayList<Movies_Item>) : RecyclerView.Adapter<ListMoviesAdapter.ViewHolder>()
    {
    internal var arrayList = ArrayList<Movies_Item>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.poster_mov?.setImageResource(arrayList[position].poster_mov)
        holder.movie_year?.text=arrayList[position].movie_year.toString()
        holder.movie_title?.text=arrayList[position].movie_title
        holder.movie_tag?.text=arrayList[position].movie_tag

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster_mov: ImageView?=null
        var movie_year: TextView?=null
        var movie_title: TextView?=null
        var movie_tag: TextView?=null

        init {
            poster_mov = itemView.findViewById(R.id.poster_mov)
            movie_year = itemView.findViewById(R.id.movie_year)
            movie_title = itemView.findViewById(R.id.movie_title)
            movie_tag = itemView.findViewById(R.id.movie_tag)
        }
    }
}