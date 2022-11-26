'use strict';

import TodoApi from '/js/todo/todoApi.js';

class Todo {

    constructor() {
        this.dom = {
            id: {
                todoList: document.getElementById('todoList'),
                todoInput: document.getElementById('TodoListInput'),
                todoInputBt: document.getElementById('TodoListInputBt')
            },
            class: {
                todoBt: document.getElementsByClassName('todoBt'),
            }
        };

        let todoParam = {
            el: {
                id: {
                    todoList: this.dom.id.todoList,
                    todoInput: this.dom.id.todoInput,
                    todoInputBt: this.dom.id.todoInputBt
                },
                class: {
                    todoBt: this.dom.class.todoBt,
                }
            },
            callback: {
                rendering: this.renderDiv
            }
        };

        this.todoApi = new TodoApi(todoParam);

        this.todoApi.getList();
    }



        renderDiv(data) {
        console.log(data)
        let div = '' +
            `<div class="d-flex align-items-center border-bottom py-2" id="todoDiv${data.seq}">`                                        +
                `<div class="w-100 ms-3">`                                                                                              +
                    `<div class="d-flex w-100 align-items-center justify-content-between">`                                             +
                        `<${data.successYn === 'Y' ? 'del' : 'span'} id="todoText${data.seq}">`                                         +
                            `${data.text}`                                                                                              +
                        `<${data.successYn === 'Y' ? '/del' : '/span'}>`                                                                +
                        `<button class="todoBt btn btn-sm ${data.successYn === 'Y' ? 'text-primary' : ''}" data-seq="${data.seq}">`     +
                            `<i class="fa fa-times"></i></button>`                                                                      +
                    `</div>`                                                                                                            +
                `</div>`                                                                                                                +
            `</div>`;
        return div;
    }

}

(() => {

    new Todo();

})();