package prj.mob1.prjmob1.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.os.ResultReceiver
import android.os.Bundle
import android.os.Handler


//class NewMoviesReceiver : ResultReceiver() {
// Defines a generic receiver used to pass data to Activity from a Service
class NewMoviesReceiver:BroadcastReceiver()  {

    // Triggered by the Alarm periodically (starts the service to run task)
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, NotificationService::class.java)
        i.putExtra("foo", "bar")
        context.startService(i)
    }

    companion object {
        val REQUEST_CODE = 12345
        val ACTION = "prj.mob1.prjmob1.notification.alarm"
    }
}