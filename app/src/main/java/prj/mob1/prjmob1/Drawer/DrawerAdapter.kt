package prj.mob1.prjmob1.Drawer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import prj.mob1.prjmob1.R

/**
 * Created by LE on 01/04/2018.
 */
class DrawerAdapter(private val context: Context, arrayList: ArrayList<DrawerModel>) : RecyclerView.Adapter<DrawerAdapter.ViewHolder>() {

    internal var arrayList = ArrayList<DrawerModel>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.lv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (position)
        {
            3,6-> holder.divider?.visibility=View.VISIBLE
        }
        holder.title?.text=arrayList[position].name
        holder.ivicon?.setImageResource(arrayList[position].image)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView?=null
        var ivicon: ImageView?=null
        var divider :View ?=null

        init {
            title = itemView.findViewById(R.id.name)
            ivicon = itemView.findViewById(R.id.ivicon)
            divider=itemView.findViewById(R.id.divider)
        }
    }
}