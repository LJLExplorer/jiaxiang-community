/*概要页面*/
class SummaryPage {
    list;
    currentPage;
    itemInAPage;
    totalPage;
    template;
    event;
    initializer;
    renderMemberBox(memberBox, member) {
        memberBox.innerHTML = `
            <img src="${member.image}" alt="和睦嘉">
            
            <div class="service-info">
                <div class="service-title">${member.title}</div>
                <div class="service-detail">【联系电话】${member.phone}</div>
                <div class="service-detail">【开放时间】${member.time}</div>
                <div class="service-detail">【活动地址】${member.address}</div>
            </div>
        `;
    }
    render() {
        const documentFragmemt = document.createDocumentFragment();
        const membersContainer = document.createElement("div");
        membersContainer.id = "services-container";
        const start = (this.currentPage - 1) * this.itemInAPage;
        const end = start + this.itemInAPage;
        const pageMembers = this.list.slice(start, end);
        const boxPerRow = 2;
        for (let rowNum = 0; rowNum < Math.ceil(pageMembers.length / boxPerRow); rowNum += 1) {
            const row = document.createElement("div");
            row.className = "services-row";
            for (let colNum = 0; colNum < Math.min(boxPerRow, pageMembers.length - rowNum * boxPerRow); colNum++) {
                const count = rowNum * boxPerRow + colNum;
                const memberBox = document.createElement("div");
                memberBox.className = "service-box";
                memberBox.id = `${pageMembers[count].id}`;
                this.renderMemberBox(memberBox, pageMembers[count]);
                row.appendChild(memberBox);
            }
            membersContainer.appendChild(row);
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
