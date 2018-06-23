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
class MyListAdapter(private val context: Context, arrayList: ArrayList<Item>) : RecyclerView.Adapter<MyListAdapter.ViewHolder>()
{
    internal var arrayList:ArrayList<Item> = ArrayList<Item>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // val view = inflater.inflate(R.layout.my_item, parent, false)
        val view = inflater.inflate(R.layout.new_version_item_layout, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // holder.my_poster?.setImageResource(arrayList[position].poster)
        if(arrayList[position].poster!=null) RemoteApiService.getRemoteImage(arrayList[position].poster!!,this.context)!!.into(holder.my_poster)
       // holder.my_title?.text=arrayList[position].title

    }
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    fun filterList(filterdNames: ArrayList<Item>) {
        this.arrayList= filterdNames
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_poster: ImageView?=null
       // var my_title: TextView?=null

        init {
            my_poster= itemView.findViewById(R.id.my_poster)
          //  my_title = itemView.findViewById(R.id.my_title)
        }
    }
}

