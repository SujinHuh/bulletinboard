'use strict';

import TodoApi from '../todo/todoApi.js';

class Todo {

    constructor() {
        this.dom = {
            todoListDiv : document.getElementById('todoList'),
            todoListInput : document.getElementById('TodoListInput'),
            todoListInputBt : document.getElementById('TodoListInputBt')
        };


        let todoParam = { el : {
                listDiv : this.dom.todoListDiv,
                addInput : this.dom.todoListInput
            },
            callback : this.renderDiv
        };

        this.todoApi = new TodoApi(todoParam);

        this.init();
        this.eventBind();
    }

    init() {
        this.todoApi.getList();
    }

    eventBind() {
        this.dom.todoListInputBt.addEventListener('click', this.todoApi.addTodo);
    }

    renderDiv(data) {     // 렌더링
        let el = document.createElement('div');

        let div = '' +
            `<div class="d-flex align-items-center border-bottom py-2">`                                                                            +
                `<input class="form-check-input m-0" type="checkbox">`                                                                              +
                    `<div class="w-100 ms-3">`                                                                                                      +
                        `<div class="d-flex w-100 align-items-center justify-content-between">`                                                     +
                            `<${data.successYn === 'Y' ? 'del' : 'span'}>${data.text}<${data.successYn === 'Y' ? '/del' : '/span'}>`                +
                            `<button class="todoBt${data.seq} btn btn-sm ${data.successYn === 'Y' ? 'text-primary' : ''}" data-seq="${data.seq}">` +
                                `<i class="fa fa-times"></i></button>`                                                                              +
                        `</div>`                                                                                                                    +
                    `</div>`                                                                                                                        +
            `</div>`;

        el.innerHTML = div;

        return el;
    }

}

(() => {

    new Todo();

})();