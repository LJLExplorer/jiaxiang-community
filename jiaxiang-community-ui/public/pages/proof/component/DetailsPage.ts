import ProofsDAO from "./ProofsDAO";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";
import MarkdownDriver from "../../../driver/markdown-driver";

/*详情页面*/
class DetailsPage implements IsSubPage {
    public proof: ProofsDAO;
    public template: HTMLTemplateElement;


    public async render() {
        const nameSlot = document.createElement("div");
        const contentSlot = document.createElement("div");

        nameSlot.classList.add("proof-name");
        contentSlot.classList.add("proof-content");

        nameSlot.innerText = this.proof.name;
        contentSlot.innerHTML = this.proof.content;

        const root: DocumentFragment = this.template.content.cloneNode(true) as DocumentFragment;
        root.querySelector("slot[name='name']").replaceWith(nameSlot);
        root.querySelector("slot[name='content']").replaceWith(contentSlot);

        return root;
    }

    constructor(proof: ProofsDAO, template: HTMLTemplateElement) {
        this.proof = proof;
        this.template = template;
    }
}

export default DetailsPage;