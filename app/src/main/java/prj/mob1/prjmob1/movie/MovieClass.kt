package prj.mob1.prjmob1.movie

/**
 * Created by sol on 25/03/2018.
 */
data class MovieClass(val title:String, val release: String, val year: Int, val tags: String,
                      val duration: Int, val posterId: Int, var overview: String) {
    constructor() : this("","",0,"",0,0,"")
}

