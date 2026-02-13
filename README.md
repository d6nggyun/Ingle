# 🌍 Ingle - 외국인 유학생 맞춤형 지원 플랫폼

> **Ingle**은 인천대학교 외국인 유학생들의 학교 적응을 돕기 위한  
> 튜토리얼 기반 맞춤형 지원 서비스입니다.

---

## 📌 프로젝트 소개

Ingle은 다양한 언어와 문화적 배경을 가진 외국인 유학생들이  
한국 생활과 학업에 원활히 적응할 수 있도록 지원하는 플랫폼입니다.

튜토리얼 기반 가이드, 3D 캠퍼스 지도, 스탬프 보상 시스템 등을 통해  
학교 적응 과정을 구조적으로 지원하도록 설계했습니다.

---

## 📱 서비스 화면

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/cd26ee88-f009-4ded-8508-b7a09c7b1b59" width="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/bca93cb1-6b69-48d2-ac3a-51474b196e24" width="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/290d390e-62bc-45a3-bf3a-59caae15b030" width="250"/>
    </td>
  </tr>
</table>

---

## 🏗 시스템 아키텍처

<img width="2290" height="1162" alt="architecture" src="https://github.com/user-attachments/assets/e8d90ea6-9789-4121-a3b9-03303095d1eb" />

---

## ⚙️ 주요 기능

| 기능 | 설명 |
|------|------|
| 🔐 로그인 | 학교 포털 계정 기반 로그인 |
| 🦮 튜토리얼 | 외국인 학생 맞춤형 적응 가이드 제공 |
| 🗺️ 3D 지도 | 캠퍼스 건물 위치 및 정보 제공 |
| ✍🏻 스탬프 | 튜토리얼 완료 보상 시스템 |
| 🛠 관리자 | 회원 및 스탬프 통계 관리 |
| 💬 피드백 | 서비스 개선을 위한 사용자 의견 수집 |

---

## 🔎 핵심 기술 설계 및 문제 해결

### 1️⃣ Redis 캐싱 기반 지도 조회 성능 최적화

지도 화면 범위(bounds)에 따라 건물 데이터를 조회하는 구조에서  
동시 사용자 증가 시 DB 부하 및 응답 지연 가능성이 존재했습니다.

#### 해결

- 조회 범위를 Key로 사용하여 Redis 캐싱
- 동일 영역 요청 시 DB 대신 캐시 응답 반환
- TTL 설정을 통한 데이터 최신성 및 메모리 효율 확보

#### 결과

✔ 지도 조회 응답 속도 **47ms → 21ms (약 55% 단축)**  
✔ DB 조회 횟수 감소  
✔ 동시 사용자 증가 환경에서도 안정적 성능 유지  

---

### 2️⃣ 다중 데이터소스 분리 설계 (MySQL + Oracle)

Hibernate 공통 설정으로 인해 Oracle에서 MySQL 전용 SQL이 실행되어  
`ORA-00933` 오류 발생.

#### 해결

- MySQL 전용 DataSource / EntityManagerFactory / TransactionManager 구성
- `@EnableJpaRepositories`로 MySQL Repository 패키지 명시적 분리
- Oracle은 JPA 제거 후 JdbcTemplate 기반 독립 구성
- 엔티티 스캔 범위를 MySQL 전용 패키지로 제한

#### 결과

✔ DB 간 SQL 충돌 문제 해결  
✔ 데이터 접근 계층 책임 명확화  
✔ 멀티 데이터소스 환경에서 안정적 구조 확보  

---

### 3️⃣ Redis 직렬화/역직렬화 문제 해결

`@Cacheable` 적용 후 record 타입 DTO 역직렬화 과정에서  
`ClassCastException` 및 `HttpMessageNotWritableException` 발생.

#### 원인

- record는 생성자 기반 역직렬화를 사용
- `ParameterNamesModule` 미등록 시 생성자 매핑 실패
- `Jdk8Module`, `JavaTimeModule` 미등록 시 Optional/LocalDateTime 처리 실패
- 타입 메타데이터 미포함으로 LinkedHashMap으로 역직렬화

#### 해결

- Redis 전용 ObjectMapper 별도 구성
  - `ParameterNamesModule`
  - `Jdk8Module`
  - `JavaTimeModule`
- `activateDefaultTyping` 활성화로 타입 메타데이터 포함
- `GenericJackson2JsonRedisSerializer` 직접 구성 후 RedisCacheConfiguration 적용

#### 결과

✔ record 및 Java 8 타입 정상 역직렬화  
✔ 캐시 기반 조회 안정화  
✔ 직렬화/역직렬화 메커니즘 이해 기반 문제 해결  

---

## 🛠 기술 스택

| 영역 | 기술 |
|------|------|
| Backend | Spring Boot |
| Database | MySQL, Oracle |
| Cache | Redis |
| Infra | Docker, Nginx |
| Storage | Amazon S3 |

---

## 📈 프로젝트 성과

- Redis 캐싱을 통한 지도 조회 성능 개선
- 다중 데이터소스 분리를 통한 구조 안정화
- 직렬화 전략 개선으로 캐시 계층 예외 제거
- 외국인 유학생 대상 맞춤형 지원 서비스 MVP 구현
