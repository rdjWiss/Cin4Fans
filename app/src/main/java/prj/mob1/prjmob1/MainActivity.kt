package prj.mob1.prjmob1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import prj.mob1.prjmob1.episode.EpisodeActivity
import prj.mob1.prjmob1.movie.MovieActivity
import prj.mob1.prjmob1.season.SeasonActivity
import prj.mob1.prjmob1.show.ShowActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_movie.setOnClickListener {
            val intent1 = Intent (this, MovieActivity:: class.java)
            startActivity (intent1)
        }

        btn_show.setOnClickListener {
            val intent1 = Intent (this, ShowActivity :: class.java)
            startActivity (intent1)
        }

        btn_season.setOnClickListener {
            val intent1 = Intent (this, SeasonActivity :: class.java)
            startActivity (intent1)
        }

        btn_episode.setOnClickListener {
            val intent1 = Intent (this, EpisodeActivity :: class.java)
            startActivity (intent1)
        }
    }
}
