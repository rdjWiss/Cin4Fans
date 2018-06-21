package prj.mob1.prjmob1.Liste_movies

import android.content.Context
import android.content.Intent
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
import prj.mob1.prjmob1.ListItem.*
import prj.mob1.prjmob1.Person.PersonActivity

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.R.layout.item
import prj.mob1.prjmob1.movie.MovieActivity


import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import prj.mob1.prjmob1.show.ShowActivity
import retrofit2.Response



/**
 * Created by LE on 03/04/2018.
 */
class ListMoviesFragment: BaseFragment_New()
{
    private lateinit var list_adapter : MyListAdapter

    private var  mRecyclerView: RecyclerView? = null
    private var ArrayMovies=ArrayList<Item>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        getData()
    }

    private fun initRecyclerView(view : View ) {
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
    }

  override fun openActivity(position:Int)
    {
        val intent = Intent(context, MovieActivity::class.java)
        val bundle = Bundle()
        bundle.putInt("id",list_adapter.arrayList[position].id)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
    }



   override fun getData(){
        RemoteApiService.apply { sendRequest(create()!!.getLatesMovies(), { onCreateMovieDataSuccess(it) },{onCreateMovieLatestFail(it)}) }
    }

    fun onCreateMovieDataSuccess(result: Response<ListMovies>)
    {
        if (result.isSuccessful) {

            for (movie  in result.body()!!.movies) {
                //var item = Item(movie.id,movie.posterId, movie.year, movie.title, movie.tags)
                val item = Item(movie.id,movie.posterId,movie.title)
                ArrayMovies.add(item)
            }
            list_adapter= MyListAdapter(context as AppCompatActivity, ArrayMovies )
            mRecyclerView?.adapter= list_adapter

        } else //error 400-500
            Log.e("erroor","err" +result.body().toString())
    }


   override fun  onQueryTextChange(text: String)
    {
        filter(text,ArrayMovies,list_adapter)
    }


}