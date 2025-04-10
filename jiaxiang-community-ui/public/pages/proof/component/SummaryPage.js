/*概要页面*/
class SummaryPage {
    list;
    currentPage;
    itemInAPage;
    totalPage;
    template;
    event;
    initializer;
    renderProofBox(proofBox, proof) {
        const proofName = document.createElement("div");
        proofName.className = "proof-name";
        proofName.textContent = proof.name;
        const proofBtn = document.createElement("button");
        proofBtn.className = "proof-btn";
        proofBtn.textContent = "点击进入";
        proofBtn.id = proof.id;
        proofBox.appendChild(proofName);
        proofBox.appendChild(proofBtn);
        // 添加点击事件，跳转到证明详细信息页面，并传递当前页码
        /*proofBox.addEventListener("click", function() {
            window.location.href = `proof_detail.html?name=${proof.name}&page=${currentPage}`;
        });*/
    }
    render() {
        const start = (this.currentPage - 1) * this.itemInAPage;
        const end = start + this.itemInAPage;
        const pageProofs = this.list.slice(start, end);
        const container = document.createElement("div");
        for (let i = 0; i < pageProofs.length; i += 3) {
            const row = document.createElement("div");
            row.className = "proofs-row";
            for (let j = 0; j < 3 && i + j < pageProofs.length; j++) {
                const proofBox = document.createElement("div");
                proofBox.className = "proof-box";
                this.renderProofBox(proofBox, pageProofs[i + j]);
                row.appendChild(proofBox);
            }
            container.appendChild(row);
        }
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='list']").replaceWith(container);
        this.registryEvent(root);
        this.operate(root);
        return root;
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
    constructor(list, template, itemInAPage, currentPage, event, dataInitializer) {
        this.list = list;
        this.template = template;
        this.itemInAPage = itemInAPage;
        this.currentPage = currentPage;
        this.event = event;
        this.initializer = dataInitializer;
        this.totalPage = Math.ceil(this.list.length / this.itemInAPage);
    }
}
export default SummaryPage;
