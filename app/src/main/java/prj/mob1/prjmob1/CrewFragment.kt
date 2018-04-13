package prj.mob1.prjmob1

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CrewFragment() : Fragment() {

    private var typeCrew: Int? = null ///Movie ou show; juste pour tests sans db

    private lateinit var listener: OnCrewSelected

    companion object {

        private val ARG_typeCrew = "TypeCrew"

        fun newInstance(param1: Int): CrewFragment {
            val fragment = CrewFragment()
            val args = Bundle()
            args.putInt(ARG_typeCrew, param1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            typeCrew = arguments.getInt(ARG_typeCrew)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnCrewSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnCrewSelected.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_crew,
                container, false)

//        val activity = activity
        var person = Crew()
        if(typeCrew==0){//Movie
            person = Crew(getResources().getString(R.string.movie_crew_name),
                    getResources().getString(R.string.movie_crew_character),
                    R.drawable.crew_chadwick)
        }
        else{//TV SHOW
            person = Crew(getResources().getString(R.string.show_crew_name),
                    getResources().getString(R.string.show_crew_character),
                    R.drawable.crew_andrew)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.crew_directors_list) as
                RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        var crewListAdapter = CrewListAdapter(arrayListOf(person, person, person))
        recyclerView.adapter =crewListAdapter


        val recyclerView2 = view.findViewById<RecyclerView>(R.id.crew_actors_list) as
                RecyclerView
        recyclerView2.layoutManager = LinearLayoutManager(activity)
        recyclerView2.adapter =CrewListAdapter(arrayListOf(person, person, person))
        return view
    }


    interface OnCrewSelected {
        fun onCrewSelected(name : String)
    }


    /**/
    internal inner class CrewListAdapter(val crewList : ArrayList<Crew>)
        : RecyclerView.Adapter<prj.mob1.prjmob1.CrewFragment.CrewListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_crew_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            viewHolder.itemNames.text = crewList[i].name
            viewHolder.itemCharacter.text = crewList[i].character
            viewHolder.itemImage.setImageResource(crewList[i].image)

            viewHolder.itemView.setOnClickListener { v: View  ->

                /*Snackbar.make(v, "Click detected on item $i",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show()*/

                listener.onCrewSelected(crewList[i].name)
            }
        }

        override fun getItemCount(): Int {
            return crewList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var itemImage: ImageView
            var itemNames: TextView
            var itemCharacter: TextView

            init {
                itemImage = itemView.findViewById(R.id.crew_item_image)
                itemNames = itemView.findViewById(R.id.crew_item_name)
                itemCharacter = itemView.findViewById(R.id.crew_item_character)

            }


        }
    }

}// Required empty public constructor
