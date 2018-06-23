package prj.mob1.prjmob1.ListItem

import android.content.Context
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.lv_item.view.*

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.R.id.name
import prj.mob1.prjmob1.Settings.ChoiceGenresActivity
import prj.mob1.prjmob1.Settings.ItemChoice


/**
 * Created by LE on 20/04/2018.
 */
class ChoiceAdapter(private val context: Context, arrayList: ArrayList<ItemChoice>) : RecyclerView.Adapter<ChoiceAdapter.ViewHolder>()
{
    internal var arrayList = ArrayList<ItemChoice>()
    private val inflater: LayoutInflater
   var mygenres = ArrayList<ItemChoice>()

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = inflater.inflate(R.layout.item_choice, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre!!.text=arrayList[position].name

        holder.genre!!.setOnCheckedChangeListener { buttonView,
              isChecked ->
            Log.e("papa","arrayList[position]"+arrayList[position].id)
            addGenre(position)
        }
    }
    override fun getItemCount(): Int {
        return arrayList.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var genre: CheckBox?=null

        init {
            genre=itemView.findViewById(R.id.checkboxChoice)
        }
    }

    fun addGenre(position:Int)
    {
        mygenres.add(arrayList.get(position))
    }
    fun RemoveGenre(position:Int)
    {
        mygenres.remove(arrayList.get(position))
    }



}

