package prj.mob1.prjmob1.Util

import android.app.ProgressDialog
import android.content.Context

/**
 * Created by sol on 23/06/2018.
 */
class LoadingDialog {

    companion object {
        lateinit var pd : ProgressDialog

        fun showLoadingDialog(activity:Context?): ProgressDialog{
            pd = ProgressDialog(activity)
            pd.setTitle("Loading...")
            pd.setMessage("Please wait.")
            pd.setCancelable(false)
            pd.show()
            return pd
        }


    }
}