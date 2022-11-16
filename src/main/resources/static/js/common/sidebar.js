'use strict';

class Sidebar {

    constructor() {
        let path = location.pathname;

        if( path === '/member/login' || path === '/member/create'){
            console.log('sidebar 없음');
            return ;
        }

        this.dom = {
            content : document.querySelector ('.content'),
            sidebar : document.querySelector ('.sidebar'),
            sidebarToggler : document.querySelector ('.sidebar-toggler'),
        };

        this.eventBind();
    }
    //토글?
    eventBind() {
        this.dom.sidebarToggler.addEventListener('click', this.toggle.bind(this));
    }

    toggle() {
        this.dom.sidebar.classList.toggle('open');
        this.dom.content.classList.toggle('open');
    }

    // 함수 2개만들면되요
    // axios로 통신해서 메뉴가져오는거

    // 메뉴가저온걸로 rendering할부분

}

(() => {

    new Sidebar();

})();