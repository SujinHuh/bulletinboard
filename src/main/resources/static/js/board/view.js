'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class View {

    async constructor() {
        this.dom = {
            modifyBt: document.getElementById('modifyBt')
        };
        console.log(location.pathname); // 현재 페이지의 경로를 반환
        let array = location.pathname.split('/');

        let seq = array[array.length - 1]; // BoardAction-view() 1. 해당 페이지로 들어와서 url에있는 seq를 parsing을 한다음 getView 함수에 전달

        // 1.  modal을 쓰기에 가장먼저 로딩이되야된다.
        this.setModal();
        // 2. 게시글을 가져와야 이벤트를 바인딩할수있다.
        await this.getView(seq);
        // 3. 이벤트를 바인딩 할 태그 + 모달을 띄어야되니까 가장마지막에 이벤트를 바인딩한다.
        if (this.myBbs) {
            this.eventBind();
        }
    }
    eventBind() {   // 버튼에 이벤트는 모달을 셋팅하는게 아닌 선언된 모달의 함수를 이용해야된다.
                    // 지금같은 경우는 open함수를 사용.
        this.dom.modifyBt.addEventListener('click', () => this.modifyModal.open());

    }

    setModal(){  // 한번만 콜되야되는 함수
        let modifyModal = {
            title: '수정',
            body : '수정하시겠습니까?',
            callback : [
                {
                    name : '수정',
                    callback : (target) => this.modify('modify', target)
                },{
                    name: '삭제',
                    callback : (target) => this.modify('delete',target)
                }
            ]
        }

        this.modifyModal = new Modal(modifyModal);

        let notiParam = {
            title : '게시판',
            body : '선택해주세요',
        }
        this.notiModal = new Modal(notiParam);
    }

    //비동기 -> 동기
    async getView(seq) {  // 마찬가지로 게시글을 가져오는 함수이기에 한번만 콜되야된다.
        let a = document.getElementById('contentArea'); //해당 tag에 접근
        //  BoardAction-view() 2. axios를 통해  method post로 아래 URL로 게시글번호를 전달한 상황입니다.
        axios.post('/board/view/' + seq)
            .then((res) => {
                let view = res.data.data;
                console.log(res)    // 이건 진짜 web response html status 같은거 포함.
                console.log(res.data);  // java에서 내려준 responseVo
                // res.data 가 저렇게 json으로 찍힘 data가 null이면
                console.log(res.data.data); // responseVo에 set한 data
                this.myBbs = view.myBbs;
                let div = '<tr>' +
                    `<td>${view.title}</td>` +
                    `<td>${view.content}</td>` +
                    '</tr>';
                a.insertAdjacentHTML("beforeend", div); //js로 dom 요소를 삽입
            })
            .catch((res) => {
                console.log(res);
                //exception 발생후 메세지는 서버에서 발생
                a.insertAdjacentHTML("beforeend", `<div>${res.response.data.message}</div>`); //js로 dom 요소를 삽입
                this.notiModal.setBody(res.response.data.message);
                this.notiModal.open();
            });

    }

    modify(type,target) {   // 수정/삭제는 한번만 일어나겠죠? 그러니까 이것도 한번만 콜되야되는 함수.
        let seq = target.dataset.seq;
        axios.post(`/board/modify/${seq}`)
            .then((res) => {
                if(res.data.code === '0111') {
                    this.modifyModal.setBody('수정 완료');
                } else {
                    this.modifyModal.setBody('수정 실패');
                }
                this.modifyModal.open();

                if(type === 'suscess'){
                    target.classList.toggle('text-primary');
                    document.getElementById('viewText${seq}').classList.toggle('text-decoration-line-through');//클래스의 유무체크
                } else if (type === 'delete') {
                    document.getElementById('viewDiv${seq}').remove();
                }
            })
            .catch((res) =>{
                this.notiModal.open();
            });
        ;

    }

}

(() => {

    new View();

})();