/*详情页面*/
class DetailsPage {
    proof;
    template;
    async render() {
        const nameSlot = document.createElement("div");
        const contentSlot = document.createElement("div");
        nameSlot.classList.add("proof-name");
        contentSlot.classList.add("proof-content");
        nameSlot.innerText = this.proof.name;
        contentSlot.innerHTML = this.proof.content;
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='name']").replaceWith(nameSlot);
        root.querySelector("slot[name='content']").replaceWith(contentSlot);
        return root;
    }
    constructor(proof, template) {
        this.proof = proof;
        this.template = template;
    }
}
export default DetailsPage;
