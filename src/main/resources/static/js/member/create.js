'use strict';

import axios from '/js/common/axios.js';

class Create {

    constructor() {
        this.dom = {
            loginBt : document.getElementById('loginBt'),
            signUpBt : document.getElementById('signUpBt'),

            name : document.getElementById('name'),
            email : document.getElementById('email'),
            password : document.getElementById('password'),
        };

        this.init();
    }

    init() {
        console.log('init!!');
        this.eventBind();
    }

    eventBind() {
        console.log('eventBind!!');
        this.dom.signUpBt.addEventListener('click', this.create.bind(this));
        this.dom.loginBt.addEventListener('click', function() { location.href = '/member/login'});
    }

    create() {
        let param = {
            name : this.dom.name.value,
            email : this.dom.email.value,
            password : this.dom.password.value,
        };

        console.log(param);

        axios.post('/member/create', param)
            .then((res) => {
                console.log(res);
                if(res.data.code === '0000'){
                    alert('회원가입에 성공하였습니다. \n' +
                        '로그인 해주세요.');

                    location.href = '/member/login';
                }else{
                    alert('실패하였습니다.');
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new Create();

})();