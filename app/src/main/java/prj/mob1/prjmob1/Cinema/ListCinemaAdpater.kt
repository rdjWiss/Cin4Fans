package prj.mob1.prjmob1.Cinema
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestBuilder
import prj.mob1.prjmob1.ListItem.Item
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

/**
 * Created by LE on 20/04/2018.
 */
class  ListCinemaAdpater(private val context: Context, arrayList: ArrayList<Item>) : RecyclerView.Adapter<ListCinemaAdpater.ViewHolder>()
{
    internal var arrayList = ArrayList<Item>()
    private val inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context)
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.cinema_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //holder.cinema?.setImageResource(arrayList[position].poster)
        RemoteApiService.getRemoteImage(arrayList[position].poster,this.context)!!.into(holder.cinema)
       /* holder.cinema_rate?.text=" (${arrayList[position].year.toString()})"
        holder.cinema_title?.text=arrayList[position].title
        holder.cinema_adress?.text=arrayList[position].tag*/

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cinema: ImageView?=null
        var cinema_rate: TextView?=null
        var cinema_title: TextView?=null
        var cinema_adress: TextView?=null

        init {
            cinema = itemView.findViewById(R.id.cinema)
            cinema_rate = itemView.findViewById(R.id.cinema_rate)
            cinema_title = itemView.findViewById(R.id.cinema_title)
            cinema_adress = itemView.findViewById(R.id.cinema_adress)
        }
    }
}


