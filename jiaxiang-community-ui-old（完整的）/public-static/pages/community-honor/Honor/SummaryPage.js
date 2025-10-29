/*概要页面*/
class SummaryPage {
    list;
    currentPage;
    itemInAPage;
    totalPage;
    template;
    event;
    initializer;
    render() {
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
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='list']").replaceWith(membersContainer);
        this.registryEvent(root);
        this.operate(root);
        documentFragmemt.appendChild(root);
        return documentFragmemt;
    }
    registryEvent(ele) {
        this.event.eventList.forEach((eventInfos, selector) => {
            const selectorEle = ele.querySelector(selector);
            if (selectorEle) {
                eventInfos.forEach((eventInfo) => {
                    selectorEle.addEventListener(eventInfo.type, eventInfo.listener);
                });
            }
        });
    }
    operate(ele) {
        this.initializer.list.forEach((callbacks, selector) => {
            const target = ele.querySelector(selector);
            if (target) {
                callbacks.forEach(callback => {
                    callback.call(ele, target);
                });
            }
        });
    }
    constructor(commissionerList, template, itemInAPage, currentPage, event, dataInitializer) {
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
