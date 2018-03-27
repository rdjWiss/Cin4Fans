package prj.mob1.prjmob1.season

/**
 * Created by sol on 27/03/2018.
 */
data class Season (val num_season:String,val title_show: String, val nbr_episodes: Int, val date_begin: String,
              val date_end: String, val posterId: Int, var overview: String) {
    constructor() : this("","",0,"","",0,"")

}