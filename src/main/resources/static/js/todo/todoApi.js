'use strict';

import axios from '../common/axios.js';

class TodoApi {

    constructor(param) {
        // param {el : {
        //              listDiv : renderEL,
        //              addInput : inputEL
        //              },
        //      callback : callbackFunction}
        this.param = param;
        console.log('todo constructor!!');
    }

    getList() { // 가져오기
        axios.post('/todo/list')
            .then((res) => {
                let list = res.data.data;
                for(let i = 0; i < list.length; i++) {
                    let div = this.param.callback(list[i]);
                    this.param.el.listDiv.append(div);
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

    addTodo() {
        axios.post('/todo/add', {text : this.param.el.addInput.value})
            .then((res) => {
                console.log(res);
                if(res.data.code === '0000'){
                    alert('추가 완료.');
                    // 입력한 인풋 초기화
                    this.param.el.addInput.value = '';
                    // 동적으로 붙여줘야됨.
                    let div = this.param.callback(res.data.data);
                    this.param.el.listDiv.append(div);
                }else{
                    alert('추가 실패.');
                }
            })
            .catch((res) => {
                console.log(res);
            });
    }

}

export default TodoApi;