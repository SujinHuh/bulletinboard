'use strict';

import axios from '/js/common/axios.js';

class List {

    constructor(type) {
        this.type = type;
        this.dom = {

        };

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

                for(let i = 0; i < list.length; i++){
                    a.append(`<td>${list[i].seq}</td>`);
                }

            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new List('free');

})();