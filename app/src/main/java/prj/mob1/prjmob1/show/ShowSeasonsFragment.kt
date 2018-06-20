package prj.mob1.prjmob1.show

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.season.Season

class ShowSeasonsFragment : Fragment() {

    private lateinit var listener: ShowSeasonsFragment.OnSeasonSelected

    private lateinit var seasons: ArrayList<Season>

    companion object {

        private val ARG_LIST = "list"

        fun newInstance(seasons: ArrayList<Season>): ShowSeasonsFragment {
            val fragment = ShowSeasonsFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_LIST, seasons)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            seasons = arguments!!.getParcelableArrayList(ARG_LIST)
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnSeasonSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnSeasonSelected.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View =  inflater.inflate(R.layout.fragment_show_seasons, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.show_seasons_list) as
                RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = SeasonListAdapter(seasons)

        return view
    }

    interface OnSeasonSelected {
        fun onSeasonSelected(season: Season)
    }
    /**/
    internal inner class SeasonListAdapter(val seasonList: ArrayList<Season>)
        : RecyclerView.Adapter<prj.mob1.prjmob1.show.ShowSeasonsFragment.SeasonListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_show_seasons_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            val seasonNum = "Season ${seasonList[i].num_season}"
            viewHolder.itemNums.text = seasonNum
            val seasonEp = " ${seasonList[i].nbr_episodes.toString()} episodes"
            viewHolder.itemEps.text = seasonEp

            viewHolder.itemView.setOnClickListener { v: View  ->

                listener.onSeasonSelected(seasonList[i])
            }
        }

        override fun getItemCount(): Int {
            return seasonList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var itemNums: TextView
            var itemEps: TextView

            init {
                itemNums = itemView.findViewById(R.id.season_item_num)
                itemEps = itemView.findViewById(R.id.season_item_eps)

            }
        }
    }
}// Required empty public constructor
