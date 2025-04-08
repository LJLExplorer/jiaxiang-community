class PageContainer extends HTMLElement {
    clear() {
        this.innerHTML = "";
    }
    async show(page) {
        this.clear();
        const documentFragment = page.render();
        await new Promise(resolve => {
            document.startViewTransition(() => {
                this.appendChild(documentFragment);
                resolve();
            });
        });
        return this;
    }
    constructor() {
        super();
    }
}
customElements.define('page-container', PageContainer);
export default PageContainer;
