
## 프로젝트 소개

청과물 가격을 조회할 수 있는 API 중계 웹 애플리케이션으로 토큰 발급, 목록, 가격 조회 API를 제공합니다.

시작 시 테스트 더미 데이터가 생성됩니다.

과일과 채소를 구분하기 위한 인증방식으로 JWT 토큰을 사용했으며 만료 시간은 30분으로 설정되었습니다.

## 설치환경

Project Gradle-Grooby<br>
Spring Boot 3.0.4<br>
Packaging Jar<br>
Java 17<br>

h2database<br>
springdoc-openapi-starter-webmvc-ui:2.0.2<br>
junit: 4.13.1<br>
jjwt:0.9.1<br>
jaxb-api:2.3.1<br>
mockito-core:3.9.0<br>

## 프로젝트 구성 

Spring Boot 애플리케이션을 실행하기 위한 메인 클래스 (src/main/java/com/example/market/FreshMarketApplication)

com/example/market/  애플리케이션의 전체적인 패키지 구조가 이 디렉토리를 기준으로 구성
- common<br> 공통적으로 사용되는 유틸리티 클래스 등이 위치하는 디렉토리
- config<br> 스프링 프레임워크에서 사용하는 환경설정과 관련된 클래스들이 위치하는 디렉토리
- controller<br> 웹 애플리케이션에서 클라이언트로부터 요청을 받고, 그에 대한 응답을 처리하는 클래스들이 위치하는 디렉토리
- domain<br> 애플리케이션에서 사용되는 도메인 객체, 즉 엔티티 객체들이 위치하는 디렉토리
- dto<br> 데이터 전송을 위한 객체들이 위치하는 디렉토리
- interceptor<br> 클라이언트의 요청을 가로채서 처리하는 인터셉터관련 클래스 위치
- repository<br> 데이터베이스와 관련된 작업을 처리하는 인터페이스 클래스 디렉토리
- service<br> 비즈니스 구현하는 역할

src/main/resources/
- h2: 테스트 더미 데이터 관련 설정 sql
- templates: 화면 확인을 위한 html
- static/js: templates 관련 js

## 사용방법

### 사용자단화면
사용자 화면에서 분류를 선택하고 이름을 입력하여 가격을 조회할 수 있습니다<br>
URL http://localhost:8080/

<br>
<img width="60%" src="https://user-images.githubusercontent.com/19386154/225294115-0bc3e101-34fd-4c8c-8b87-6fe932ee2d86.gif"/>
<br>


### Swagger 화면 Vegetable
다음 URL에서 스웨거를 통해 채소 토큰 발급, 목록, 가격 조회 API 확인할 수 있습니다.<br>
URL http://localhost:8080/swagger-ui/index.html

- v1/product/token/vegetable<br>
채소관련 API 호출을 위한 access token을 response header의 Set-Cookie로 발급됩니다.<br>


- v1/product/vegetable/list<br>
header 내 access token이 요구됩니다.<br>
채소 목록을 조회하는 API입니다.<br>


- v1/product/vegetable/item<br>
header 내 access token이 요구됩니다.<br>
이름이 지정된 채소의 가격을 조회하는 API입니다.<br>
토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.<br>

<br>
<img width="60%" src="https://user-images.githubusercontent.com/19386154/225294345-cb317128-ae38-41d7-8498-787f7a7f8275.gif"/>
<br>

### Swagger 화면 Fruit
다음 URL에서 스웨거를 통해 과일 토큰 발급, 목록, 가격 조회 API 확인할 수 있습니다.<br>
URL http://localhost:8080/swagger-ui/index.html

- v1/product/token/fruit<br>
header 내 access token이 요구됩니다.<br>
이름이 지정된 과일의 가격 정보를 조회하는 API입니다.
토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.<br>


- v1/product/fruit/list<br>
header 내 access token이 요구됩니다.<br>
과일 목록을 조회하는 API입니다.<br>


- v1/product/fruit/item<br>
과일관련 API 호출을 위한 access token을 발급합니다.

<br>
<img width="60%" src="https://user-images.githubusercontent.com/19386154/225295716-1a89e038-f9aa-419d-be78-75f600b898be.gif"/>
<br>