package prj.mob1.prjmob1.MyMovies
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import prj.mob1.prjmob1.R

/**
 * Created by LE on 18/04/2018.
 */
class MyMoviesAdapter(private val context: Context, arrayList: ArrayList<MyMovieItem>) : RecyclerView.Adapter<MyMoviesAdapter.ViewHolder>()
{
    internal var arrayList = ArrayList<MyMovieItem>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.my_movie_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.poster_mov?.setImageResource(arrayList[position].poster_mov)

        holder.movie_title?.text=arrayList[position].movie_title

    }
    override fun getItemCount(): Int {
        return arrayList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var poster_mov: ImageView?=null
        var movie_title: TextView?=null

        init {
            poster_mov = itemView.findViewById(R.id.my_poster_mov)
            movie_title = itemView.findViewById(R.id.my_movie_title)
        }
    }
}