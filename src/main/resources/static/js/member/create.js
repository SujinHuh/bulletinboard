'use strict';

import axios from '/js/common/axios.js';

class Create {

    constructor() {
        this.dom = {
            loginBt : document.getElementById('loginBt'),
            signUpBt : document.getElementById('signUpBt'),
        };

        this.init();
    }

    init() {
        console.log('init!!');
        this.eventBind();
    }

    eventBind() {
        console.log('eventBind!!');
        this.dom.loginBt.addEventListener('click', function() { location.href = '/member/login'});
        this.dom.signUpBt.addEventListener('click', this.create);
    }

    create() {
        axios.post('/member/create')
            .then((res) => {
                console.log(res);
            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new Create();

})();