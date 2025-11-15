# 잉글 (Ingle) - Server Repository
#### Ingle – 인천대학교에 재학 중인 외국인 유학생들을 위한 튜토리얼 기반 맞춤형 지원 서비스

<img width="576" height="582" alt="image" src="https://github.com/user-attachments/assets/a664772b-c2de-46de-a1fc-70e36b1270a2" />
<br><br>
다양한 언어와 문화적 배경을 가진 학생들이 한국 생활과 학업에 원활히 적응할 수 있도록 돕는 것을 목표로 합니다. <Br>
튜토리얼, 지도 등을 통해 학교에 대한 정보와 편의를 제공합니다.

<br><br>

---
<img width="1170" height="2007" alt="image" src="https://github.com/user-attachments/assets/478e8b73-0d72-44cd-bd42-f30f9b631f8c" />

<br>

# 아키텍처

---

# 주요 기능

🔐 로그인 / 회원가입	학교 포털 계정을 통한 로그인 <br>
🦮 튜토리얼	외국인 학생들의 학교 생활 적응을 위한 맞춤형 튜토리얼 제공 <br>
🗺️ 지도	3D 기반 학교 건물 지도 <br>
✍🏻 스탬프	스탬프 보상을 통한 튜토리얼 동기 자극 <br>
🛠️ 관리자	회원/스탬프 관련 통계 <br>
💬 피드백	서비스 개선을 위한 피드백 기능 <br>

---

# 📌 기술적 고민

Redis 캐싱 적용을 통한 성능 최적화 <br> 
다중 데이터 소스 분리 <br>
Redis 캐시 역직렬화(JSON) 문제 해결 <br>
