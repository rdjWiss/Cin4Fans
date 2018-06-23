package prj.mob1.prjmob1.notification

import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.app.PendingIntent
import android.content.Context
import android.content.Intent



/**
 * Created by sol on 23/06/2018.
 */
class UpdateScheduler {

    // Setup a recurring alarm every half hour
    fun scheduleAlarm(act:Context) {
        // Construct an intent that will execute the AlarmReceiver
        val intent = Intent(act.applicationContext, NewMoviesReceiver::class.java)

        // Create a PendingIntent to be triggered when the alarm goes off
        val pIntent = PendingIntent.getBroadcast(act, NewMoviesReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Setup periodic alarm every every half hour from this point onwards
        val firstMillis = System.currentTimeMillis() // alarm is set right away
        val alarm = act.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES , pIntent)
//        AlarmManager.INTERVAL_FIFTEEN_MINUTES
    }

    fun cancelAlarm(act:Context) {
        val intent = Intent(act.applicationContext, NewMoviesReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(act, NewMoviesReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarm = act.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.cancel(pIntent)
    }
}