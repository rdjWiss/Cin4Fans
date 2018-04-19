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

class PersonInfosFragment : Fragment() {
    private var name = ""
    private lateinit var listener: OnRateClick

    companion object {

        private val ARG_namePerson = "personName"

        fun newInstance(nom : String): PersonInfosFragment {
            val fragment = PersonInfosFragment()
            val args = Bundle()
            args.putString(ARG_namePerson , nom)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

            name = arguments.getString(ARG_namePerson)
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding : FragmentPersonInfosBinding =
                FragmentPersonInfosBinding.inflate(inflater!! ,container , false)
        val view : View  = binding.root

        //Set rating listener
        view.findViewById<ImageView>(R.id.person_infos_rate).setOnClickListener{
            listener.onRateClick()
        }

        var indice= 0;
        if(resources.getStringArray(R.array.person_names)[1] == name) indice = 1

        val birthday = resources.getStringArray(R.array.person_birthdays)[indice]
        val origin = resources.getStringArray(R.array.person_from)[indice]
        val bio = resources.getStringArray(R.array.person_biographies)[indice]

        val profil = resources.obtainTypedArray(R.array.person_profils).getResourceId(indice,0)
        val image = resources.obtainTypedArray(R.array.person_images).getResourceId(indice,0)

        view.findViewById<ImageView>(R.id.person_image_top).setImageResource(image)
        view.findViewById<ImageView>(R.id.person_profil_image).setImageResource(profil)

        binding.person = Person(name,birthday,origin,bio,0,0)
        return view

    }
}// Required empty public constructor
