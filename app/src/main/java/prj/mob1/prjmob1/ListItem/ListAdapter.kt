package prj.mob1.prjmob1.ListItem

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestBuilder
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

/**
 * Created by LE on 20/04/2018.
 */
class ListAdapter (private val context: Context, arrayList: ArrayList<Item>) : RecyclerView.Adapter<ListAdapter.ViewHolder>()
{
    internal var arrayList = ArrayList<Item>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        /**

        val poster = myView.findViewById<ImageView>(R.id.movie_infos_poster)
        RemoteApiService.getRemoteImage(movie.posterId,this.context)!!.into(poster)

         * */

        //holder.poster?.setImageResource(arrayList[position].poster)
        if(arrayList[position].poster!=null) RemoteApiService.getRemoteImage(arrayList[position].poster!!,this.context)!!.into(holder.poster)
       /* holder.year?.text=arrayList[position].year.toString()
        holder.title?.text=arrayList[position].title
        holder.tag?.text=arrayList[position].tag*/

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster: ImageView?=null
        var year: TextView?=null
        var title: TextView?=null
        var tag: TextView?=null

        init {
            poster = itemView.findViewById(R.id.poster)
            year = itemView.findViewById(R.id.year)
            title = itemView.findViewById(R.id.title)
           tag = itemView.findViewById(R.id.tag)
        }
    }
}

