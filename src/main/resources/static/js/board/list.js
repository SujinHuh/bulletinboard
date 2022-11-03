'use strict';

import axios from '/js/common/axios.js';

class List {

    constructor(type) {
        this.type = type;
        this.dom = {

        };

        this.init();
    }

    init() {
        console.log('init!!');
        this.eventBind();
    }

    eventBind() {
        console.log('eventBind!!');
        this.getList();
    }

    getList() {
        axios.post('/freeboard/list')
            .then((res) => {
                console.log(res);
            })
            .catch((res) => {
                console.log(res);
            });
    }
}

(() => {

    new List('free');

})();