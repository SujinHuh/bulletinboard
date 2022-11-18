서버 배포시\
sudo java -Dfile.encoding=utf8 -jar -Dspring.profiles.active=product lms-0.0.1-SNAPSHOT.jar &


개발 관련\
thymeleaf 사용을 지양한다.\
(FE 도입 하지않고 구성이 어려운 Layout, Session, Token 등은 예외)

GetMapping 페이지 이동

PostMapping 비동기 호출

페이지 진입 후 모든 Dom 구성은 비동기로 호출

Javascript는 es6기반 class기반으로 진행한다.