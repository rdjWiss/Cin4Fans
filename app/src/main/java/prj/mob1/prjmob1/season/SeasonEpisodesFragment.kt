package prj.mob1.prjmob1.season

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.episode.Episode

class SeasonEpisodesFragment : Fragment() {

    private lateinit var listener: SeasonEpisodesFragment.OnEpisodeSelected
    private lateinit var episodes: ArrayList<Episode>

    companion object {
        private val ARG_LIST= "list"

        fun newInstance(episodes: ArrayList<Episode>): SeasonEpisodesFragment {
            val fragment = SeasonEpisodesFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_LIST, episodes)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            episodes = arguments!!.getParcelableArrayList(ARG_LIST)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnEpisodeSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnSeasonSelected.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View =  inflater.inflate(R.layout.fragment_season_episodes, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.season_episodes_list) as
                RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        /*var episodes: ArrayList<Episode> = ArrayList<Episode>()
        val episodeTitlesList = resources.getStringArray(R.array.episode_titles)
        val episodeNumsList = resources.getStringArray(R.array.episode_nums)
        val episodeDatesList = resources.getStringArray(R.array.episode_dates)
        val episodeOverviewsList = resources.getStringArray(R.array.episode_overviews)
        val titleShow = getString(R.string.episode_title_show)
        val seasonShow = getString(R.string.episode_num_season)
        val channel = getString(R.string.episode_channel)
        for(i in 0 .. episodeTitlesList.size-1){
            episodes.add(Episode(episodeTitlesList[i],
                    episodeNumsList[i], seasonShow,titleShow, episodeDatesList[i],
                    channel,1,episodeOverviewsList[i]
            ))

        }

*/
        recyclerView.adapter = EpisodeListAdapter(episodes)

        return view
    }

    interface OnEpisodeSelected {
        fun onEpisodeSelected(episode: Episode)
    }

    /**/
    internal inner class EpisodeListAdapter(val episodeList: ArrayList<Episode>)
        : RecyclerView.Adapter<prj.mob1.prjmob1.season.SeasonEpisodesFragment.EpisodeListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_season_episodes_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            viewHolder.itemTitles.text = episodeList[i].episode_title
            val details = "Episode ${episodeList[i].num_episode} | ${episodeList[i].date}"
            viewHolder.itemDetails.text = details


            viewHolder.itemView.setOnClickListener { v: View  ->

                listener.onEpisodeSelected(episodeList[i])
            }
        }

        override fun getItemCount(): Int {
            return episodeList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var itemTitles: TextView
            var itemDetails: TextView

            init {
                itemTitles = itemView.findViewById(R.id.episode_item_title)
                itemDetails = itemView.findViewById(R.id.episode_item_details)

            }


        }
    }
}// Required empty public constructor
