'use strict';

import axios from '/js/common/axios.js';

class Login {

    constructor() {
        console.log('init')
        this.dom = {
            // input
            email : document.getElementById('email'),
            password : document.getElementById('password'),
            loginRememberMe : document.getElementById('loginRemeberMe'),
            // button
            loginBt : document.getElementById('loginBt'),
            signUpBt : document.getElementById('signUpBt'),
        };

        this.eventBind();
    }

    eventBind() {
        this.dom.loginBt.addEventListener('click', () => this.login());
        this.dom.signUpBt.addEventListener('click', () => location.href = '/member/create');
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