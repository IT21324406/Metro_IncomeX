package com.example.additionaltransactions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class setGoal : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder

    private val channelID = "goal"
    private val desc = "Notifications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_goal)

        val setbtn = findViewById<Button>(R.id.setBtn)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(channelID, desc,NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GRAY
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this)
                .setContentTitle("Budget Goal")
                .setContentText("You have set the budget goal!")
                .setChannelId(channelID)
                .setSmallIcon(R.drawable.info)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher_background))
        }else{
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                builder = Notification.Builder(this)
                    .setContentTitle("Budget Goal")
                    .setContentText("You have set the budget goal!")
                    .setChannelId(channelID)
                    .setSmallIcon(R.drawable.info)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher_background))
            }
        }
        notificationManager.notify(1234, builder.build())
    }
}