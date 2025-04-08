/*概要页面*/
class CommissionerList {
    list;
    event;
    currentPage;
    itemInAPage;
    totalPage;
    template;
    renderMemberBox(memberBox, member) {
        const img = document.createElement("img");
        img.src = member.image;
        const memberInfo = document.createElement("div");
        memberInfo.className = "member-info";
        const nameRole = document.createElement("div");
        nameRole.className = "member-name-role";
        nameRole.textContent = `${member.name} - ${member.role}`;
        const duty = document.createElement("div");
        duty.className = "member-duty";
        duty.textContent = member.duty;
        memberInfo.appendChild(nameRole);
        memberInfo.appendChild(duty);
        memberBox.appendChild(img);
        memberBox.appendChild(memberInfo);
        if (this.event.eventList.has(member)) {
            this.event.eventList.get(member).forEach((value) => {
                memberBox.addEventListener(value.type, value.listener);
            });
        }
    }
    render() {
        const documentFragmemt = document.createDocumentFragment();
        const membersContainer = document.createElement("div");
        const start = (this.currentPage - 1) * this.itemInAPage;
        const end = start + this.itemInAPage;
        const pageMembers = this.list.slice(start, end);
        for (let i = 0; i < pageMembers.length; i += 2) {
            const row = document.createElement("div");
            row.className = "members-row";
            const memberBox1 = document.createElement("div");
            memberBox1.className = "member-box";
            this.renderMemberBox(memberBox1, pageMembers[i]);
            row.appendChild(memberBox1);
            if (i + 1 < pageMembers.length) {
                const memberBox2 = document.createElement("div");
                memberBox2.className = "member-box";
                this.renderMemberBox(memberBox2, pageMembers[i + 1]);
                row.appendChild(memberBox2);
            }
            membersContainer.appendChild(row);
        }
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='list']").replaceWith(membersContainer);
        documentFragmemt.appendChild(root);
        return documentFragmemt;
    }
    constructor(commissionerList, template, itemInAPage, currentPage, event) {
        this.list = commissionerList;
        this.template = template;
        this.itemInAPage = itemInAPage;
        this.currentPage = currentPage;
        this.event = event;
        this.totalPage = Math.ceil(this.list.length / this.itemInAPage);
    }
}
export default CommissionerList;
