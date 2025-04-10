import MattersDAO from "./MattersDAO.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";
import CanRegistryEvent from "../../../common-component/multiple-pages/CanRegistryEvent.js";
import CanInitData from "../../../common-component/multiple-pages/CanInitData.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";

/*概要页面*/
class SummaryPage implements IsSubPage, CanRegistryEvent, CanInitData {
    public list: MattersDAO[];
    public currentPage: number;
    public itemInAPage: number;
    public totalPage: number;

    public template: HTMLTemplateElement;
    public event: EventRegister;
    public initializer: DataInitializer;


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

        const root: HTMLElement = this.template.content.cloneNode(true) as HTMLElement;
        root.querySelector("slot[name='list']").replaceWith(container);

        this.registryEvent(root);
        this.operate(root);

        documentFragmemt.appendChild(root);

        return documentFragmemt;
    }

    public registryEvent(ele: HTMLElement) {
        this.event.eventList.forEach((eventInfos, selector) => {
            const selectorEle = ele.querySelector(selector);
            if (selectorEle) {
                eventInfos.forEach((eventInfo) => {
                    selectorEle.addEventListener(eventInfo.type, eventInfo.listener);
                })
            }
        })
    }

    public operate(ele: HTMLElement): void {
        this.initializer.list.forEach((callbacks, selector) => {
            const target = ele.querySelector(selector);
            if (target) {
                callbacks.forEach(callback => {
                    callback.call(ele, target);
                })
            }
        })
    }

    constructor(commissionerList: MattersDAO[],
                template: HTMLTemplateElement,
                itemInAPage: number,
                currentPage: number,
                event: EventRegister,
                dataInitializer: DataInitializer,) {
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