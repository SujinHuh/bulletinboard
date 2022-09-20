'use strict';

import axios from '../common/axios.js';

class Index {

    constructor() {
        this.dom = {
            kakaoMapParsingButton: document.getElementById('kakaoMapParsingButton')
            ,
        };

        this.init();
    }

    init() {
        console.log('init!!');
        this.eventBind();
    }

    eventBind() {
        console.log('eventBind!!');

        let parsingEvent = () => {
            console.log('parsingEvent start');
            axios.post('/parsing/kakaoMap')
                .then((res) => {
                    console.log(res);
                })
                .catch((res) => {
                    console.log(res);
                });
            console.log('parsingEvent end');
        };

        this.dom.kakaoMapParsingButton.addEventListener('click', parsingEvent);
    }

}

(() => {

    new Index();

})();