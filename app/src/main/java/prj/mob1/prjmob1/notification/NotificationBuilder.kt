package prj.mob1.prjmob1.notification

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context

/**
 * Created by sol on 23/06/2018.
 */
class NotificationBuilder {

    companion object {
        fun builder(nm: NotificationManager,
                            pi: PendingIntent,
                            context:Context
//                    ,
                            /*title:String,
                            content:String*/
        ): Notification.Builder? {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                        "ch00", "ch00", NotificationManager.IMPORTANCE_HIGH)
                nm.createNotificationChannel(mChannel)
                val builder = Notification.Builder(context,"ch00")
                        /*.setContentTitle(title)
                        .setContentText(content)*/
                        .setSmallIcon(R.drawable.btn_star)
//                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setOngoing(true)
//                        .setProgress(100, 0, false)
                        .setAutoCancel(true)
                return builder


            }else{

                val builder = Notification.Builder(context)
                        /*.setContentTitle(title)
                        .setContentText(content)*/
                        .setSmallIcon(R.drawable.btn_star)
//                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .setOngoing(true)
//                        .setProgress(100, 0, false)
                        .setAutoCancel(true)
                return builder

            }

        }
    }
}