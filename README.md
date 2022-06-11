## SSG 백엔드 과제 1번 - 홍성진

***

### ⚙️ Environment.
**packaging** : `jar`

**Language** : `Java11`

**Build Tool** : `Gradle`

**Framework** : `Spring Boot 2.7.0`

**Database** : `H2 In Memory DB`

**Document** : `Swagger 3.0`

***

### 🔧 Installation.

```shell
./gradlew init
./gradlew build
java -jar ./build/libs/product-info-api-0.0.1-SNAPSHOT.jar
```

***

### ✏️ Documents.

```shell
http://localhost:8080/swagger-ui.html
```

**초기 사용법**
- 회원가입및 로그인을 제외한 모든 요청에는 Jwt Token Authorization이 필요합니다.
1. **user-api-controller** 를 통해 회원가입을 진행합니다.
2. **user-api-controller** 를 통해 로그인을 진행합니다. (로그인 성공 시, Jwt Token 발급)
3. **Global Authorization** 에 발급 받은 Jwt Token 을 입력합니다.
4. Jwt Token 인증 성공 시, 모든 요청에 대한 응답을 확인할 수 있게됩니다.

***

### 🚀 Description

**Erd**

<img width="80%" alt="Erd" src="https://user-images.githubusercontent.com/56334761/173187924-aa7d925e-e907-4276-8032-f29c60f465be.png">

**Application Architecture**

<img width="80%" alt="architecture" src="https://user-images.githubusercontent.com/56334761/173188504-b1d9bf11-d86a-4a97-9d34-fae7967bebbb.png">

**Summary**

- **Domain**
  1. `Item` 과 `Promotion`의 N:N 관계를  `PromotionItem` 을 추가하여 1:N, 1:N 관계로 풀어냈습니다.
  2. 준영속 상태를 고려하여 `equals()` 와 `hashcode()`를 재정의 하였습니다.
  3. `@MappedSuperclass` , `@EnableJpaAuditing` 을 사용하여 엔티티 등록 시, 생성 시간과 update 시간을 저장하도록 설계하였습니다.

- **Core Business Logic**
  1. `Item` 등록 시, 매칭 가능한 `Promotion` 을 `DB` 에서 찾아 연동시켰습니다.
  2. `Promotion` 등록 시, 매칭 가능한 `Item`을 `DB` 에서 찾아 연동시켰습니다.
  3. 주문 가능한 `Item` 조회 시, 탈퇴한 회원이 조회를 할 때 예외가 발생하도록 설계하였습니다.
  4. `Item` 과 `Promotion` 함께 조회시, `JPA` 의 `default-batch-fetch`를 통하여 쿼리 통신의 양이 줄어들도록 설계하였습니다.
  5. 회원가입, 로그인 외의 모든 request는 선제 Jwt Token의 검증을 요하도록 설계하였습니다.

- **Security**
  1. 대칭키 방식을 활용하여 `Jwt Server`를 통하여 `Token`을 발급 받습니다.
  2. 요청에 대한 `Custom Filter` 를 `UsernamePasswordAuthenticationFilter` 전에 등록하여, 
   `Token`의 인증을 선제적으로 담당하도록 설계하였습니다.
