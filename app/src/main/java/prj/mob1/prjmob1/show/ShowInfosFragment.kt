package prj.mob1.prjmob1.show

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_show_infos.*

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentShowInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService


class ShowInfosFragment : Fragment() {

    private lateinit var listener: OnRateClick

    private /*lateinit*/ var show: TVShow? = null

    companion object {

        private val ARG_SHOW = "show"

        fun newInstance(show: TVShow): ShowInfosFragment {
            val fragment = ShowInfosFragment()
            val args = Bundle()
            args.putParcelable(ARG_SHOW, show)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            show = arguments!!.getParcelable(ARG_SHOW)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnRateClick) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRateClick.")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentShowInfosBinding =
                FragmentShowInfosBinding.inflate(inflater ,container , false)
        val myView : View  = binding.root

        binding.show = show

        val poster = myView.findViewById<ImageView>(R.id.show_infos_poster)
        if(show!!.posterId!=null) RemoteApiService.getRemoteImage(show!!.posterId,this.context)!!.into(poster)


        //Set rating listener
        myView.findViewById<ImageView>(R.id.show_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        //Ajout du listner de bookmark
        myView.findViewById<ImageView>(R.id.show_infos_bookmark1).setOnClickListener{
            Snackbar.make(myView,"Added to favorites", Snackbar.LENGTH_SHORT).show()
            show_infos_bookmark2.visibility = View.VISIBLE
            show_infos_bookmark1.visibility = View.INVISIBLE
        }

        myView.findViewById<ImageView>(R.id.show_infos_bookmark2).setOnClickListener{
            Snackbar.make(myView,"Removed from favorites", Snackbar.LENGTH_SHORT).show()
            show_infos_bookmark1.visibility = View.VISIBLE
            show_infos_bookmark2.visibility = View.INVISIBLE
        }

        return myView
    }
}// Required empty public constructor
