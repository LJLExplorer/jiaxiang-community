import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";

class PageContainer extends HTMLElement {
    public clear() {
        this.innerHTML = "";
    }

    public async show(page: IsSubPage) {
        this.clear();

        const documentFragment: DocumentFragment = page.render();


        await new Promise<void>(resolve => {
            document.startViewTransition(() => {
                this.appendChild(documentFragment);
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