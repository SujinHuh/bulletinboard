"use strict";
/**
 * [공통] 실시간 시계 (header 영역)
 */
(function realTimeClock (containerID) {
  // 시간 출력 함수
  function printTime () {
    const now = new Date();
    // 요일 구하기
    const week      = ['일', '월', '화', '수', '목', '금', '토'];
    const dayOfWeek = week[now.getDay()];
  
    // 월, 일, 시, 분, 초 출력 (10 이하일때 앞에 0 붙이기)
    const month   = now.getMonth() < 10 ? `0${now.getMonth() + 1}` : now.getMonth() + 1;
    const date    = now.getDate() < 10 ? `0${now.getDate()}` : now.getDate();
    const hours   = now.getHours() < 10 ? `0${now.getHours()}` : now.getHours();
    const minutes = now.getMinutes() < 10 ? `0${now.getMinutes()}` : now.getMinutes();
    const seconds = now.getSeconds() < 10 ? `0${now.getSeconds()}` : now.getSeconds();
  
    document.getElementById(containerID).textContent = `${now.getFullYear()}.${month}.${date} ${dayOfWeek}요일 ${hours}:${minutes}:${seconds}`
  }
    printTime()
    const timerId = setInterval(printTime, 1000)
})('realTime');

// 모달 컨트롤러
const modalController = (function () {
  return {
    open: function (id) {
      document.getElementById(id).style.display = 'block';
    },
    close: function (id) {
      document.getElementById(id).style.display = 'none';
    }
  }
})();