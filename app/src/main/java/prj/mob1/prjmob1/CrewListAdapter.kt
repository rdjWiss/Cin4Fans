/*
package prj.mob1.prjmob1

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.content.Context

*/
/**
 * Created by sol on 12/04/2018.
 *//*

class CrewListAdapter(val context: Context,val seasonList : ArrayList<Crew>) : RecyclerView.Adapter<CrewListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.fragment_crew_item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitles.text = seasonList[i].name
        viewHolder.itemDetails.text = seasonList[i].character
        viewHolder.itemImage.setImageResource(seasonList[i].image)

        viewHolder.itemView.setOnClickListener { v: View  ->

            Snackbar.make(v, "Click detected on item $i",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()

            context.onCrewSelected(seasonList[i].name)
        }
    }

    override fun getItemCount(): Int {
        return seasonList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemTitles: TextView
        var itemDetails: TextView

        init {
            itemImage = itemView.findViewById(R.id.crew_item_image)
            itemTitles = itemView.findViewById(R.id.crew_item_name)
            itemDetails = itemView.findViewById(R.id.crew_item_character)

        }


    }
}*/
