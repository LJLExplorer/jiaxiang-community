class PageContainer extends HTMLElement {
    clear() {
        this.innerHTML = "";
    }
    async show(page) {
        this.clear();
        const documentFragment = await page.render();
        const that = this;
        await new Promise(resolve => {
            document.startViewTransition(() => {
                that.appendChild(documentFragment);
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
