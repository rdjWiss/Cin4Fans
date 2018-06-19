package prj.mob1.prjmob1.Liste_movies

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import prj.mob1.prjmob1.Cinema.ListCinemaAdpater

import prj.mob1.prjmob1.ListItem.Item

import prj.mob1.prjmob1.ListItem.ListMovies
import prj.mob1.prjmob1.ListItem.MyListAdapter
import prj.mob1.prjmob1.R


import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import retrofit2.Response



/**
 * Created by LE on 03/04/2018.
 */
class ListMoviesFragment: android.support.v4.app.Fragment()
{
    private lateinit var list_adapter : MyListAdapter
    private var views: View? = null
    private var  mRecyclerView: RecyclerView? = null


   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        views = inflater!!.inflate(R.layout.fragment_list, container, false)
        return views
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view!!)
        getMovieData()
    }

    private fun initRecyclerView(view : View ) {
        mRecyclerView = view.findViewById<View>(R.id.item_listview) as RecyclerView
        mRecyclerView?.setHasFixedSize(true)
        if (resources.getString(R.string.isLand) == "false")
            mRecyclerView!!.layoutManager = GridLayoutManager(activity,2)
        else
              mRecyclerView!!.layoutManager = GridLayoutManager(activity,4)
    }



    fun getMovieData(){
        RemoteApiService.apply { sendRequest(create()!!.getLatesMovies(), { onCreateMovieLatestSuccess(it) },{onCreateMovieLatestFail(it)}) }
    }

    fun onCreateMovieLatestSuccess(result: Response<ListMovies>)
    {
        if (result.isSuccessful) {

            val movies_now =ArrayList<Item>()
            Log.e("erroor", "sucess")
            for (movie  in result.body()!!.movies) {
                var item = Item(movie.posterId, movie.year, movie.title, movie.tags)
                movies_now.add(item)
            }
            list_adapter= MyListAdapter(context as AppCompatActivity,movies_now )
            mRecyclerView?.adapter= list_adapter

        } else //error 400-500
        {
            Log.e("erroor","err" +result.body().toString())
        }

    }
    fun onCreateMovieLatestFail(error:Throwable) {
        Log.e("erroor","errror"+ error.message.toString())
    }
}