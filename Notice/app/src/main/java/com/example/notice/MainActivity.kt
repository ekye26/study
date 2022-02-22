package com.example.notice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.notice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.notificationButton.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            //알림(Notification)을 관리하는 관리자 객체를 운영체제(Context)로부터 소환
            val builder: NotificationCompat.Builder
            //NotificationChannel로 알림 채널 만듦 -> 채널에 정보 대입해 NotificationCompat.Builder만든다
            if (Build.VERSION.SDK_INT >= O) {
                //Oreo 이상에서는 notificationManager 필수 !
                val channelId = "one-channel" // 알림채널 식별자
                val channelName = "My Channel One" // 알림채널의 이름(별칭)

                //알림객체 채널 만들기
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    //채널에 다양한 정보 설정
                    description = "My Channel One Description"
                    setShowBadge(true)
                    val uri: Uri =
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) //푸시 알림 메시지 소리
                    val audioAttributes = AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build()
                    setSound(uri, audioAttributes)
                    enableVibration(true)
                }

                manager.createNotificationChannel(channel) //채널 생성을 요청
                builder = NotificationCompat.Builder(this, channelId) //채널 이용하여 객체 생성
            } else {
                builder = NotificationCompat.Builder(this)
            }
            // NotificationCompat.Builder ->
            builder.run {
                //알림의 기본 정보
                setSmallIcon(R.drawable.small)
                setWhen(System.currentTimeMillis())
                setContentTitle("카카오토")
                setContentText("Hello")
                setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
            }


            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel = "답장" //이 글자 클릭하면 입력 창 뜬다.
            //답장 시 적은 값
            var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            val replyIntent = Intent(this, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )

            manager.notify(11, builder.build())
        }

        val intent: Intent = intent
        val reply_data = intent.getStringExtra("reply")

        binding.reply.text = reply_data

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val reply_data = intent?.getStringExtra("reply")

        val reply : TextView = findViewById(R.id.reply)
        reply.text = reply_data
    }
}
