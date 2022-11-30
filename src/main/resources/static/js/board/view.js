'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class View {

    constructor() {
        console.log(location.pathname); // 현재 페이지의 경로를 반환
        let array = location.pathname.split('/');

        let seq = array[array.length - 1]; // BoardAction-view() 1. 해당 페이지로 들어와서 url에있는 seq를 parsing을 한다음 getView 함수에 전달

        let contentNull = { //modal
            title : '게시판',
            body : '게시글 없음',
        }
        let modifyModal = { // modifyModal
            title: '수정',
            body : '수정하시겠습니까?',
            callback : [
                {
                    name : '수정',
                    //callback :
                }
            ]
        }
        this.notiModal = new Modal(contentNull);
        this.getView(seq);
    }

    setModal(){
        let contentNull = { //modal
            title : '게시판',
            body : '게시글 없음',
        }
        let modifyModal = { // modifyModal
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
        this.notiModal = new Modal(contentNull);
        this.notiModal = new Modal(modifyModal);
    }

    getView(seq) {
        let a = document.getElementById('contentArea'); //해당 tag에 접근
        //  BoardAction-view() 2. axios를 통해  method post로 아래 URL로 게시글번호를 전달한 상황입니다.
        axios.post('/board/view/' + seq)
            .then((res) => {
                let view = res.data.data;
                console.log(res)    // 이건 진짜 web response html status 같은거 포함.
                console.log(res.data);  // java에서 내려준 responseVo
                // res.data 가 저렇게 json으로 찍힘 data가 null이면
                console.log(res.data.data); // responseVo에 set한 data
                if(view.myBbs == true){
                    let btn = '<button>수정</button>';
                    a.addEventListener("click", (e) => this.modifyModal.open(e));
                }
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

    modify(type,target) {
        let seq = target.dataset.seq;
        axios.post('/board/view/${type}/${seq}')
            .then((res) => {
                if(res.data.code === '0000') {
                    this.modifyModal.setBody('수정 완료');
                } else {
                    this.modifyModal.setBody('수정 실패');
                }
                this.modifyModal.open();

                if(type === 'suscess'){
                    target.classList.toggle('text-primary');
                    document.getElementById('viewText${seq}').classList.toggle('');//클래스의 유무체크
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