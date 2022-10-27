'use strict';

import axios from '../common/axios.js';

class Index {

    constructor() {
        this.dom = {
            todoListDiv : document.getElementById('todoList'),
            TodoListInput : document.getElementById('TodoListInput'),
            TodoListInputBt : document.getElementById('TodoListInputBt')
        };

        this.init();
        this.eventBind();
    }

    init() {
        this.getList();
    }

    getList() { // 가져오기
        let div;
        axios.post('/todolist/list')
            .then((res) => {
                let list = res.data.data;
                console.log(list);
                for(let i = 0; i < list.length; i++) {
                    div = this.makeDiv(list[i]);
                    this.dom.todoListDiv.append(div);
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

    eventBind() {
        this.dom.TodoListInputBt.addEventListener('click', this.addTodo.bind(this));
    }

    addTodo(e) {
        let param = {
            text : this.dom.TodoListInput.value
        };

        axios.post('/todolist/add', param)
            .then((res) => {
                console.log(res);
                if(res.data.code === '0000'){
                    alert('추가 완료.');

                    // 입력한 인풋 초기화
                    this.dom.TodoListInput.value = '';

                    // 동적으로 붙여줘야됨.
                    let div = this.makeDiv(res.data.data);
                    this.dom.todoListDiv.append(div);
                }else{
                    alert('추가 실패.');
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

    makeDiv(data) {     // 렌더링
        let el = document.createElement('div');

        let div = '' +
            `<div class="d-flex align-items-center border-bottom py-2">`                                                                            +
                `<input class="form-check-input m-0" type="checkbox">`                                                                              +
                    `<div class="w-100 ms-3">`                                                                                                      +
                        `<div class="d-flex w-100 align-items-center justify-content-between">`                                                     +
                            `<${data.successYn === 'Y' ? 'del' : 'span'}>${data.text}<${data.successYn === 'Y' ? '/del' : '/span'}>`                +
                            `<button class="todoBt+${data.seq} btn btn-sm ${data.successYn === 'Y' ? 'text-primary' : ''}" data-seq="${data.seq}">` +
                                `<i class="fa fa-times"></i></button>`                                                                              +
                        `</div>`                                                                                                                    +
                    `</div>`                                                                                                                        +
            `</div>`;

        el.innerHTML = div;

        return el;
    }

}

(() => {

    new Index();

})();