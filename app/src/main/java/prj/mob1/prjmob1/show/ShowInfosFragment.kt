package prj.mob1.prjmob1.show

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentShowInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick


class ShowInfosFragment : Fragment() {

    private lateinit var listener: OnRateClick

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnRateClick) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRateClick.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentShowInfosBinding =
                FragmentShowInfosBinding.inflate(inflater!! ,container , false)
        val myView : View  = binding.root

        //Set rating listener
        myView.findViewById<ImageView>(R.id.show_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        // setting values to model
        val title:String = getString(R.string.show_title)
        val episodes = getString(R.string.show_episodes).toInt()
        val tags = getString(R.string.show_tags)
        val duration = getString(R.string.show_duration).toInt()
        val show = TVShow(title, episodes ,
                tags, duration,1,"")

        val poster = resources.obtainTypedArray(R.array.show_images).getResourceId(1,0)
        myView.findViewById<ImageView>(R.id.show_infos_poster).setImageResource(poster)

        Log.e("TAG",show.title)
        binding.show = show

        return myView
    }
}// Required empty public constructor
