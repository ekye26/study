### 네트워크 프로그래밍
#### 부제 : Volley와 Retrofit으로 뉴스 앱 만들기
---------------------------------------
Volley
* implementation 'com.android.volley:volley:x.x.x'
* 핵심클래스
	* RequestQueue : 서버 요청자 - 서버에 요청을 보내는 역할
	* XXXRequest : XXX타입의 결과를 받는 요청정보 - 서버 URL과 같은 결과를 가져오는 콜백 정보는 이 객체에 담아서 전송

Retrofit
* implementation 'com.squareup.retrofit2:retorifit:x.x.x'
* interface A(), B() 호출 : 함수만 선언, 어떤 코드도 담지 않는다. 
   --> Retrofit : 실제 통신 시 필요한 코드를 담은 서비스(컴포넌트 X) 객체 만들어 준다.
   -->  A() { }, B() { }
   --> 함수 호출 시 Call 객체 반환 : Call 객체이ㅡ enqueue() 함수를 호출과 동시에 통신 수행
	* 즉, 통신용 함수를 선언한 인터페이스 작성 > Retrofit에 전송 > Retrofit이 서비스 객체 반환 > 함수 호출 후 Call 객체 반환 > Call 객체의 enqueue()함수 호출 > 네트워크 통신
* 애너테이션
	* @GET	
		* 서버와 연동 시 GET 방식으로 요청
		* @GET("users/list?sort=desc")처럼 '?'뒤에 데이터 추가 가능
	* @Path
		* URL 경로 동적으로 지정 시
		* @Path("group/{id}/users/{name}") : {id}, {name}의 값 동적으로 받는다.
	* @Query
		* 함수의 매개변숫값을 서버에 전달

Glide
* 공개 라이브러리
* 이미지의 크기 조절, 로딩 이미지, 오류 이미지 표시 쉽게 구현
----------------------------------
2022.03.15
