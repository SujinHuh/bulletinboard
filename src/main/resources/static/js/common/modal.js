"use strict";

class Modal {
    /**
     * Modal을 쓰기위한 class
     * 아래 형식으로 선언하면 자동으로 생성된다.
     *
     * param : {
     *      title : title, -- Modal Title
     *      body : body, -- Modal Body
     *      callback : [
     *          {
     *              name : name -- 버튼이름
     *              callback : function -- 실행 콜백
     *          }
     *      ]   -- Array 형태
     *  }
     */
    constructor(prop) {
        this.modalId = this.getUUID();

        let title = prop.title;
        let body = prop.body;
        let callback = prop.callback || [{}];

        this.dom = {
            body: document.getElementById('body'),
        }

        this.el = `<div id="${this.modalId}" class="modal" tabindex="-1" style="top:30%;">`                                     +
                    `<div class="modal-dialog">`                                                                                +
                        `<div class="modal-content">`                                                                           +
                            `<div class="modal-header">`                                                                        +
                                `<div class="modal-title">${title}</div>`                                                       +
                                    `<button type="button" class="btn-close" id="${this.modalId}CloseBt1"></button>`            +
                                `</div>`                                                                                        +
                            `<div class="modal-body" id="${this.modalId}Body">`                                                 +
                                `${body}`                                                                                       +
                            `</div>`                                                                                            +
                            `<div class="modal-footer" id="${this.modalId}Footer">`                                             +
                                `<button type="button" class="btn btn-secondary" id="${this.modalId}CloseBt2">Close</button>`   +
                            `</div>`                                                                                            +
                            `</div>`                                                                                            +
                        `</div>`                                                                                                +
                    `</div>`;

        console.log('Modal constructor');

        // body 부분에 동적으로 dom 생성
        this.dom.body.insertAdjacentHTML('beforeend', this.el);

        // dom 생성 후 캐싱처리
        this.dom.modal = document.getElementById(this.modalId);
        this.dom.modalBody = document.getElementById(this.modalId + 'Body');
        this.dom.modalFooter = document.getElementById(this.modalId + 'Footer');
        this.dom.modalCloseBt1 = document.getElementById(this.modalId + 'CloseBt1');
        this.dom.modalCloseBt2 = document.getElementById(this.modalId + 'CloseBt2');

        // dom event bind
        this.dom.modalCloseBt1.addEventListener('click', () => this.close(this.getUUID()));
        this.dom.modalCloseBt2.addEventListener('click', () => this.close(this.getUUID()));

        callback.forEach((item, index) => {
            if(item.name === undefined && item.callback === undefined){
                return;
            }
            let id = this.modalId + 'Callback' + index;
            let bt = `<button type="button" class="btn btn-secondary" id="${id}">${item.name}</button>`;
            this.dom.modalFooter.insertAdjacentHTML('afterbegin', bt);
            document.getElementById(id).addEventListener('click', () => {
                item.callback;
                this.close(this.getUUID());
            });
        })

    }

    setBody(body) {
        this.dom.modalBody.innerHTML = body;
    }
    getUUID() {
        return this.modalId || self.crypto.randomUUID();
    }

    open(id) {
        document.getElementById('body').className = 'modal-open';
        document.getElementById(id).style.display = 'block';
    }

    close(id) {
        document.getElementById('body').className = '';
        document.getElementById(id).style.display = 'none';
    }
}

export default Modal;