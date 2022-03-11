package com.example.to_do_list

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //오른쪽 상단아래 버튼 클릭 시
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            getResult.launch(intent)
        }

        //처음 실행이 아닌 그 후에 저장된 값을 불러오자. savedInstanceState는 bundle.
        datas=savedInstanceState?. let { //bundle 객체로 넘어곤 값이 null이 아닌 경우 그 상태값을 목록으로 사용
            it.getStringArrayList("datas")?.toMutableList()
        } ?: mutableListOf()

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return super.registerReceiver(receiver, filter)
    }

    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        //AddActivity가 넘긴 값을 받아야함
        it.data!!.getStringExtra("result")?.let {
            datas?.add(it) //항목을 추가하고
            adapter.notifyDataSetChanged() // 명령을 내림
        }

    }

    //액티비티 상태를 유지하기 위함
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }
    
}