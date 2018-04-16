package prj.mob1.prjmob1.Liste_movies

/**
 * Created by LE on 03/04/2018.
 */
data class Movies_Item ( val poster_mov: Int, val movie_year:Int,val movie_title:String,val movie_tag:String)
{
        constructor() : this(0,0,"","")
}



