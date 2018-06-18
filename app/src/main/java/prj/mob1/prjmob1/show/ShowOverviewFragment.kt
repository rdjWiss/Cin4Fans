package prj.mob1.prjmob1.show

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentShowOverviewBinding

class ShowOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding : FragmentShowOverviewBinding =
                FragmentShowOverviewBinding.inflate(inflater!! ,container , false)
        var myView : View  = binding.root


        // setting values to model
        val overview = getResources().getString(R.string.show_overview)

        val show = TVShow(overview)
        Log.e("TAG",show.title)
        binding.show = show

        return myView
    }

}
