'use strict';

import axios from '/js/common/axios.js';

class View {

    constructor() {
        console.log(location.pathname); // 현재 페이지의 경로를 반환
        let array = location.pathname.split('/');

        let seq = array[array.length - 1];
        this.getView(seq);
    }

    getView(seq) {
        let a = document.getElementById('contentArea'); //해당 tag에 접근
        axios.post('/board/view/' + seq)
            .then((res) => {
                let view = res.data.data;
                console.log(res)
                console.log(res.data);
                console.log(res.data.data);
                // console.log(res.data.data[0]);

                    let div =  '<tr>' +
                        `<td>${view[seq].title}</td>`+
                        `<td>${view[seq].content}</td>`+
                        '</tr>';
                    a.insertAdjacentHTML("beforeend", div); //js로 dom 요소를 삽입


            })
            .catch((res) => {
                console.log(res);
            });

    }
}

(() => {

    new View();

})();