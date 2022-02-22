package com.example.broadcast


import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var state : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 등록 시 사용
        registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!.apply {
            when(getIntExtra(BatteryManager.EXTRA_STATUS, -1)){
                BatteryManager.BATTERY_STATUS_CHARGING -> {
                    when(getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)){
                        BatteryManager.BATTERY_PLUGGED_USB -> { // usb 연결
                            state = "USB 연결"
                            binding.chargingResultView.text="USB 연결"
                            //BitmapFactory : 큰 비트맵을 효율적으로 로드함
                            //디코딩 메서드(decodeByteArray(), decodeFile(), decodeResource())
                            binding.chargingImageView.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.usb))

                        }
                        BatteryManager.BATTERY_PLUGGED_AC -> { //어댑터연결
                            state = "AC 연결"
                            binding.chargingResultView.text="AC 연결"
                            binding.chargingImageView.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.ac))
                        }
                    }
                }
                else -> { // 플러그가 usb, 어댑터 아닌 경우
                    state = "Not 연결"
                    binding.chargingResultView.text="Not 연결"
                }
            }

            val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1) //현재 충전 레벨 조사
            val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1) //ㅐ터리 레벨 최대량
            val batteryPct = level / scale.toFloat() * 100
            binding.percentResultView.text = "$batteryPct %"
        }
        /** 액티비티 화면에 출력 */

        /** 알림 띄우기 */
        binding.button.setOnClickListener {
            val intent= Intent(this, MyReceiver::class.java)
            intent.putExtra("state", state)
            sendBroadcast(intent)
        }
    }
}