package prj.mob1.prjmob1.Settings

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_choice_genres.*
import prj.mob1.prjmob1.ListItem.*

import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.initDrawer
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService
import retrofit2.Response
import java.security.AccessController.getContext

class ChoiceGenresActivity : AppCompatActivity() {

    private lateinit var list_adapter : ChoiceAdapter
    private var  mRecyclerView: RecyclerView? = null
    private var ArrayChoice=ArrayList<ItemChoice>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_genres)
        setTitle("Choix de genres")
        initDrawer()
        initRecyclerView()
        getData()
        choice.setOnClickListener(View.OnClickListener {
            showValideDialog()
        })

    }

    private fun initRecyclerView() {
        mRecyclerView = findViewById<View>(R.id.item_listview) as RecyclerView
        mRecyclerView?.setHasFixedSize(true)
        if (resources.getString(R.string.isLand) == "false")
            mRecyclerView!!.layoutManager = GridLayoutManager(this,2)
        else
            mRecyclerView!!.layoutManager = GridLayoutManager(this,4)
    }

     fun getData(){
        RemoteApiService.apply { sendRequest(create()!!.getGenres(), { onCreateChoiceDataSuccess(it) },{onCreateChiceDataFail(it)}) }
    }

    fun onCreateChoiceDataSuccess(result: Response<ListeItemChoice>)
    {
        if (result.isSuccessful) {
            for (choice in result.body()!!.choices) {
                var item = ItemChoice(choice.id,choice.name)
                ArrayChoice.add(item)
            }
            list_adapter= ChoiceAdapter(this,ArrayChoice )
            mRecyclerView?.adapter= list_adapter

        } else //error 400-500
            Log.e("erroor","err" +result.body().toString())
    }

    fun onCreateChiceDataFail(error:Throwable) {
        Log.e("erroor","errror"+ error.message.toString())
    }


    fun showValideDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmer Choix de genres")
        builder.setMessage("Voulez-vous vraiment sauvegarder cette liste?")
        builder.setPositiveButton("Confirmer", DialogInterface.OnClickListener { _, _ ->
            val prefs: SharedPreferences = getSharedPreferences("GENRES", 0)
            val editor = prefs.edit()
            editor.putInt("choices"+"_size", list_adapter.mygenres.size)
            Log.e("papa",list_adapter.mygenres.size.toString())

            for ( i in 0 .. (list_adapter.mygenres.size - 1) ){
                editor.putString(i.toString(),list_adapter.mygenres.get(i).id.toString() )
                }
                 editor.commit()
            finish()
        })
        builder.setNegativeButton("Annuler",DialogInterface.OnClickListener { _, _ ->
           val prefs: SharedPreferences = getSharedPreferences("GENRES", 0)
            val size = prefs.getInt("choices" + "_size", 0)
            if (size >0)
            {
                for (i in 0 .. (size - 1))
                {
                    Log.e("papa",prefs.getString(i.toString(),null).toString())
                }
            }


            finish()
        })

        val dialog = builder.create()
        dialog.show()
    }



}

