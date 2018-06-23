package prj.mob1.prjmob1.Liste_shows
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import prj.mob1.prjmob1.ListItem.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.R.layout.item
import prj.mob1.prjmob1.Util.EndlessRecyclerViewScrollListener
import prj.mob1.prjmob1.Util.LoadingDialog
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.show.ShowActivity
import retrofit2.Response

/**
 * Created by LE on 16/04/2018.
 */
 class ListShowFragment: BaseFragment_New() {

    private lateinit var list_adapter : MyListAdapter
    private var  mRecyclerView: RecyclerView? = null
    var ArrayShow=ArrayList<Item>()

    private lateinit var loadingDialog :ProgressDialog

    private var showListInput: Boolean = false

    companion object {

        private val ARG_LIST = "list"
        private val ARG_IN = "in"

        fun newInstance(shows: ArrayList<Item>,showListInput:Boolean): ListShowFragment {
            val fragment = ListShowFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_LIST, shows)
            args.putBoolean(ARG_IN,showListInput)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            ArrayShow = arguments!!.getParcelableArrayList(ARG_LIST)
            showListInput = arguments!!.getBoolean(ARG_IN)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        if(!showListInput) getData()
        else{
            list_adapter= MyListAdapter(context as AppCompatActivity,ArrayShow )
            mRecyclerView?.adapter= list_adapter
        }
    }

    private fun initRecyclerView(view : View) {
        mRecyclerView = view.findViewById<View>(R.id.item_listview) as RecyclerView
        mRecyclerView?.setHasFixedSize(true)
        if (resources.getString(R.string.isLand) == "false")
            mRecyclerView!!.layoutManager = GridLayoutManager(context as AppCompatActivity,2)
        else
            mRecyclerView!!.layoutManager = GridLayoutManager(activity,4)
        mRecyclerView!!.addOnItemTouchListener(BaseFragment.RecyclerTouchListener(activity!!.applicationContext,  mRecyclerView!!, object : BaseFragment.ClickListener {
            override fun onClick(view: View, position: Int) {
                openActivity(position)
            }
            override fun onLongClick(view: View?, position: Int) {
            }
        }))

        if(!showListInput){
            val scrollListener = object : EndlessRecyclerViewScrollListener(mRecyclerView!!.layoutManager as GridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    loadNextDataFromApi(page)
                }
            }
            // Adds the scroll listener to RecyclerView
            mRecyclerView?.addOnScrollListener(scrollListener)
        }
    }

    private fun loadNextDataFromApi(page:Int){
//        Toast.makeText(activity,"Loading data page $page", Toast.LENGTH_LONG).show()
        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getTVShow_now(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    //                Toast.makeText(activity,"Response ${result.body()!!}", Toast.LENGTH_LONG).show()
                    Log.e("LOAD",result.toString())
                    for (show  in result.body()!!.shows) {
                        val item = Item(show.id,show.posterId,show.title)
                        ArrayShow.add(item)
                    }
                    mRecyclerView!!.adapter.notifyDataSetChanged()
                }, { error ->
                    Toast.makeText(activity, "Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()

                })
    }


    override fun openActivity(position:Int)
    {
        val intent = Intent(context, ShowActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id",list_adapter.arrayList[position].id)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }

    override fun getData(){
        loadingDialog= LoadingDialog.showLoadingDialog(this.context)
        RemoteApiService.apply { sendRequest(create()!!.getTVShow_now(), { onCreateMovieDataSuccess(it) },{onCreateMovieLatestFail(it)}) }
    }

    fun onCreateMovieDataSuccess(result: Response<ListShow>)
    {
        if (result.isSuccessful) {

            for (show  in result.body()!!.shows) {
               val item = Item(show.id,show.posterId,show.title)
                ArrayShow.add(item)
            }
            list_adapter= MyListAdapter(context as AppCompatActivity,ArrayShow )
            mRecyclerView?.adapter= list_adapter
            loadingDialog.dismiss()

        } else //error 400-500
            Log.e("erroor","err" +result.body().toString())
    }

    override fun  onQueryTextChange(text: String)
    {
        filter(text,ArrayShow,list_adapter)
    }

}
