### 액티비티와 컴포넌트
#### 부제 : 할 일 앱
------------------------------------
* INTENT
  * 컴포넌트를 실행하려고 시스템에 전달하는 메시지
 
 * onActivityResult()의 deprecated 

   * 기존코드
```
     ...
     startActivityForResult(intent, 10)
     ...
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==10 && resultCode== Activity.RESULT_OK){
            data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
     }
     
```

   * 변경코드
    
    
 ```
 
 ...
 getResult.launch(intent)
 ...
  private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        //AddActivity가 넘긴 값을 받아야함
        it.data!!.getStringExtra("result")?.let {
            datas?.add(it) //항목을 추가하고
            adapter.notifyDataSetChanged() // 명령을 내림
        }

    }
```
------------------------------
* 기존코드에서 추가한 점 
  * 상단바에서 입력한 데이터값(답장)을 본 앱에 출력
  * 즉, 원격입력(RemoteInput)된 입력을 출력
* 2022.02.22
