### 다이얼로그와 알림
#### 부제 : 카카오톡 알림 만들기
------------------------------------
* 구조
  *  NotificationManager -> notify() <- Notification <-create- NotificationCompat.Builder() <- NotificationChannel()
------------------------------
* 기존코드에서 추가한 점
  * 기존 : 상단바에 카카오톡처럼 알림만 뜸
  * 추가 :  상단바에서 입력한 데이터값(답장)을 본 앱에 출력
    * 즉, 원격입력(RemoteInput)된 입력을 출력
* 2022.02.18
