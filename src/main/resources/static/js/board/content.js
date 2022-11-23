'use strict';

import axios from '/js/common/axios.js';

class Content{

    constructor(){
        this.dom = {
            title : document.getElementById('title')
            , content : document.getElementById('content')
            , privateY : document.getElementById('privateY')
            , privateN : document.getElementById('privateN')
            , contentAddBt : document.getElementById('contentAddBt')

        };
        this.init();
    }

    init(){
        this.eventBind();
    }

    eventBind() {
        this.dom.contentAddBt.addEventListener('click', () => this.addContent());
    }

    addContent(e) {
        let param = {
            title : this.dom.title.value,
            content: this.dom.content.value
        };

        if(this.dom.privateN.checked){
            param.privateYn = this.dom.privateN.value;
        }else{
            param.privateYn = this.dom.privateY.value;
        }

        axios.post('/freeboard/add', param)
            .then((res) => {
                console.log(res);
                if (res.data.code === '0000') {
                    alert('추가 완료.');
                    location.href = '/freeboard/content/res.data.seq'
                } else {
                    alert('추가 실패.');
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

}

(() => {

new Content();

})();