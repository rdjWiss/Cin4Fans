package prj.mob1.prjmob1.ListItem

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import prj.mob1.prjmob1.R

/**
 * Created by LE on 20/04/2018.
 */
class MyListAdapter(private val context: Context, arrayList: ArrayList<Item>) : RecyclerView.Adapter<MyListAdapter.ViewHolder>()
{
    internal var arrayList = ArrayList<Item>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.my_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.my_poster?.setImageResource(arrayList[position].poster)

        holder.my_title?.text=arrayList[position].title

    }
    override fun getItemCount(): Int {
        return arrayList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_poster: ImageView?=null
        var my_title: TextView?=null

        init {
            my_poster= itemView.findViewById(R.id.my_poster)
            my_title = itemView.findViewById(R.id.my_title)
        }
    }
}