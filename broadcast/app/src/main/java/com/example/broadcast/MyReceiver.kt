package com.example.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Log.d("broadcast","MyReceiver...............")
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        //getSystemService :  시스템-레벨 서비스(다른 애플리케이션과 공유 시 시스템으로부터 객체를 얻을 때 사용)를 요청
        val builder : NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //26버전 이상
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                //NotificationChannel : 알림 관리 단위
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                //채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true) // false 시 알림 채널이 알림 뱃지 안뜨게 함
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                //Ringtone : 진동울리기
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            //채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            //채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(context, channelId)

        } else {
            //26버전 이하
            builder = NotificationCompat.Builder(context)
        }

        builder.run {
            //알림의 기본 정보
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle("브로드캐스트")
            var state = intent.getStringExtra("state")
            setContentText("$state")
        }
        manager.notify(11, builder.build())
    }
}