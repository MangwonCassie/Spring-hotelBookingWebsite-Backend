
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
- <h4>🛠MVC용으로 CorsConfig 클래스에 정의된 addCorsMapping 메소드 외 추가로 SecurityConfig에 addCorsMappin g별도로 추가 (경로 설정 시 * 와일드 카드 사용 불가)</h4><br>
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

<h3>🔸서버 배포 과정</h3><br>
업데이트 예정


