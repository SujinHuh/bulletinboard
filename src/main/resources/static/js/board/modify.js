'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class Modify {
    constructor() {
        let array = location.pathname.split('/');
        this.seq = array[array.length - 1]; // BoardAction-view() 1. 해당 페이지로 들어와서 url에있는 seq를 parsing을 한다음 getView 함수에 전달
        this.dom = {
            title : document.getElementById('title')
            , content : document.getElementById('content')
            , privateY : document.getElementById('privateY')
            , privateN: document.getElementById('privateN')
            , contentModifyBt : document.getElementById('contentModifyBt')
        }
        this.init();
        this.getView(this.seq);
    }
    init(){
        this.eventBind();
    }

    eventBind() {
        this.dom.contentModifyBt.addEventListener('click',() => this.modifyContent());
    }

    modifyContent() {
        let param = {
            title: this.dom.title.value
            , content : this.dom.content.value
            , seq : this.seq
        };
        if(this.dom.privateN.checked) {
            param.privateYn = this.dom.privateN.value;
        } else {
            param.privateYn = this.dom.privateN.value;
        }

        axios.post('/board/modify', param)
            .then((res) => {
                console.log(res);
                if (res.data.code === '0000') {
                    alert('추가 완료.');
                    location.href = `/board/view/${this.seq}`
                } else {
                    alert('추가 실패.');
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

    getView(seq){
        let area = document.getElementById('contentArea');
        axios.post('/board/view/' + seq) //비동기
            .then((res) => { //res -> action에서 return response.
                let view = res.data.data;
                console.log(res); //
                console.log(res.data);
                console.log(res.data.data);

                this.dom.title.value = res.data.data.title;
                if(view.privateYn === 'Y'){
                    res.data.data.privateY = true;
                    res.data.data.privateN = false;
                }else{
                    res.data.data.privateY = false;
                    res.data.data.privateN = true;
                }
                this.dom.content.value = res.data.data.content;
        })
    }

}

(() => {

    new Modify();

})();