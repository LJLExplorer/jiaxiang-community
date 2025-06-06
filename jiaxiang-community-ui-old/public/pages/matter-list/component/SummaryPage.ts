import MattersDAO from "./MattersDAO.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";
import CanRegistryEvent from "../../../common-component/multiple-pages/CanRegistryEvent.js";
import CanInitData from "../../../common-component/multiple-pages/CanInitData.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";

/*概要页面*/
class SummaryPage extends CanInitData {
    public list: MattersDAO[];
    public currentPage: number;
    public itemInAPage: number;
    public totalPage: number;

    public render() {

        const documentFragmemt = document.createDocumentFragment();

        const container = document.createElement("div");
        const start = (this.currentPage - 1) * this.itemInAPage;
        const end = start + this.itemInAPage;

        this.list.slice(start, end).forEach((matter, index) => {
            const button = document.createElement("button");
            button.id = matter.id;
            button.className = `big-btn`;
            button.innerHTML = `
                    ${matter.title}
                    <span>点击进入</span>
                `;
            container.appendChild(button);
        });

        const root = this.template.content.cloneNode(true) as DocumentFragment;
        root.querySelector("slot[name='list']").replaceWith(container);

        this.registryEvent(root);
        this.operate(root);

        documentFragmemt.appendChild(root);

        return documentFragmemt;
    }

    constructor(commissionerList: MattersDAO[],
                template: HTMLTemplateElement,
                itemInAPage: number,
                currentPage: number,
                event: EventRegister,
                dataInitializer: DataInitializer,) {
        super();

        this.list = commissionerList;
        this.template = template;

        this.itemInAPage = itemInAPage;
        this.currentPage = currentPage;
        this.event = event;
        this.initializer = dataInitializer;

        this.totalPage = Math.ceil(this.list.length / this.itemInAPage);

    }


}

export default SummaryPage;