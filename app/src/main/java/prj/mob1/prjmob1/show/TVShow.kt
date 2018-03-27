package prj.mob1.prjmob1.show

/**
 * Created by sol on 26/03/2018.
 */
data class TVShow(val title:String, val nbr_episodes: Int, val tags: String,
                       val duration: Int, val posterId: Int, val overview: String) {
    constructor() : this("",0,"",0,0,"")
    constructor(overview:String) :this("",0,"",0,0,overview)
}