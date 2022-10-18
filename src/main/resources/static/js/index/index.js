'use strict';

import axios from '../common/axios.js';

class Index {

    constructor() {
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
    }

}

(() => {

    new Index();

})();