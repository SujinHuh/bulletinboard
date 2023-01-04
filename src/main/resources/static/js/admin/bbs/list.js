'use strict';

import axios from '/js/common/axios.js';
import Modal from '/js/common/modal.js';

class List {
    constructor() {

        console.log("list constructor")
        this.getList();
    }


    getList() {
        axios.post('/admin/bbs/list', {page : 1, type : '', search : ''})
            .then((res) => {
               console.log(res);
            });
    }

}

(() => {

    new List();

})();