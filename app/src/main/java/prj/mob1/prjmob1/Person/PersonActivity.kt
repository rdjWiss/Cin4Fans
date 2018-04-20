package prj.mob1.prjmob1.Person

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AlertDialog
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_person.*
import prj.mob1.prjmob1.R
import prj.mob1.prjmob1.rating.OnRateClick

class PersonActivity : AppCompatActivity(), OnRateClick {

    private var name :String= ""
    private var modeTab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        if (resources.getString(R.string.isLand) == "true") modeTab = true

        val bundle = intent.getBundleExtra("bundle")
        /*TODO: not null*/
        if(bundle != null){
            name = bundle.getString("personName","")
        }

        val infosFragment =
                PersonInfosFragment.newInstance(name)
        supportFragmentManager.beginTransaction().add(R.id.person_infos, infosFragment).commit()

        //Overview fragment en mode tablette
        if (modeTab){
            val overview = PersonOverviewFragment.newInstance(name)
            supportFragmentManager.beginTransaction()
                    .add(R.id.person_overview, overview).commit()
        }

        //Tabs
        configureTabLayout()
    }

    private fun configureTabLayout() {
        if(!modeTab) person_tab_layout.addTab(person_tab_layout.newTab().setText("Infos"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("Movies"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("TV Shows"))
        person_tab_layout.addTab(person_tab_layout.newTab().setText("Comments"))

        val adapter = PersonTabPagerAdapter(supportFragmentManager,
                person_tab_layout.tabCount,name,modeTab)
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
}
