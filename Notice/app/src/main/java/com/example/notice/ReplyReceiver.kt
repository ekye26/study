package com.example.notice

import android.app.Notification
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.notice.databinding.ActivityMainBinding

class ReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        //알림에 적힌 입력글 획득
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_reply")
        Log.d("NOTICE", "replyTxt : $replyTxt")

        //알림 취소
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(11)

        val intent = Intent(context.applicationContext, MainActivity::class.java)
        intent.putExtra("reply",replyTxt)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.applicationContext.startActivity(intent)
    }
}