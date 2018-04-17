package prj.mob1.prjmob1.BaseFragment

import android.view.View


/**
 * Created by LE on 17/04/2018.
 */
abstract open class BaseFragment : android.support.v4.app.Fragment()
{
    private var views: View? = null

    abstract fun openFragment(position: Int)


    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

}