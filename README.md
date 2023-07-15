# Todo List Application - SpringBoot / React

<div align="center">
<img width="500" alt="image" src="https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend/assets/121186383/d1797e43-d8f7-4257-8ff5-c62f74434bee">



[데모 사이트 바로가기](https://todo.olooe.city)

</div>

---

## 프로젝트 소개

> **To Do List Application** <br> > **개발기간: 2022.05.10 ~ 2022.05.24** <br>
> Demo Site : https://todo.olooe.city

<br>

교재 (React.js, 스프링 부트, AWS로 배우는 웹 개발 101 - 김다정)를 참고하여 어플리케이션을 제작하였습니다. SpringBoot의 사용 방식과 React와 같은 싱글 페이지 어플리케이션의 동작 방식을 익히고 Oauth 2.0을 통한 다양한 소셜 로그인을 구현하는 것을 목표로 하여 프로젝트를 진행하였습니다. 더불어 JWT를 사용한 로그인 유지 기능과 Spring Security를 통한 인증을 구현하는 것을 중점으로 구현하였습니다.

개발환경은 SpringBoot와 React을 사용하여 프론트엔드와 백엔드 서버가 분리된 아키텍처를 구현하였고 DataBase는 MariaDB를 사용하였습니다.

[Frontend Git 바로가기](https://github.com/olo02/AWS_fullstack_personal_project_Todo_Frontend)

<br>

---

## 시작 가이드

### Requirements

Backend

- [Java version 1.8](https://www.oracle.com/java/technologies/downloads/#java17)
- SpringBoot 2.7.14(SNAPSHOT)
- Gradle - Groovy
- <details markdown="1">
    <summary>DataBase 연결 - application-datasource.yml 추가</summary>

    ```
    spring:
        datasource:
            driver-class-name: org.mariadb.jdbc.Driver
        username: 계정이름
        password: 계정비밀번호
        url: 계정url
    ```

    </details>
- <details markdown="1">
    <summary>Oauth API key - application-oauth2.yml 추가</summary>

    ```
    spring:
        security:
            oauth2:
                client:
                    registration:
                         github:
                            client-id: 클라이언트 아이디
                            client-secret: 시크릿 키
                            redirect-uri: "{baseUrl}/oauth2/callback/{registrationId}"
                            scope:
                             - user:email
                             - read:user
                        google:
                            client-id: 클라이언트 아이디
                            client-secret: 시크릿 키
                            redirect-uri: "{baseUrl}/oauth2/callback/{registrationId}"
                            scope:
                            - email
                            - profile
                        naver:
                            client-id: 클라이언트 아이디
                            client-secret: 시크릿 키
                            redirect-uri: "{baseUrl}/oauth2/callback/{registrationId}"
                            client-authentication-method: POST
                            authorization-grant-type: authorization_code
                            scope:
                            - email
                            - nickname
                            - mobile
                            client-name: Naver
                        kakao:
                            client-id: 클라이언트 아이디
                            client-secret: 시크릿 키
                            redirect-uri: "{baseUrl}/oauth2/callback/{registrationId}"
                            client-authentication-method: POST
                            authorizationGrantType: authorization_code
                            scope:
                            - account_email
                            - profile_nickname
                            clientName: Kakao
                    provider:
                        github:
                            authorization-uri: https://github.com/login/oauth/authorize
                            token-uri: https://github.com/login/oauth/access_token
                            user-info-uri: https://api.github.com/user
                        naver:
                            authorization-uri: https://nid.naver.com/oauth2.0/authorize
                            token-uri: https://nid.naver.com/oauth2.0/token
                            user-info-uri: https://openapi.naver.com/v1/nid/me
                            user-name-attribute: response
                        kakao:
                            authorizationUri: https://kauth.kakao.com/oauth/authorize
                            tokenUri: https://kauth.kakao.com/oauth/token
                            userInfoUri: https://kapi.kakao.com/v2/user/me
                            userNameAttribute: id


    ```

    </details>

Frontend

- node.js with npm

### Installation

Backend

```bash
$ git clone https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend.git
```

Frontend

```bash
$ git clone https://github.com/olo02/AWS_fullstack_personal_project_Todo_Frontend.git
```

### 실행

Backend(localhost:8080)와 Frontend(localhost:3000)를 모두 실행해야 어플리케이션을 확인할 수 있습니다.

<br>

---

## 설계

### Stacks

- #### Environment

    <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellij Idea&logoColor=white">

- #### Language

  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <br>
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">

- #### Framework & Library

    <img src="https://img.shields.io/badge/Spring MVC-6DB33F?style=for-the-badge&logo=spring&logoColor=white">   
    <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">  
    <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"> <br>
    <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
    <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white"> 
    <img src="https://img.shields.io/badge/react router-CA4245?style=for-the-badge&logo=reactrouter&logoColor=white">

- #### DataBase

    <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">

- #### Infrastructure

    <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white">
    <img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=Nginx&logoColor=white">
    <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

### ERD

<div align="center">
<img width="400" alt="image" src="https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend/assets/121186383/ee02e351-d9aa-45f7-a7bd-e153974d8dab">
</div>

<br>

---

## 화면 구성

<div align="center">

|                                                                    로그인 페이지                                                                    |                                                                   회원가입 페이지                                                                   |
| :-------------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------: |
| <img width="500" src="https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend/assets/121186383/631960e1-6c78-49d8-9dce-43fdf16a4dc6"/> | <img width="500" src="https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend/assets/121186383/3bb36598-895e-445d-9972-7bd413d3e203"/> |
|                                                                     메인 페이지                                                                     |                                                                                                                                                     |
| <img width="500" src="https://github.com/olo02/AWS_fullstack_personal_project_Todo_Backend/assets/121186383/6117e60e-ddab-42ab-9fde-9a9e76b2701d"/> |                                                                                                                                                     |

</div>

---

## 기능

> [x] 작업완료 [ ] 작업예정

#### 회원가입

- [x] id 중복 검사
- [x] pw 유효성 검사
- [x] 비밀번호 암호화 : Bcrypt

#### 로그인

- [x] 유효성 검사
- [x] Security 인증
- [x] JWT 사용 자동 로그인
- [x] Github 소셜 로그인
- [x] Google 소셜 로그인
- [x] Naver 소셜 로그인
- [x] Kakao 소셜 로그인

#### TodoList

- [x] Rest API를 사용한 SPA 구현
- [x] Todo 추가
- [x] Todo 수정
- [x] Todo 삭제

---

## 아키텍쳐

### 디렉토리 구조

Backend

```bash
├── README.md
├── hello/src/main
│   ├── java/city/olooe/hello
│   │   ├── config : WebMvcConfig 및 WebSecurityConfig 설정
│   │   ├── controller
│   │   ├── dto
│   │   ├── model
│   │   ├── filter
│   │   ├── persistence
│   │   ├── security : 시큐리티 및 Oauth2.0 설정
│   │   ├── service
│   │   └── HelloApplication.java : 프로젝트 실행 파일
│   └── resources
│       ├── application-oauth2.yml : Oauth2.0 설정 추가 필요
│       ├── application-datasource.yml : DataBase 설정 추가 필요
│       └── application.yml : 기본 설정 파일
├── .gitignore
├── build.gradle
├── hello/src/main
├── hello/src/main
└── settings.gradle
```

Frontend

```bash
├── public
├── src
│   ├── service
│   │   └── ApiService.js : Backend server 연결 설정
│   ├── AddTodo.js : Todo 추가 이벤트
│   ├── App.css
│   ├── App.js
│   ├── App.test.js
│   ├── Todo.js : Todo 수정/삭제 이벤트
│   ├── api-config.js : Backend url 설정 => API_BASE_URL
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── reportWebVitals.js
│   └── setupTests.js
├── .gitignore
├── README.md
├── package-lock.json
└── package.json
```

---

## References

- React.js, 스프링 부트, AWS로 배우는 웹 개발 101 - 김다정
- README Template : [parkjiye](https://velog.io/@luna7182/%EB%B0%B1%EC%97%94%EB%93%9C-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-README-%EC%93%B0%EB%8A%94-%EB%B2%95)
- Hit : [hit](https://hits.seeyoufarm.com/)
