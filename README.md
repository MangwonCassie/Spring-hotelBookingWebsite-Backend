
<h3>📌📎 [프로젝트 # HOTEL BOOKING WEBSITE])</h3>
[FRONT-END GITHUB ADDRESS]([https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Front/blob/master/README.m](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Front)d)
<br>
- You can find the history for adding functions for front side if you click the link above.

참여 인원 : 1명
개발 기간: 24.02~ING

| OS           | Windows 10    |
| ------------ | ------------- |
| Language     | <img src="https://img.shields.io/badge/Java-F7DF1E?style=for-the-badge&logo=Java&logoColor=white">   |
| FrameWork    | <img src="https://img.shields.io/badge/SpringBoot-F7DF1E?style=for-the-badge&logo=SpringBoot&logoColor=white">   |
| IDE          | <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/VisualStudio-007ACC?style=for-the-badge&logo=visualstudio&logoColor=white">  |
| Database     |  <img src="https://img.shields.io/badge/MySQL-47A248?style=for-the-badge&logo=MySQL&logoColor=white">  |
| Library      |<img src="https://img.shields.io/badge/REACT-61DAFB?style=for-the-badge&logo=react&logoColor=white">  |
| Version Control        | <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">  |
| Server        | <img src="https://img.shields.io/badge/EC2-181717?style=for-the-badge&logo=EC2&logoColor=white">  |
| ETC        | <img src="https://img.shields.io/badge/WinSCP-181717?style=for-the-badge&logo=WinSCP&logoColor=white">  <img src="https://img.shields.io/badge/Putty-181717?style=for-the-badge&logo=Putty&logoColor=white">|
<br/>
<br>

<h3>🔸ERD </h3>
추후 업데이트 예정
<br><br>

<h3>🔸시연사진 </h3>
업데이트 예정
<br><br>

<h3>🔸주요 기능 에러 해결 과정 </h3>

- Spring Security 설정 후 CORS 에러
- ![error1](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/9f40284e-a749-4322-acba-a59b5f11bfc4)


- ![error2](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/a464de3d-a3c5-448f-bcc7-619451bb7f4a)

<br>
- 🛠MVC용으로 CorsConfig 클래스에 정의된 addCorsMapping 메소드 외 추가로 SecurityConfig에 addCorsMapping 로직을 별도로 추가 (경로 설정 시 * 와일드 카드 사용 불가)<br>
- CorsConfig 클래스의 addCorsMapping method<br>


```@Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:9192", "http://127.0.0.1:5173/","http://127.0.0.1:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("Authorization", "Content-Type", "Accept")
                    .maxAge(MAX_AGE);
        }
```

---

- SecurityConfig 클래스의 addCorsMapping method<br>
<br>


``` @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5173")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(6000);
    }
```
<br>

![booking1](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/c8a00ac3-580f-4d3b-8bf0-d647991f0f07)
![booking2](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/1cbb6226-8322-4ca9-a404-256b83f0af6d)
![booking3](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/b3addf20-a402-4f45-a747-764311cecb33)

- adult가 0 기본값으로 설정되는 문제
- Hibernate: select r1_0.id,r1_0.is_booked,r1_0.photo,r1_0.room_price,r1_0.room_type from room r1_0 where r1_0.id=?
Hibernate: select b1_0.room_id,b1_0.booking_id,b1_0.adults,b1_0.children,b1_0.confirmation_code,b1_0.check_in,b1_0.check_out,b1_0.guest_email,b1_0.guest_full_name,b1_0.total_guest from booked_room b1_0 where b1_0.room_id=?
Hibernate: insert into booked_room (adults,children,confirmation_code,check_in,check_out,guest_email,guest_full_name,room_id,total_guest) values (?,?,?,?,?,?,?,?,?)
Hibernate: update room set is_booked=?,photo=?,room_price=?,room_type=? where id=?

- BookingResponse에 필드명과 mysql 컬럼 같게 하는 거는 해결을 못하고, BookedRoom의 생성자 부분을 아래와 같이 변경해야 input값과 동일한 값으로 입력가능함.
- calculateTotalNumberOfGuest 에는 따로 매개변수로 받을 필요 없음 클래스 필드값을 그대로 사용하기 때문임. 

```  public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest = this.NumOfAdults + this.NumOfChildren; //클래스 내부 값 찾아서 외부에서 받는 매개변수 필요가 없음.
    }

    public void setNumOfAdults(int numOfAdults) {
        this.NumOfAdults =  numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren) {
        this.NumOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }
```

<br>
- profile탭에서 예약한 내용이 있지만 나타나지 않는 증상<br>

![profile error](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/e30b93ca-97f7-4ea1-b025-412d16861845)


<br>

- frontend에서 요청보낼 때, cors 에러 방지 에러와 인증을 위한 token를 header에 설정해준다.

```import axios from "axios"

export const api = axios.create({
	baseURL: "http://localhost:9192",
	headers: {"Access-Control-Allow-Origin": "*"}
})

export const getHeader = () => {
	const token = localStorage.getItem("token")
	console.log("Token:", token) // Add this line to log the token
	return {
		Authorization: `Bearer ${token}`,
		"Content-Type": "application/json"
	}
}


<br>
- localstorage의 header에 token, userId, userRole 저장
![token의 userId, token, userRole](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/531b2154-70af-430a-8732-b2bc51c66a18)



```
- @Entity 어노테이션 붙여진 JPA 엔티티임을 나타내는 BookedRoom에서 guestEmail 필드를 사용하여 예약을 찾음.

``` @GetMapping("/user/{email}/bookings")
    public ResponseEntity<List<BookingResponse>> getBookingsByUserEmail(@PathVariable String email) {
        List<BookedRoom> bookings = bookingService.getBookingsByUserEmail(email);
        List<BookingResponse> bookingResponses = new ArrayList<>();
        for (BookedRoom booking : bookings) {
            BookingResponse bookingResponse = getBookingResponse(booking);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);
    }
```


<br>
- 프로필에서 booking 현황을 확인할 수 있게 처리.

![book error solved](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/9b11f3e4-96a9-43bf-a615-4098dddef536)




<h3>🔸서버 배포 과정</h3><br>

- Winscp로 ec2 배포한 인스턴스 연결 후 mysql 설치 <br>
<br>

```
sudo su // 관리자 권한
apt-get update // 패키지 관리자 업데이트  
apt-get install mysql-server // mysql 설치
```

<br>
- sql 파일 export할 때 ansi 인코딩 되있을수도 있으므로, 메모장 다른이름으로 저장할 때 인코딩 방식 etf-8로 변경해야함<br>
- winscp에서 ec2 컴퓨터로 sql파일 업로드 하기  <br>
- mysql -u root -p <br>
- ALTER USER 'root'@'localhost' IDENTIFIED BY '12341234';  <br>
- (ALTER 전에 SELECT user, plugin FROM mysql.user WHERE user = 'root'; 했었을 때 root 사용자 (혹은 사용하는 사용자)가 mysql_native_password로 설정되어있어야함)<br>
- exit (mysql 나감) <br>
- mysql -u root -p < Dump20240507-utf.sql <br>
- SHOW DATABASES;  <br>
- use 쓰려는 데이터베이스명 <br>
- SHOW TABLES  <br>
- SELECT * FROM booked_room; <br><br>

	
 <h3>🔸ec2에 설치된 mysql과 로컬 mysql workbench 연결하기 </h3> <br>
 

- EC2에 인바운드 설정에도 3306 포트에 대한 문을 열어준다.

![ec2-1](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/860c6476-07d4-496d-b49a-5746d6c4b08e)

- mysql -u root -p // winscp 에 로그인 후 mysql 접속 <br>

```
SELECT host, user, authentication_string FROM mysql.user;   //(authentication_string은 password 같은 개념)  <br>
````

![ec2-2](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/d58ee66e-f5ff-4745-9421-bb050015105e)<br>

**host가 원격으로 접속할 수 있는 test 사용자를 만들어야 접속가능하다. 현재 로컬에만 가능한 사용자만 있는 상태 <br>

```

CREATE USER 'test'@'%' IDENTIFIED BY '12341234';

GRANT ALL PRIVILEGES ON *.* TO 'test'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;
```



![ec2](https://github.com/MangwonCassie/Spring-hotelBookingWebsite-Backend/assets/129250487/68f86611-5612-4df8-9660-2429bad829ec)






