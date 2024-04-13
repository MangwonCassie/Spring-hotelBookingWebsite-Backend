
# PROJECTS



<h3>📌📎 [프로젝트 #1  NIKE CLONE WEBSITE PROJECT] (https://github.com/SC-Front-End/Nike-Clone)</h3>

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




<h3>🔸서버 배포 과정</h3><br>
업데이트 예정


