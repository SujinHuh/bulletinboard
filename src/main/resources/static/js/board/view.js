'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class View {

    constructor() {
        this.dom = {
            commentList : document.getElementById('commentList')
            ,commentText : document.getElementById('commentText')
            , commentAddBt : document.getElementById('commentAddBt')
        };

        console.log(location.pathname); // 현재 페이지의 경로를 반환
        let array = location.pathname.split('/');

        this.seq = array[array.length - 1]; // BoardAction-view() 1. 해당 페이지로 들어와서 url에있는 seq를 parsing을 한다음 getView 함수에 전달

        this.eventBind();
        // 1.  modal을 쓰기에 가장먼저 로딩이되야된다.
        this.setModal();
        this.deleteModal();
        // 2. 게시글을 가져와야 이벤트를 바인딩할수있다.
        this.getView();
        this.getComment();

        // this.setDeleteContent(seq); //-> 여기서 실행을 시키지 않으면
        // 3. 이벤트를 바인딩 할 태그 + 모달을 띄어야되니까 가장마지막에 이벤트를 바인딩한다.
    }

    eventBind() {
        this.dom.commentAddBt.addEventListener('click', () => this.addComment());
    }

    setModal(){  // 한번만 콜되야되는 함수
        console.log("setModal");
        let modifyModal = {
            title: '수정',
            body : '수정하시겠습니까?',
            callback : [
                {
                    name : '수정',
                    // callback : (target) => {alert("이 버튼은 수정페이지로 이동해야됩니다.")}
                    callback : () => location.href = `/board/modify/${this.seq}`
                },{
                    name: '삭제',
                    callback : () => this.deleteCheckModal.open()
                    // callback : () => alert("삭제 모달창으로 이동") //this.deleteModal.opne()
                }
            ]
        }

        this.modifyModal = new Modal(modifyModal);

        let notiParam = {
            title : '게시판',
            body : '',
        }
        this.notiModal = new Modal(notiParam);
    }

    //삭제 확인하는 modal창
    deleteModal() {
        console.log("deleteModal 진입");
        let deleteCheckModal = {
            title : '삭제',
            body : '정말 삭제하시겠습니까?',
            callback : [
                {
                    name : '삭제',
                    callback : () => this.setDeleteContent()
                    // callback : () => location.href = `/board/delete`
                }
            ]
        }
        this.deleteCheckModal = new Modal(deleteCheckModal);
    }

    setDeleteContent(){ //질문
        console.log("contentDelete 진입")
        //비동기통신 /http통신 deleteYn을 n으로 수정
        axios.post(`/board/delete/${this.seq}`)//url 맵핑
        // axios.post(`/board/delete/`, {seq : seq}) //requestParam 맵핑 /{json객체로 던지는}
            .then((res) => {
                let view = res.data.data;
                console.log(res)
                console.log(res.data);
                console.log(res.data.data);
                // 여기서 작업이 발생해야지
                // 삭제가처리되었다는 정보 or 페이지이동

                location.href = `/board/list`;

                //res.data.data.deleteN = false;
            })
    }

    addComment() {
        console.log("addComment>>> 진입")
        axios.post('/comment/list/add', {bbsSeq : this.seq ,text: this.dom.commentText.value}) //등록을 하게되면 textArea input만 있으면된다. seq필요없고 input 도큐먼트의 value값을 찾으면된다.
            .then((res) => {
                console.log(res);
                console.log(res.data);
                console.log(res.data.data);
                if (res.data.code === '0000') {
                    alert('댓글 추가 완료');
                    //비동기로 -> 단일객체로
                    this.dom.commentText.value = ''; //입력 인풋 초기화
                    let div = this.render(res.data.data);
                    this.dom.commentList.insertAdjacentHTML('beforeend', div);
                } else {
                    alert('댓글 추가 실패');
                }
            })
            .catch((res) => {
                console.log(res);
            })
    }

    render(data) { //list구현하는 함수
        let div = ` <tr>` +
            `<td>${data.seq}</td>`+
            `<td class="card-header" data-seq="${data.seq}" > ${data.text}</a></td>` +
            `<td>${data.createDate == undefined ? '' : data.createDate}</td>`+
            `</tr> <p>`;
        return div;
    }

    getComment() { //coment list로 가져오기
        console.log("getComment>>> 진입")
        axios.post('/comment/list/'+ this.seq)
            .then((res) => {
                let list = res.data.data;
                console.log(res);
                console.log(res.data);
                console.log(res.data.data);
                for(let i = 0; i < list.length; i++) {
                    let div = this.render(list[i]);
                    this.dom.commentList.insertAdjacentHTML("beforeend", div) // javascript dom요소 삽입
                }
                let array = [...document.getElementById('card-header')];
                array.forEach((value, index) => {
                    location.href = '/comment/list/' + this.dom.commentList.currentTarget.getAttribute('seq')
                })
            })
            .catch((res) => {
                console.log(res);
            });
    }

    //비동기 -> 동기
    getView() {  // 마찬가지로 게시글을 가져오는 함수이기에 한번만 콜되야된다.
        let area = document.getElementById('contentArea'); //해당 tag에 접근
        //  BoardAction-view() 2. axios를 통해  method post로 아래 URL로 게시글번호를 전달한 상황입니다.
        axios.post('/board/view/' + this.seq)
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
                    '</tr>'

                ;
                if(this.myBbs){
                    div += '<button type="button" class="btn btn-primary ms-2" id="modifyBt" name="modifyBt">Modify</button>';
                }
                area.insertAdjacentHTML("beforeend", div); //js로 dom 요소를 삽입
                this.dom.modifyBt = document.getElementById('modifyBt');
                this.dom.modifyBt.addEventListener('click', () => this.modifyModal.open());

            })
            .catch((res) => {
                console.log(res);
                //exception 발생후 메세지는 서버에서 발생
                area.insertAdjacentHTML("beforeend", `<div>${res.response.data.message}</div>`); //js로 dom 요소를 삽입
                this.notiModal.setBody(res.response.data.message);
                this.notiModal.open();
            });

    }



}

(() => {

    new View();

})();