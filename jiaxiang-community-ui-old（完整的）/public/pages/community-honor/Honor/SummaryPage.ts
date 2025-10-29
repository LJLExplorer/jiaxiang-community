import HonorDAO from "./HonorDAO";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import CanInitData from "../../../common-component/multiple-pages/CanInitData.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer";

/*概要页面*/
class SummaryPage extends CanInitData {
    public list: HonorDAO[];
    public currentPage: number;
    public itemInAPage: number;
    public totalPage: number;

    public render() {

        const documentFragmemt = document.createDocumentFragment();

        const membersContainer = document.createElement("ul");
        membersContainer.id = "honor-list";

        const start = (this.currentPage - 1) * this.itemInAPage;
        const end = start + this.itemInAPage;
        const pageMembers = this.list.slice(start, end);

        for (let i = 0; i < pageMembers.length; i++) {
            const member = pageMembers[i];
            const memberBox = document.createElement("li");
            memberBox.innerText = member.content;
            memberBox.id = member.id;
            membersContainer.appendChild(memberBox);
        }

        const root = this.template.content.cloneNode(true) as DocumentFragment;
        root.querySelector("slot[name='list']").replaceWith(membersContainer);

        this.registryEvent(root);
        this.operate(root);

        documentFragmemt.appendChild(root);

        return documentFragmemt;
    }

    constructor(commissionerList: HonorDAO[],
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