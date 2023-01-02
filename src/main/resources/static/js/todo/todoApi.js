'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class TodoApi {

    /**
     * Todo를 쓰기위한 class
     * 아래 형식으로 선언하면 자동으로 생성된다.
     *
     * param : { el : {
     *              id : {
     *                    todoList : listAreaEL,  -- todolist가 붙을 영역
     *                    todoInput : inputEL,    -- todo값을 입력받을 input
     *                    todoInputBt : inputBtEL -- todo입력 이벤트 발생 버튼
     *                  },
     *              class : {
     *                      todoBt : todoBt       -- todo버튼
     *                  }
     *              }
     *       callback : {
     *                  rendering : callbackFunction    -- 각 화면에 따른 HTML 렌더링 콜백
     *      }
     *  }
     */

    constructor(prop) {
        this.prop = prop;
        console.log('todo constructor!!');
        this.setModal();
        this.eventBind();
    }

    setModal() {
        let verifyParam = {
            title: 'TODO',
            body: '작업을 골라주세요..',
            callback: [{
                name : '완료',
                callback : (target) => this.modify('success', target)
            },{
                name : '삭제',
                callback : (target) => this.modify('delete', target)
            }]
        }
        this.verifyModal = new Modal(verifyParam);

        let notiParam = {
            title: 'TODO',
            body: '작업을 골라주세요..',
        }

        this.notiModal = new Modal(notiParam);
    }

    eventBind() {
        this.prop.el.id.todoInputBt.addEventListener('click', () => this.addTodo());
    }

    listEventBind() {
        // spread 연산자
        let btArray = [...this.prop.el.class.todoBt];
        // Array 객체
        // let btArray = Array.from(this.prop.el.class.todoBt);
        btArray.forEach((item, index) => {
            item.addEventListener('click', (e) => this.verifyModal.open(e) );
        });
    }

    getList() { // 가져오기
        axios.post('/todo/list')
            .then((res) => {
                let list = res.data.data;
                for (let i = 0; i < list.length; i++) {
                    this.prop.el.id.todoList.insertAdjacentHTML('afterbegin', this.prop.callback.rendering(list[i]));
                }
                this.listEventBind();
            })
            .catch((res) => {
                console.log(res);
            });
    }

    addTodo() {
        axios.post('/todo/add', {text: this.prop.el.id.todoInput.value})
            .then((res) => {
                if (res.data.code === '0000') {
                    this.notiModal.setBody('추가 완료');
                    // 입력한 인풋 초기화
                    this.prop.el.id.todoInput.value = '';
                    // 동적으로 붙여줘야됨.
                    this.prop.el.id.todoList.insertAdjacentHTML('beforeend', this.prop.callback.rendering(res.data.data));
                    // 동적이벤트 바인딩
                    let length = this.prop.el.class.todoBt.length - 1;
                    this.prop.el.class.todoBt[length].addEventListener('click', (e) => this.verifyModal.open(e) );
                } else {
                    this.notiModal.setBody('추가 실패');
                }
                this.notiModal.open();
            })
            .catch((res) => {
                console.log(res);
            });
    }

    modify(type, target) {
        // target은 버튼 객체로 따로 ID를 지정안해도 된다.
        let seq = target.dataset.seq;
        console.log("todo modify 진입");
        axios.post(`/todo/modify/${type}/${seq}`)
            .then((res) => {
                if (res.data.code === '0000') {
                    this.notiModal.setBody('수정 완료.');

                    if(type === 'success'){
                        target.classList.toggle('text-primary')
                        document.getElementById(`todoText${seq}`).classList.toggle('text-decoration-line-through');
                    }else if(type === 'delete'){
                        document.getElementById(`todoDiv${seq}`).remove();
                    }
                } else {
                    this.notiModal.setBody('수정 실패.');
                }

                this.notiModal.open();
            })
            .catch((res) => {
                this.notiModal.setBody(res.response.data.message);
                this.notiModal.open();
            });
    }

}

export default TodoApi;