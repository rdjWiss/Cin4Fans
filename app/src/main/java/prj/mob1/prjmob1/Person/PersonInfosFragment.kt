package prj.mob1.prjmob1.Person

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentPersonInfosBinding
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

class PersonInfosFragment : Fragment() {
    private var person:Person? = null
    private lateinit var listener: OnRateClick

    companion object {

        private val ARG_PERSON = "person"

        fun newInstance( person:Person): PersonInfosFragment {
            val fragment = PersonInfosFragment()
            val args = Bundle()
            args.putParcelable(ARG_PERSON , person)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            person= arguments!!.getParcelable(ARG_PERSON)
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
        val binding : FragmentPersonInfosBinding =
                FragmentPersonInfosBinding.inflate(inflater ,container , false)
        val view : View  = binding.root

        var image = view.findViewById<ImageView>(R.id.person_profil_image)
        if(person!!.imageId !=null) RemoteApiService.getRemoteImage(person?.imageId!!,this.context)!!.into(image)

        image = view.findViewById<ImageView>(R.id.person_image_top)
        if(person!!.imageTop !=null) RemoteApiService.getRemoteImage(person?.imageTop!!,this.context)!!.into(image)
        binding.person = person

        //Set rating listener
        view.findViewById<ImageView>(R.id.person_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        return view

    }
}// Required empty public constructor
