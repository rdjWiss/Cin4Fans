package prj.mob1.prjmob1.ListItem

/**
 * Created by LE on 20/04/2018.
 */

data class Item ( val poster: Int, val year:Int,val title:String,val tag:String)
{
    constructor() : this(0,0,"","")
}
