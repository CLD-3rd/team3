# 🏃 FastPick - 선착순 응모 서비스

실시간 선착순 응모 기반의 서비스입니다. Redis 기반의 빠른 처리와 사용자 경험을 극대화한 인프라 구성으로 안정성과 퍼포먼스를 동시에 잡았습니다.

---

## 📌 핵심 기능

- **회원가입 / 로그인**
  - 사용자 인증 및 세션 관리
- **상품 목록 조회**
  - 응모 가능 상품과 완료 상품을 직관적으로 구분
- **선착순 응모 시스템**
  - 상품별 실시간 응모 가능
- **Redis Pub/Sub 기반 실시간 당첨 알림**
  - 빠른 피드백 제공
- **응모 제한 및 관리 기능**
  - 동일 상품 1회 응모 제한 (중복 방지)

---

## 👨‍👩‍👧‍👦 팀원

- 김정모 (팀장)  
- 백주희 (부팀장)  
- 조성욱 (팀원)  
- 김재용 (팀원)  
- 장민지 (팀원)  
- 김덕중 (팀원)

---

## 🛠 기술 스택

- **백엔드 개발**: Java 17, Spring Boot 3, Spring Data JPA, Maven  
- **인프라 / 클라우드**: AWS (EKS, RDS, ElastiCache for Redis, ALB, IAM, Bastion EC2)  
- **컨테이너 / 배포**: Docker, DockerHub, Kubernetes, Helm, ArgoCD, Kustomize, ALB Ingress Controller  
- **인프라 자동화**: Terraform  
- **CI/CD & GitOps**: GitHub Actions, ArgoCD  
- **모니터링 / 테스트**: Prometheus, Grafana, k6  
- **운영 도구**: Termius, STS, Visual Studio Code

---
## 🗂 ERD

<img width="695" height="150" alt="erd" src="https://github.com/user-attachments/assets/03c83584-c87f-41f5-99f6-14ff1337d8f0" />

---
## 🧠 시스템 아키텍쳐
<img width="899" height="487" alt="시스템 아키텍쳐" src="https://github.com/user-attachments/assets/2f41edb7-1167-4e29-8452-3661262e557c" />



---
## 🎨 화면구성

<table>
  <tr>
    <td colspan="2" align="center"><strong>🟦 메인 페이지</strong></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <img width="100%" height="auto" alt="메인화면" src="https://github.com/user-attachments/assets/66429f87-d4c4-4207-8bdd-cb71d4a1acc6" />
    </td>
  </tr>
</table>

<br><br>

<table>
  <tr>
    <td align="center"><strong>회원가입 화면</strong></td>
    <td align="center"><strong>로그인 화면</strong></td>
  </tr>
  <tr>
    <td><img width="367" height="406" alt="회원가입" src="https://github.com/user-attachments/assets/55daedfd-7fbf-495f-a0b8-207dc1631a8d" /></td>
    <td><img width="371" height="302" alt="로그인" src="https://github.com/user-attachments/assets/15c72a00-051b-4efa-8e9a-c99be1f9a803" /></td>
  </tr>
</table>

<br><br>

<table>
  <tr>
    <td colspan="2" align="center"><strong>🎯 응모 요청 화면</strong></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <img width="724" height="343" alt="응모 요청 화면" src="https://github.com/user-attachments/assets/f438459e-f9a9-4079-894a-a35c0b54b771" />
    </td>
  </tr>
</table>

<br><br>

<table>
  <tr>
    <td colspan="2" align="center"><strong>🥳 응모 성공 팝업</strong></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <img width="447" height="137" alt="alert" src="https://github.com/user-attachments/assets/62888490-5826-44aa-a9da-094db4579a86" />
    </td>
  </tr>
</table>

<br><br>

<table>
  <tr>
    <td colspan="2" align="center"><strong>🛑 응모 마감 후 화면</strong></td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <img width="324" height="385" alt="응모 완료" src="https://github.com/user-attachments/assets/5b704ef4-dab6-47f0-be93-74b516d28f34" />
    </td>
  </tr>
</table>




---
### 🏗 인프라 (Terraform) 폴더 구성
```bash
📁 terraform/
├── main.tf         # 루트 구성: 모듈 호출, 변수 선언 등 - vpc,eks등 모듈 연결 + s3구성
├── variables.tf      # 루트에서 사용할 변수들
├── outputs.tf       # 루트에서 외부로 내보낼 값들
├── provider.tf       # AWS, region, 버전 정보
├── modules/        # 재사용 가능한 모듈들
│ ├── vpc/        # VPC, subnet, route table, IGW, NAT 등
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── eks/        # EKS 클러스터 + 노드 그룹
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── iam/        # IAM Role, IRSA 등
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── bastion/                
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── redis/       # ElastiCache Redis 단일 노드
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── alb/        # ALB, Target Group, Listener, Ingress용
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ ├── rds/        # RDS MySQL 인스턴스
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
│ └── monitoring/     # Prometheus + Grafana + Ingress
│ │ ├── main.tf
│ │ ├── variables.tf
│ │ └── outputs.tf
```
---
### 📈 모니터링 메트릭 (k5 테스트)

- ✅ 응모 성공률 = 성공 수 / 전체 응모

- ❌ 응모 실패율 = (정원초과 + 중복 응모) / 전체 응모

- ⚡ http.server.requests: 요청 응답 속도

- 🔥 system.cpu.usage: CPU 사용률 모니터링
  
---
### ❌ 성공하지 못한 부분

- **Ingress + ALB 자동화 실패**
  - Terraform으로 ALB 및 Ingress를 자동화하려 했지만, 보안 그룹, 타겟 그룹, listener 설정 간의 의존성과 동기화 문제가 발생하여 수동으로 처리함.

- **Prometheus, Grafana 설치 자동화 실패**
  - Terraform Helm provider를 통해 설치를 시도했으나, 시간문제로 인해 수동으로 Helm 명령어로 설치함.

- **k6 부하 테스트 결과 수집 및 Grafana 시각화 실패**
  - k6 테스트는 성공했으나, 결과를 Prometheus에 연동하여 Grafana에서 시각화하려고 했지만 시간 문제로 구현하지 못함
