package prj.mob1.prjmob1.AllListMovies

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import prj.mob1.prjmob1.ListItem.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.EndlessRecyclerViewScrollListener
import prj.mob1.prjmob1.Util.LoadingDialog

import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import retrofit2.Response


/**
 * Created by LE on 17/04/2018.
 */
class AllListMoviesFragment: BaseFragment_New()
{
    private lateinit var list_adapter : MyListAdapter
    private var  mRecyclerView: RecyclerView? = null
    private var ArrayMovies=ArrayList<Item>()

    private lateinit var loadingDialog :ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        getData()
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

    private fun loadNextDataFromApi(page:Int){
//        Toast.makeText(activity,"Loading data page $page", Toast.LENGTH_LONG).show()
        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getAllMovies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    Log.e("LOAD",result.toString())
//                    Toast.makeText(activity,"Total ${result}", Toast.LENGTH_LONG).show()
                    for (movie  in result.body()!!.movies) {
                        val item = Item(movie.id,movie.posterId,movie.title)
                        ArrayMovies.add(item)
                    }
                    mRecyclerView!!.adapter.notifyDataSetChanged()
                }, { error ->
                    Toast.makeText(activity, "Error ${error.message}", Toast.LENGTH_LONG).show()
                    error.printStackTrace()

                })
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
        loadingDialog= LoadingDialog.showLoadingDialog(this.context)
        RemoteApiService.apply { sendRequest(create()!!.getAllMovies(), { onCreateMovieDataSuccess(it) },{onCreateMovieLatestFail(it)}) }
    }

    fun onCreateMovieDataSuccess(result: Response<ListMovies>)
    {
        if (result.isSuccessful) {
           // val movies_now =ArrayList<Item>()
            for (movie  in result.body()!!.movies) {
               // var item = Item(movie.id,movie.posterId, movie.year, movie.title, movie.tags)
                val item = Item(movie.id,movie.posterId,movie.title)
                ArrayMovies.add(item)
            }
            list_adapter= MyListAdapter(context as AppCompatActivity,ArrayMovies )
            mRecyclerView?.adapter= list_adapter
            loadingDialog.dismiss()

        } else //error 400-500
            Log.e("erroor","err" +result.body().toString())
    }
   /* fun onCreateMovieLatestFail(error:Throwable) {
        Log.e("erroor","errror"+ error.message.toString())
    }*/

  override fun  onQueryTextChange(text: String)
   {
       filter(text,ArrayMovies,list_adapter)
   }

    /*private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filterdNames = ArrayList<Item>()

        //looping through existing elements
        for (movie in ArrayMovies) {
            //if the existing elements contains the search input
            if (movie.title.startsWith(text.toLowerCase(),true)) {
                //adding the element to filtered list
                filterdNames.add(movie)
            }
        }

        //calling a method of the adapter class and passing the filtered list
        list_adapter.filterList(filterdNames)
    }*/

}