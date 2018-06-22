package prj.mob1.prjmob1.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.RatingBar
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_person.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.Util.ConnectivityChecker
import prj.mob1.prjmob1.rating.OnRateClick
import prj.mob1.prjmob1.retrofitUtil.RemoteApiService

class PersonActivity : AppCompatActivity(), OnRateClick {


    private var personId :Int= 0
    private var person = Person()
    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
        if(bundle != null){
            personId = bundle.getInt("personId",0)
        }else{
            personId = 20
        }

        if(ConnectivityChecker.isNetworkAvailable(this)) this.getPersonInfos()
        else{
            Toast.makeText(this,"Can't get person infos. No Network Connection",Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun getPersonInfos(){
        val apiService: RemoteApiService? = RemoteApiService.create()
        apiService!!.getPersonInfosById(personId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                result ->
//                    Toast.makeText(this,"Response ${result.tvCredits.cast[0].title}", Toast.LENGTH_LONG).show()
                Log.e("Person",result.toString())

                person = result
                initMovieInfosFrag()
                initOverviewFragTabMode()
                configureTabLayout()


            }, { error ->
                Toast.makeText(this,"Error ${error.message}", Toast.LENGTH_LONG).show()
                error.printStackTrace()
                finish()

            })
    }

    private fun initMovieInfosFrag(){
        val infosFragment =
                PersonInfosFragment.newInstance(person)
        supportFragmentManager.beginTransaction().add(R.id.person_infos, infosFragment).commit()
    }

    private fun initOverviewFragTabMode() {
        //Overview fragment en mode tablette
        if (modeTab) {
            val overview = PersonOverviewFragment.newInstance(person.biography, person.birthday, person.origin )
            supportFragmentManager.beginTransaction()
                    .add(R.id.person_overview, overview).commit()
        }
    }

    private fun configureTabLayout() {
        if(!modeTab) person_tab_layout.addTab(person_tab_layout.newTab().setText("Infos"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("Movies"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("TV Shows"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("Comments"))

        val adapter = PersonTabPagerAdapter(supportFragmentManager,
                person_tab_layout.tabCount,person,modeTab)
        person_viewpager.adapter = adapter

        person_viewpager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(person_tab_layout))

        person_tab_layout.addOnTabSelectedListener(object :
                TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                person_viewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    override fun onRateClick(){
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_rating, null)
        val dialogRatingBar = dialogView.findViewById<RatingBar>(R.id.dialogRb)

        builder.setView(dialogView)
                .setPositiveButton("OK") { dialogInterface, i ->

                    Toast.makeText(this,
                            "Rated: ${dialogRatingBar.rating}", Toast.LENGTH_SHORT )
                            .show()

                }
                .setNegativeButton("Annuler") { dialogInterface, i ->
                }
                .show()
    }

    override fun onRemoveBookmark() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddBookmark() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
