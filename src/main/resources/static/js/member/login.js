'use strict';

import axios from '/js/common/axios.js';

class Login {

    constructor() {
        const paramsString = location.search;
        let searchParams = new URLSearchParams(paramsString);

        if( searchParams.has('auth') ) {
            // TODO MODAL로 변경 예정
            alert('로그인 해주세요');
            location.href = '/member/login';
        }


        this.dom = {
            // input
            email : document.getElementById('email'),
            password : document.getElementById('password'),
            // button
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
        this.dom.loginBt.addEventListener('click', this.login.bind(this));
        this.dom.signUpBt.addEventListener('click', function() { location.href = '/member/create'});
    }

    login(e) {
        let param = {
            email : this.dom.email.value,
            password : this.dom.password.value
        };
        axios.post('/member/login', param)
            .then((res) => {
                console.log(res);
            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new Login();

})();