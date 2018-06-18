package prj.mob1.prjmob1.Crew

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.retrofitUtil.models.CreditResponse

class CrewFragment() : Fragment() {

    private var typeCrew: Int? = null ///Movie ou show; juste pour tests sans db
    private var credits: CreditResponse? = null

    private lateinit var listener: OnCrewSelected

    companion object {

        private val ARG_typeCrew = "TypeCrew"
        private val ARG_credits = "Credits"

        fun newInstance(param1: Int,credits: CreditResponse): CrewFragment {
            val fragment = CrewFragment()
            val args = Bundle()
            args.putInt(ARG_typeCrew, param1)
            args.putParcelable(ARG_credits, credits)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            typeCrew = arguments!!.getInt(ARG_typeCrew)
            credits = arguments!!.getParcelable(ARG_credits)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_crew,
                container, false)

        Log.e("CREWFrag",credits!!.cast.toString())
        /*TODO show crew*/

        var modeTab = false
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val recyclerView = view.findViewById<RecyclerView>(R.id.crew_directors_list) as
                RecyclerView
        if(!modeTab) recyclerView.layoutManager = LinearLayoutManager(activity)
        else recyclerView.layoutManager = GridLayoutManager(activity, 2)

        val recyclerView2 = view.findViewById<RecyclerView>(R.id.crew_actors_list) as
                RecyclerView
        if(!modeTab) recyclerView2.layoutManager = LinearLayoutManager(activity)
        else recyclerView2.layoutManager = GridLayoutManager(activity, 2)


        val crewListAdapter = CrewListAdapter(credits!!.cast)
        recyclerView.adapter =crewListAdapter

        recyclerView2.adapter =CrewListAdapter(credits!!.crew)
        return view
    }


    interface OnCrewSelected {
        fun onCrewSelected(creditId : Int)
    }


    /**/
    internal inner class CrewListAdapter(val crewList : List<Any>)
        : RecyclerView.Adapter<CrewListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_crew_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

            val credit = crewList[i]
            if(credit is Crew) {
                viewHolder.itemNames.text = credit.name
                viewHolder.itemCharacter.text =  "${credit.departement}, ${credit.job}"
                if(credit.image!=null)  RemoteApiService.getRemoteImage(credit.image,context)!!.into(viewHolder.itemImage)
            }
            else if(credit is Cast) {
                viewHolder.itemNames.text = credit.name
                viewHolder.itemCharacter.text =  credit.character
                if(credit.image!=null) RemoteApiService.getRemoteImage(credit.image,context)!!.into(viewHolder.itemImage)
            }

            viewHolder.itemView.setOnClickListener { _: View  ->

               if(credit is Crew){
                   listener.onCrewSelected(credit.id)
               }
               else if(credit is Cast){
                   listener.onCrewSelected(credit.id)
               }
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
