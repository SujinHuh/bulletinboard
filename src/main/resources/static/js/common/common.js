"use strict";
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