package prj.mob1.prjmob1.Util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by sol on 22/06/2018.
 */
class ConnectivityChecker {

    companion object {
        fun isNetworkAvailable(act : Context): Boolean {
            val connectivityManager = act.getSystemService(Context.CONNECTIVITY_SERVICE)
            return if (connectivityManager is ConnectivityManager) {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            } else false
        }
    }
    
}