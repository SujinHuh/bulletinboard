'use strict';

class Sidebar {

    constructor() {
        let path = location.pathname;

        if( path === '/member/login' || path === '/member/create'){
            console.log('sidebar 없음');
            return ;
        }

        this.dom = {
            'content' : document.querySelector ('.content'),
            'sidebar' : document.querySelector ('.sidebar'),
            'sidebarToggler' : document.querySelector ('.sidebar-toggler'),
        };

        this.eventBind();
    }

    eventBind() {
        this.dom.sidebarToggler.addEventListener('click', this.toggle.bind(this));
    }

    toggle() {
        this.dom.sidebar.classList.toggle('open');
        this.dom.content.classList.toggle('open');
    }

}

(() => {

    new Sidebar();

})();