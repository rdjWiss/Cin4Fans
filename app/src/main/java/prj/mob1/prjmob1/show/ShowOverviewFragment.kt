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

    private lateinit var overview: String

    companion object {

        private val ARG_OVERVIEW = "overview"

        fun newInstance(overview: String): ShowOverviewFragment {
            val fragment = ShowOverviewFragment()
            val args = Bundle()
            args.putString(ARG_OVERVIEW, overview)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            overview = arguments!!.getString(ARG_OVERVIEW)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentShowOverviewBinding =
                FragmentShowOverviewBinding.inflate(inflater ,container , false)
        val myView : View  = binding.root


        // setting values to model
        //val overview = getResources().getString(R.string.show_overview)

/*<<<<<<< HEAD*/
        val show = TVShow()
        show.overview = overview
/*=======
       // val show = TVShow(overview)
        val show = TVShow()
        Log.e("TAG",show.title)
>>>>>>> 5090e7887016804d7eaca29504dacda43df2d5ec*/
        binding.show = show

        return myView
    }

}
