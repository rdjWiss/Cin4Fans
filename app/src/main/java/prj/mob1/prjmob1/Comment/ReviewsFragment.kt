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
import prj.mob1.prjmob1.retrofitUtil.models.ReviewsResponse

class ReviewsFragment : Fragment() {

    private var reviews: ReviewsResponse? = null

    companion object {

        private val ARG_reviews = "Reviews"

        fun newInstance(reviews: ReviewsResponse): ReviewsFragment {
            val fragment = ReviewsFragment()
            val args = Bundle()
            args.putParcelable(ARG_reviews, reviews)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            reviews = arguments!!.getParcelable(ARG_reviews)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_comments, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.comments_list) as
                RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(activity)

        /*val comment =Comment(getResources().getString(R.string.comment_username),
                getResources().getString(R.string.comment_body))

        val commentsListAdapter = ReviewsListAdapter(arrayListOf(comment,comment,comment,comment
                ,comment,comment))*/
        val commentsListAdapter = ReviewsListAdapter(reviews!!.results)
        recyclerView.adapter =commentsListAdapter

        return view
    }

    internal inner class ReviewsListAdapter(val commentsList: List<Review>)
        : RecyclerView.Adapter<ReviewsFragment.ReviewsListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ReviewsFragment.ReviewsListAdapter.ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.fragment_comments_item, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ReviewsFragment.ReviewsListAdapter.ViewHolder, i: Int) {
            viewHolder.itemUsername.text = commentsList[i].author
            viewHolder.itemBody.text = commentsList[i].content

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
