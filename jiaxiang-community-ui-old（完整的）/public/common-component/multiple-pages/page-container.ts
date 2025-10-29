import IsSubPage from "./IsSubPage";

class PageContainer extends HTMLElement {
    public clear() {
        this.innerHTML = "";
    }

    public async show(page: IsSubPage) {
        this.clear();

        const documentFragment: DocumentFragment = await page.render();
        const that = this;

        await new Promise<void>(resolve => {
            document.startViewTransition(() => {
                that.appendChild(documentFragment);
                resolve();
            })
        })

        return this;
    }

    constructor() {
        super();
    }
}

customElements.define('page-container', PageContainer)

export default PageContainer;