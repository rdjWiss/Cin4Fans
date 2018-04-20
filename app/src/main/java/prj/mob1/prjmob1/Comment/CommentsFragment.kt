package prj.mob1.prjmob1.Comment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import prj.mob1.prjmob1.R

class CommentsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_comments, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.comments_list) as
                RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity)

        val comment =Comment(getResources().getString(R.string.comment_username),
                getResources().getString(R.string.comment_body))

        val commentsListAdapter = CommentsListAdapter(arrayListOf(comment,comment,comment,comment
                ,comment,comment))
        recyclerView.adapter =commentsListAdapter

        return view
    }

    internal inner class CommentsListAdapter(val commentsList: ArrayList<Comment>)
        : RecyclerView.Adapter<CommentsFragment.CommentsListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CommentsFragment.CommentsListAdapter.ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_comments_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: CommentsFragment.CommentsListAdapter.ViewHolder, i: Int) {
            viewHolder.itemUsername.text = commentsList[i].username
            viewHolder.itemBody.text = commentsList[i].body

            viewHolder.itemView.setOnClickListener { v: View  ->

                /*Snackbar.make(v, "Click detected on item $i",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show()

*/
            }
        }

        override fun getItemCount(): Int {
            return commentsList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var itemUsername: TextView
            var itemBody: TextView

            init {
                itemUsername = itemView.findViewById(R.id.comments_item_username)
                itemBody = itemView.findViewById(R.id.comments_item_body)

            }


        }
    }


}// Required empty public constructor
