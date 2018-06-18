package prj.mob1.prjmob1.bookmark

import prj.mob1.prjmob1.ActionsInterface

/**
 * Created by sol on 14/06/2018.
 */
interface OnBookmarkClick : ActionsInterface {
    override  fun onAddBookmark()
    override fun onRemoveBookmark()
}