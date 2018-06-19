package prj.mob1.prjmob1.ListItem
import prj.mob1.prjmob1.R

/**
 * Created by LE on 20/04/2018.
 */

data class Item (val poster: String, val year:String, val title:String, val tag:String)
{
    constructor() : this("","","","")
}
