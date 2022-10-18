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
            loginRememberMe : document.getElementById('loginRemeberMe'),
            // button
            loginBt : document.getElementById('loginBt'),
            signUpBt : document.getElementById('signUpBt'),
        };

        this.init();
    }

    init() {
        this.eventBind();
    }

    eventBind() {
        this.dom.loginBt.addEventListener('click', this.login.bind(this));
        this.dom.signUpBt.addEventListener('click', function() { location.href = '/member/create'});
    }

    login(e) {
        let param = `email=${this.dom.email.value}` +
                    `&password=${this.dom.password.value}` +
                    `&loginRememberMe=${this.dom.loginRememberMe.value}`;

        console.log(param);

        axios.post('/member/login/process', param)
            .then((res) => {
                console.log(res);
                if(res.data.result){
                    location.href = '/';
                }else{
                    alert(res.data.message);
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new Login();

})();