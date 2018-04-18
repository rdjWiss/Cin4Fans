package prj.mob1.prjmob1.MyShows

/**
 * Created by LE on 18/04/2018.
 */
data class MyShowItem( val poster_show: Int,val show_title:String,val show_tag:String)
{
    constructor() : this(0,"","")
}