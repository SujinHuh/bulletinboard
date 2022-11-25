'use strict';

import axios from '/js/common/axios.js';

class View {

    constructor() {
        console.log(location.pathname); // 현재 페이지의 경로를 반환
        let array = location.pathname.split('/');

        let seq = array[array.length - 1];
        // BoardAction-view() 1. 해당 페이지로 들어와서 url에있는 seq를 parsing을 한다음 getView 함수에 전달
        this.getView(seq);
    }

    getView(seq) {
        let a = document.getElementById('contentArea'); //해당 tag에 접근
        //  BoardAction-view() 2. axios를 통해  method post로 아래 URL로 게시글번호를 전달한 상황입니다.
        axios.post('/board/view/' + seq);

            // .then((res) => {
            //     let view = res.data.data;
            //     console.log(res)    // 이건 진짜 web response html status 같은거 포함.
            //     console.log(res.data);  // java에서 내려준 responseVo
            //     // res.data 가 저렇게 json으로 찍히잖아요..
            //     // 근대 data가 null이면
            //     console.log(res.data.data); // responseVo에 set한 data
            //     // null이찍히고 아래에는 null 참조할려하니 에러가나는거
            //     // 그러면 data를 우선 java에서 보내줘야되잖아요 그래야지 그data가지고 화면을 그릴수있겠죠?
            //     // /baord/view url로 가서 select하는걸 만들어야됩니다.
            //     console.log(res.data.data[seq]); // 이건내려준게 어레이 배열형태일때문
            //
            //     // 지금 에러나는건 data를 넣어준거없는데
            //     // null객체를 참조해서 0번째 프로퍼티 or 배열을 가져오려다보니 에러가나는거에요
            //
            //     // ok?
            //
            //         let div =  '<tr>' +
            //             `<td>${view.title}</td>`+
            //             `<td>${view.content}</td>`+
            //             '</tr>';
            //         a.insertAdjacentHTML("beforeend", div); //js로 dom 요소를 삽입
            //
            //
            // })
            // .catch((res) => {
            //     console.log(res);
            // });

    }
}

(() => {

    new View();

})();