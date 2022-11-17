'use strict';

import axios from '/js/common/axios.js';

class List {

    constructor(type) {
        this.type = type;
        this.dom = {};

        this.init();
        this.eventBind();
    }

    init() {
        console.log('init!!');
        this.getList();
    }

    eventBind() {
        console.log('eventBind!!');
    }

    getList() {
        let a = document.getElementById('listArea');
        axios.post('/freeboard/list')
            .then((res) => {
                let list = res.data.data;
                console.log(res);
                console.log(res.data);
                console.log(res.data.data);
                console.log(res.data.data[0]);
                for (let i = 0; i < list.length; i++) {
                    let div = `<tr>`+
                                    `<td>${list[i].seq}</td>`+
                                    `<td><a href="/board/view/${list[i].seq}">${list[i].title}</a></td>`+
                                    `<td class="title_td" data-seq="${list[i].seq}">${list[i].title}</a></td>`+
                                    `<td>${list[i].name}</td>`+
                                    `<td>${list[i].createDate}</td>`+
                                    `<td>${list[i].cnt}</td>`+
                                `</tr>`;
                    a.insertAdjacentHTML("beforeend", div);
                }

                let array = [...document.getElementsByClassName('title_td')];
                array.forEach((value, index) => {
                    value.addEventListener('click', (e) => {
                        location.href = '/board/view/' + e.currentTarget.getAttribute('data-seq')
                    })
                })
            })
            .catch((res) => {
                console.log(res);
            });

    }
}

(() => {

    new List('free');

})();