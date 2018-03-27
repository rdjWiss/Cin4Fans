package prj.mob1.prjmob1.episode

/**
 * Created by sol on 27/03/2018.
 */
data class Episode (val num_episode:String,val title_show: String, val num_season: String, val date: String,
               val channel: String, val posterId: Int, var overview: String) {
    constructor() : this("","","","","",0,"")
}