<div align="center">
  
# [2024] 🎨 palette


프로젝트 협업 도구인 **칸반 보드**를 Team 팔레트를 통해 경험해 보세요🕯<br/>
프로젝트 별로 진행 상태를 구분하고 마감 일정을 지정하여 해당 스케줄을 관리할 수 있습니다!

![image](https://github.com/user-attachments/assets/2e84ece4-16a9-448d-b197-74fe2f212faa)

이 프로젝트는 IntelliJ를 사용하여 개발되었으며, 프론트엔드는 React, 백엔드와 DB 서버는 MySQL을 사용하였습니다. 또한, Docker와 Redis를 활용했습니다.

IntelliJ 상에서 HTTP를 이용해 쉽게 테스트할 수 있으며, 각 기능은 RESTful API를 통해 구현되었습니다. 데이터 보안을 위해 사용자 인증 후에만 모든 기능이 작동합니다.
   
</div>
<br>

## 목차
- [👨‍👦‍👦👩‍👧 Team 🥓](#team)
- [🎨 Tech Stack](#tech-stack)
- [🛫 Features](#features)
- [🎯 구현 기능](#rngus)
- [📑 Technical Documentation](#tech)
- [🌌환경변수](#ghksrud)

<br>

<div id="team">

# 👨‍👨‍👧‍👦 Team 


<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/hamseungwan2023">
          <img src="https://avatars.githubusercontent.com/u/125807759?v=4" width="100px;" alt=""/><br />
          <sub><b> 팀장 : 함승완 </b></sub>
        </a><br />
        <a href="https://velog.io/@syham001/posts">🐹</a>
      </td>
      <td align="center">
        <a href="https://github.com/andrew75313">
          <img src="https://avatars.githubusercontent.com/u/161192870?v=4" width="100px;" alt=""/><br />
          <sub><b> 팀원 : 김현진 </b></sub>
        </a><br />
        <a href="https://andrew75313.tistory.com/">🐰</a>
      </td>
      <td align="center">
        <a href="https://github.com/LeeNaYoung240">
          <img src="https://avatars.githubusercontent.com/u/107848521?v=4" width="100px;" alt=""/><br />
          <sub><b> 팀원 : 이나영 </b></sub>
        </a><br />
        <a href="https://leenayoung240.github.io/ ">🦍</a>
      </td>
      <td align="center">
        <a href="https://github.com/wondo8449">
          <img src="https://avatars.githubusercontent.com/u/54055270?v=4" width="100px;" alt=""/><br />
          <sub><b> 팀원 : 김예찬 </b></sub>
        </a><br />
        <a href="https://velog.io/@wondo8449/posts">🐶</a>
      </td>
    </tr>
  </tbody>
</table>


<details>
<summary>함승완 </summary>
<div markdown="1">


- **사용자 기능 개발**
    - 사용자 JWT기반 인증,인가 구축
    - 권한관리
    - 로그인, 로그아웃, 회원가입 기능 구현
 
- **React FE 구현**
  
- **ERD 관리**

</div>
</details>


<details>
<summary>김현진</summary>
<div markdown="1">

- **컬럼 관리 기능 개발**
    - 컬럼 조회, 생성, 삭제 기능 구현
    - 컬럼 간 순서 이동 기능 구현
      
- 동시성 제어 기능
  
- **Wireframe  관리**
  
- 발표

</div>
</details>

<details>
<summary>이나영</summary>
<div markdown="1"> 
  
- **보드 관리 기능 개발**
    - 보드 조회, 생성, 수정, 삭제 기능 구현
    - 보드 초대 기능 구현
    - react - 보드 기능
      
- Docker기반 환경 구성

- **API 명세서 관리**
  
</div>
</details>

<details>
<summary>김예찬</summary>
<div markdown="1">

- **카드 관리 기능 개발**
    - 카드 조회, 생성, 수정 삭제 기능 구현
      
- **카드 상세 기능 개발**
    - 댓글 작성, 조회 기능 구현
      
- **기능 구현 점검**


</div>
</details>


<br>

<div id="tech-stack">

# 🎨 Tech Stack

| Type       | Tech                                                                                                              | Version                                                                                                           |
| ---------- | ----------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| IDE        |  ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)     |         |
| Framework  |  ![Spring](https://img.shields.io/badge/SpringBoot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)          | 3.3.1       |
| Langage    | ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)                  | JDK17              |
| Database   | ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) <br/> ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) <br/> ![Redis](https://img.shields.io/badge/redis-%23DC382D.svg?style=for-the-badge&logo=redis&logoColor=white)| 8.0.28 <br/>  3.8  <br/> 3.27.1  |
| Tools      | ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)  ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)       |     |
| |[![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)](https://teamsparta.notion.site/c5202390c88b412d87dfa359630e55ec)
 



📌 개발 기간 : 2024.07.10 ~ 2024.07.15 


<br>

<div id="features">

# 🛫 Features

- 사용자 인증 📧

  - 회원가입(일반 사용자/매니저)을 완료한 후 로그인이 가능합니다.


    <details>
    <summary> 회원가입  </summary>
    <div markdown="1">

    ![image](https://github.com/user-attachments/assets/7d719bf8-6304-428a-8d70-a88f6c1165ee)
    - user : id, password 입력
    - manager : id, password, manager-key 입력    


     </div>
    </details>
    
     <details>
    <summary> 로그인  </summary>
    <div markdown="1">

     ![image](https://github.com/user-attachments/assets/1ca3b80c-a295-450d-9252-37dfc9116ee0)

     </div>
    </details>

<br>

- 사용자/관리자 기능 👩🏻‍💻

  - 매니저(Manager)는 보드를 생성, 수정, 삭제, 조회가 가능하며 보드에 사용자를 초대할 수 있습니다. 여기서 일반 사용자를 초대할 경우 사용자는 해당 보드의 조회가 가능해집니다.

    <details>
    <summary> 보드 관리  </summary>
    <div markdown="1">

    ![image](https://github.com/user-attachments/assets/7086c99b-29ed-4b4c-97aa-d1fc2fbf0230)
    ![image](https://github.com/user-attachments/assets/1f170dc6-b025-4539-94bc-22379c0246bf)

    - 보드 등록 버튼을 누를 때
     ![image](https://github.com/user-attachments/assets/98c55ead-2007-47b7-a02e-b9f6249febd4)


    - 마우스를 보드 위에 올릴 때
   
      ![image](https://github.com/user-attachments/assets/fd9acc50-828c-4306-843b-87afb9153fd9)

     
    - 보드 수정 버튼(🔨 ) 누를 때
   
      ![image](https://github.com/user-attachments/assets/df26ed51-f7b3-45dd-b685-46becc86c213)

      
    - 보드 삭제 버튼(❌) 누를 때

      ![image](https://github.com/user-attachments/assets/52459c8a-1e0d-4d7d-afd0-2367dc9bd173)

    - 사용자 번호를 입력하고 초대 버튼(📧)을 누를 때
   
      ![image](https://github.com/user-attachments/assets/06f4f160-61f1-420d-a501-72611baa3cc6)


     </div>
    </details>

  - 매니저(Manager)는 컬럼을 생성, 삭제, 순서 이동할 수 있습니다. 여기서 컬럼 순서는 자유롭게 변경할 수 있습니다.

    <details>
    <summary> 컬럼 관리  </summary>
    <div markdown="1">

    - 컬림 페이지
    
      ![image](https://github.com/user-attachments/assets/ff24cc41-9ec6-40c7-9505-97d5bae4282f)

    - 컬럼 등록 버튼을 누를 때
      
      ![image](https://github.com/user-attachments/assets/0091df00-8b18-4ff3-b2c9-f48138457e47)
 
    - 컬럼 이동 (드래그 앤 드롭)
   
      ![image](https://github.com/user-attachments/assets/ce3ead48-ae29-4db5-8a1b-8ad836c07a7e)


    - 컬럼 삭제 버튼(❌)을 누를 때
   
      ![image](https://github.com/user-attachments/assets/dd05b56f-bd51-4263-aeb9-2ad6c2434194)

     </div>
    </details>
    
  
  - 매니저(Manager)는 카드를 생성, 수정, 삭제, 순서 이동, 조회할 수 있습니다. 여기서 조회는 전체 조회, 작업자별 조회, 상태별 조회가 가능합니다.

     <details>
    <summary> 카드 관리  </summary>
    <div markdown="1">

     - 카드 생성 버튼을 누를 때

    ![image](https://github.com/user-attachments/assets/ccc558b4-231d-469c-9872-f8195a3c9e3f)

    - 카드 생성 후
   
      ![image](https://github.com/user-attachments/assets/9307fb92-8cad-453d-94c7-12fb969ffa69)

    - 카드 제목 수정
   
      ![image](https://github.com/user-attachments/assets/6b1c12a4-7e14-463a-aa2b-1d18b9ed9ed4)

    - Left, Right 버튼을 통해 카드 순서 이동
   
      ![image](https://github.com/user-attachments/assets/3b4c1984-2799-46cf-bef2-0f019a04d1be)


     </div>
    </details>
   
  - 매니저(Manager)는 카드 상세에 댓글을 작성, 조회할 수 있습니다.

    <details>
    <summary> 댓글 관리  </summary>
    <div markdown="1">

     - 댓글 보기 버튼을 누를 때
 
       ![image](https://github.com/user-attachments/assets/22939006-1ef1-46b3-848c-1b118e890c21)


     </div>
    </details>
 
  - 일반 사용자(USER)는 초대를 받은 보드에서만 조회가 가능하며 카드는 생성, 수정, 삭제할 수 있습니다.


       </div>
</details>

<br>

<details>
  <summary> pront ✒ </summary>
   <div markdown="1">

   pront repository Link :  [![Github](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/A08-palette/pallet-pront)

  </div>
  </details>
    

<details>
<summary>DB Role, Status</summary>
<div markdown="1">
  

 ![image](https://github.com/user-attachments/assets/1b9799a6-2d28-4921-97b6-7e5ad82c94b5)



</div>
</details>


<br>

<div id="rngus">

# 🎯 구현 기능
<details>
<summary>🥉 필수 구현</summary>
<div markdown="1">

- [x] 1 단계 : 사용자 인증 기능 
- [x] 2 단계 : 보드 관리 기능
- [x] 3 단계 : 컬럼 관리 기능
- [x] 4 단계 : 카드 관리 기능
- [X] 5 단계 : 카드 상세 기능
- [X] 6 단계 : 동시성 제어
</div>
</details>


<br>

<div id="tech">

# 📑 Technical Documentation

</div>
</details>

<details>
<summary>🧬 ERD DIAGRAM</summary>
<div markdown="1">
  

![image](https://github.com/user-attachments/assets/28965b08-e10f-46ad-88e9-024dbd36f89b)


</div>
</details>


<details>
<summary> 🔨 API 명세서</summary>
<div markdown="1">
  

![image](https://github.com/user-attachments/assets/25d9e23b-b911-4b7b-b035-4b0667b97cdd)
![image](https://github.com/user-attachments/assets/589b97f0-1b34-4df9-84b9-ebb12c254dbe)
![image](https://github.com/user-attachments/assets/6e153d12-b93f-4f7e-a8c6-e64c6bd84326)
![image](https://github.com/user-attachments/assets/fe77c1fa-51b9-4136-9992-b7b1e5c7ef02)
![image](https://github.com/user-attachments/assets/4e4e1843-e819-4c96-b102-27a6fa745247)
![image](https://github.com/user-attachments/assets/1a2fa101-21b9-44fa-86c3-f7ee23a98b8c)
![image](https://github.com/user-attachments/assets/5b75e44a-ac8a-4e06-a8a1-1cdb587c189f)


</div>
</details>



</div>
</details>

<details>
<summary>🔱 Branch Rule</summary>
<div markdown="1">
  
## 🔱  Branch Rule
- main, dev, feature 브랜치 사용.
- feature 브랜치에서 기능 개발 완료시 dev 브랜치로 merge
- 프로젝트 완료시 main 브랜치로 merge
- **feature/#이슈번호**
> ex)  
> feature/#36


</div>
</details>

</div>
</details>


<details>
<summary>🌠 Commit Rule</summary>
<div markdown="1">
  
## 🌠 Commit Rule
- **[#이슈번호] '작업 타입' : '작업 내용'**
> ex)  
> [#36] 🎀 feat : 회원가입 기능 추가
> - 구체적인 내용1
> - 구체적인 내용2
> - 구체적인 내용3
> - 구체적인 내용이 있을 경우을 아래에 작성
> - 여러 줄의 메시지를 작성할 땐 "-"로 구분

<br>

| 작업 타입 | 작업내용 |
| --- | --- |
| 🎀 feat | 새로운 기능을 추가 |
| ✨ update | 해당 파일에 새로운 기능이 추가 구현 |
| 🎉 add | 없던 파일을 생성함, 초기 세팅 |
| 🐛 bugfix | 버그 수정 |
| ♻️ refactor | 코드 리팩토링 |
| 🩹 fix | 코드 수정 |
| 🚚 move | 파일 옮김/정리 |
| 🔥 del | 기능/파일을 삭제 |
| 🍻 test | 테스트 코드를 작성 |
| 🎨 readme | readme 수정 |
| 🙈 gitfix | gitignore 수정 |
| 🔨script | package.json 변경(npm 설치 등) |


</div>
</details>



<details>
<summary>🚀 Code Convention</summary>
<div markdown="1">
  
## 🚀 Code Convention
- **줄 띄우기 구분**
    - 필드 구분(맨 윗줄만 한 줄 띄우고, 필드는 전부 붙여쓰기)
    - 메서드 간의 구분 (1줄 띄우기)
    - return 등 한줄만 있는 메서드 일 경우, 붙여서 정리
        
        ```java
        public Object test() {
                return null; // 한줄로 붙이기
             }
        ```
        
    - if 문 등 조건 문은 **인텔리제이 자동정렬** 기준으로 정리
        
        ```java
        // example
         if (test == 0) {
                    return null;
                }
        ```
        
- **주석 처리**
    - 최대한 불필요한 주석 지양
    - 반드시 공유를 해야하는 중요한 주석 기재 후, 프로젝트 마무리에 전부 정리
- 연산자 좌우 한 칸 띄우기 **인텔리제이 자동정렬 기능 활용**

</div>
</details>

<br>

<div id="ghksrud">
  
# 🌌환경변수
```env
JWT_SECRET_KEY={value}
USERNAME={username}
PASSWORD={password}
MYSQL_DATABASE=palette
ACCESS_EXPIRE_TIME=60000
REFRESH_EXPIRE_TIME=120000
MANAGER_KEY= {Key}
```


