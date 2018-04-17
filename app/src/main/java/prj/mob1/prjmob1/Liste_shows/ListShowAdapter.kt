package prj.mob1.prjmob1.Liste_shows

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import prj.mob1.prjmob1.R

/**
 * Created by LE on 16/04/2018.
 */
class ListShowAdapter (private val context: Context, arrayList: ArrayList<Show_Item>) : RecyclerView.Adapter<ListShowAdapter.ViewHolder>()
    {
        internal var arrayList = ArrayList<Show_Item>()
        private val inflater: LayoutInflater

        init {
            inflater = LayoutInflater.from(context)
            this.arrayList = arrayList
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.show_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.poster_show?.setImageResource(arrayList[position].poster_show)
            holder.show_year?.text=arrayList[position].show_year.toString()
            holder.show_title?.text=arrayList[position].show_title
            holder.show_tag?.text=arrayList[position].show_tag
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var poster_show: ImageView?=null
            var show_year: TextView?=null
            var show_title: TextView?=null
            var show_tag: TextView?=null

            init {
                poster_show = itemView.findViewById(R.id.poster_show)
                show_year = itemView.findViewById(R.id.show_year)
                show_title = itemView.findViewById(R.id.show_title)
                show_tag = itemView.findViewById(R.id.show_tag)
            }
        }
    }
