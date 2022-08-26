# :iphone: favS2hare - 유튜브 콘텐츠 기반 SNS

<!-- ![로고](./docs/image/favS2hare.png) -->
<img src="./docs/image/favS2hare.png" width="350" height="100">

## :date: 프로젝트 진행 기간
2022.07.11 ~ 2022.08.19 (40일간 진행)
<br>
SSAFY 7기 2학기 공통프로젝트 A803 덕지덕질 - favS2hare

## ✔ 소개

_- 내 최애를 골라보고 POP을 통해 자랑하고 피드를 꾸며 타인과 공유할 수 있는 SNS -_

## ✔ 기획의도

_- 모바일 플랫폼을 통해 아이돌 굿즈, 정보를 편하게 얻을 수 있지만
<br>
그와 더불어 현질을 통해 정보의 차별화를 두기에 모두 공평한 덕질을 하기에 어려움이 있습니다.  
저희는 이를 해결하고자 팬들이 순수하게 덕질하고 팬들끼리의 소통을 할 수 있는 모바일 플랫폼 제작을 기획했습니다. -_

## ✔ 주요 기능

- Pop 
    - Pop이란 favS2ahre에서 자체적으로 정의한 짧은 영상을 의미합니다.
    - 자신이 좋아하는 아티스트의 youtube 동영상을 원하는 구간으로 편집해 쉽게 Pop을 제작할 수 있어요.
    - 내가 시청한 youtube 영상으로 만든 다른 사람들의 Pop을 볼 수 있어요.

- SNS 기능
    - 나와 선호하는 아티스트가 비슷한 유저를 팔로우해서 해당 유저의 Pop을 친구피드에서 볼 수 있어요.
    - 다른 유저가 제작한 Pop에 좋아요, 댓글, 댓글 좋아요를 눌러 자신의 의견을 남길 수 있어요.


- 추천 기능
    - 선호하는 아티스트 정보에 기반하여 youtube 영상을 추천받을 수 있어요.
    - 조회수, 좋아요수, 팔로우 정보 등의 정보를 바탕으로 내가 선호하는 Pop을 추천받아 시청할 수 있어요.
 


## ✔ 주요 기술

---

**Backend - Spring**

- Spring Tool Suite 3
- Spring boot 2.7.1
- Spring Data JPA
- Spring Web
- Swagger 3.0.0
- QueryDSL
- Mysql DB 8.0.30-0
- ubuntu0.20.04.2

**Frontend**

- Visual Studio Code IDE
- Vue 2.7.8
- Vuetify 2.6.7
- Vuex 3.6.2

**CI/CD**

- AWS EC2
- Docker
- NGINX
- Certbot

## ✔ 협업 툴

---

- Git
- Notion
- JIRA
- MatterMost
- Webex
- Discord

## ✔ 프로젝트 파일 구조 
- [파일 구조](./docs/파일구조.md)


## ✔ 팀원 역할 분배

---

![image.png](./docs/image/역할배분.png)


## ✔ 프로젝트 산출물

---

- [기능정의서](./docs/기능정의서.md)
- [와이어프레임](./docs/와이어프레임.md)
- [컴포넌트다이어그램](./docs/컴포넌트_다이어그램.md)
- [API](./docs/API.md)
- [ERD](./docs/ERD.md)
- [기술스택](./docs/기술스택.md)
- [UCC](./docs/UCC.md)

## ✔ 프로젝트 결과물

- [포팅매뉴얼](./docs/포팅매뉴얼.md)
- [중간발표자료](./docs/공통PJT_A803_중간발표자료.pdf)
- [최종발표자료](./docs/공통PJT_A803_최종발표자료.pdf)

## ✔ 프로젝트 파일 구조

---

## favS2ahre 서비스 화면

---

### 회원가입

이메일을 중복해서 사용할 수 없습니다.
<br>
![image.png](./docs/image/회원가입.gif)
<br>

<hr>

### 홈화면

유튜브 탭
<br>
![image.png](./docs/image/유튜브탭.gif)

친구 피드 탭
<br>
![image.png](./docs/image/친구피드.gif)
<br>

<hr>

### 유튜브 상세정보

유튜브 편집 및 북마크
<br>
![image.png](./docs/image/유튜브상세.gif)
<br>

<hr>

### 팝 생성 화면

시간 지정, 피드 설정 후 팝 생성
<br>
![image.png](./docs/image/팝생성.gif)

<br>
<hr>

### 팝 탭

사용자의 선호도에 맞는 팝 리스트
<br>
![image.png](./docs/image/팝탭.gif)

<br>
<hr>

### 팝 상세 화면

팝 좋아요 수, 댓글 상세 정보 확인
<br>
![image.png](./docs/image/팝상세.gif)

<br>
<hr>

### 검색 탭

검색어에 맞는 팝, 동영상, 유저 정보
더보기 제공
<br>
![image.png](./docs/image/검색.gif)

<br>
<hr>

### 프로필 탭

유저의 프로필 정보와 피드 정보
<br>
![image.png](./docs/image/프로필.gif)

<br>
<hr>

### 프로필 정보 변경

비밀번호를 변경할 수 있습니다.
프로필이미지, 닉네임, 자기소개 글을 변경할 수 있습니다.
<br>
![image.png](./docs/image/프로필변경.gif)
<br>

<hr>

### 다중 피드

유저가 생성한 다중 피드에 팝 리스트
<br>
![image.png](./docs/image/다중피드.gif)

<br>
<hr>

### 선호도 조사

유저가 회원가입 후 선호하는 아이돌, 노래 선택
<br>
![image.png](./docs/image/선호도.gif)

<br>
<hr>

### 저장한 유튜브 보기

설정 페이지에서 좋아요한 플레이리스트, 플레이룸, 게시글 목록을 확인할 수 있습니다.
<br>
![image.png](./docs/image/저장영상.gif)

<br>
<hr>

### 로그아웃

로그아웃하면 비회원으로 사용
비회원 사용자는 좋아요, 댓글 특정 화면 제한.
<br>
![image.png](./docs/image/로그아웃.gif)
