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
        axios.post('/board/view/' + seq)
            .then((res) => {
                console.log(res)
                console.log(res.data);
                console.log(res.data.data);
                console.log(res.data.data[0]);
                let div =  '<tr>' +
                        ''
            })
            .catch((res) => {
                console.log(res);
            });

    }
}

(() => {

    new View();

})();