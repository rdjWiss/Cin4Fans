package prj.mob1.prjmob1.Person

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.databinding.FragmentPersonOverviewBinding


class PersonOverviewFragment:Fragment() {

    private var bio = ""
    private var birthday = ""
    private var origin = ""

    companion object {

        private val ARG_BIO = "bio"
        private val ARG_BDAY = "birthday"
        private val ARG_ORIGIN = "origin"

        fun newInstance(bio : String, birthday:String,origin:String  ): PersonOverviewFragment {
            val fragment = PersonOverviewFragment()
            val args = Bundle()
            args.putString(ARG_BIO , bio)
            args.putString(ARG_BDAY , birthday)
            args.putString(ARG_ORIGIN , origin)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

            bio = arguments!!.getString(ARG_BIO)
            birthday = arguments!!.getString(ARG_BDAY)
            origin = arguments!!.getString(ARG_ORIGIN)
        }
    }

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?,
            savedInstanceState:Bundle?):View? {
     // Inflate the layout for this fragment
        val binding : FragmentPersonOverviewBinding =
                FragmentPersonOverviewBinding.inflate(inflater ,container , false)
        val view : View  = binding.root



        /*val nom = resources.getStringArray(R.array.person_names)[0]
        val birthday = resources.getStringArray(R.array.person_birthdays)[0]
        val origin = resources.getStringArray(R.array.person_from)[0]
        val bio = resources.getStringArray(R.array.person_biographies)[0]*/
        /*var indice= 0;
        if(resources.getStringArray(R.array.person_names)[1] == name) indice = 1
        Log.e("name","kjkjljljlkjlj ${resources.getStringArray(R.array.person_names)[1] == name}")
        val birthday = resources.getStringArray(R.array.person_birthdays)[indice]
        val origin = resources.getStringArray(R.array.person_from)[indice]
        val bio = resources.getStringArray(R.array.person_biographies)[indice]*/
        val person = Person()
        person.biography = bio
        person.birthday = birthday
        person.origin = origin
        binding.person = person
        return view
    }


}// Required empty public constructor
