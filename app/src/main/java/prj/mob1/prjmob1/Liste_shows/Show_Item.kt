package prj.mob1.prjmob1.Liste_shows

/**
 * Created by LE on 16/04/2018.
 */
data class Show_Item( val poster_show: Int, val show_year:Int,val show_title:String,val show_tag:String)
{
    constructor() : this(0,0,"","")
}