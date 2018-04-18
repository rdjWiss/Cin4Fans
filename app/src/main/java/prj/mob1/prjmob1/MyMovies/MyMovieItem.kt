package prj.mob1.prjmob1.MyMovies

/**
 * Created by LE on 18/04/2018.
 */
data class MyMovieItem(val poster_mov: Int, val movie_title:String, val movie_tag:String)
{
    constructor() : this(0,"","")
}