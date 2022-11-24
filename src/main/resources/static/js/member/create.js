'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class Create {

    constructor() {
        this.dom = {
            loginBt : document.getElementById('loginBt'),
            signUpBt : document.getElementById('signUpBt'),

            name : document.getElementById('name'),
            email : document.getElementById('email'),
            password : document.getElementById('password'),
        };

        let success = {
            title: '회원가입',
            body: '회원가입에 성공하였습니다. \n 로그인 해주세요.',
            callback: [{
                        name : '완료',
                        callback : () => location.href = '/member/login'
                    }]
        }

        let fail = {
            title: '회원가입',
            body: '회원가입에 실패하였습니다. \n 확인 해주세요.'
        }

        this.failModal = new Modal(fail);
        this.successModal = new Modal(success);

        this.eventBind();
    }

    eventBind() {
        this.dom.signUpBt.addEventListener('click', () => this.create());
        this.dom.loginBt.addEventListener('click', () => location.href = '/member/login');
    }

    create() {
        let param = {
            name : this.dom.name.value,
            email : this.dom.email.value,
            password : this.dom.password.value,
        };

        // if(param.name === ''){
        //     this.failModal.setBody('name 값 확인');
        //     this.failModal.open();
        //     return;
        // }
        //
        // if(param.email === ''){
        //     this.failModal.setBody('email 값 확인');
        //     this.failModal.open();
        //     return;
        // }
        //
        // if(param.password === ''){
        //     this.failModal.setBody('password 값 확인');
        //     this.failModal.open();
        //     return;
        // }

        axios.post('/member/create', param)
            .then((res) => {
                console.log(res);
                if(res.data.code === '0000'){
                    this.successModal.open();
                }else{
                    this.failModal.setBody(res.data.message);
                    this.failModal.open();
                }
            })
            .catch((res) => {
                console.log(res.response);
                this.failModal.setBody(res.response.data.message);
                this.failModal.open();
            });
    }
}

(() => {

    new Create();

})();