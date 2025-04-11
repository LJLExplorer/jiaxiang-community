import ServeDAO from "./serveDAO";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";

/*详情页面*/
class DetailsPage implements IsSubPage {
    public commissioner: ServeDAO;
    public template: HTMLTemplateElement;

    /*
    public name: string;
    public role: string;
    public duty: string;
    public image: string;
    public dutyContent: string[];
    */
    public render() {
        const documentFragmemt = document.createDocumentFragment();

        const nameSlot = document.createElement("span");
        const roleSlot = document.createElement("span");
        const dutySlot = document.createElement("span");
        const imageSlot = document.createElement("img");
        const dutyContentSlot = document.createElement("div");

        nameSlot.setAttribute("slot", "name");
        roleSlot.setAttribute("slot", "role");
        dutySlot.setAttribute("slot", "duty");
        imageSlot.setAttribute("slot", "image");
        dutyContentSlot.setAttribute("slot", "duty-content");

        nameSlot.innerText = this.commissioner.name;
        roleSlot.innerText = this.commissioner.role;
        dutySlot.innerText = this.commissioner.duty;
        imageSlot.src = this.commissioner.image;
        for (let i = 0; i < this.commissioner.dutyContent.length; i++) {
            const p = document.createElement("p");
            p.innerText = this.commissioner.dutyContent[i];
            dutyContentSlot.appendChild(p);
        }

        const root: HTMLElement = this.template.content.cloneNode(true)as HTMLElement;
        root.querySelector("slot[name='image']").replaceWith(imageSlot);
        root.querySelector("slot[name='name']").replaceWith(nameSlot);
        root.querySelector("slot[name='role']").replaceWith(roleSlot);
        root.querySelector("slot[name='duty']").replaceWith(dutySlot);
        root.querySelector("slot[name='duty-content']").replaceWith(dutyContentSlot);

        documentFragmemt.appendChild(root);
        return documentFragmemt;
    }

    constructor(commissioner: ServeDAO, template: HTMLTemplateElement) {
        this.commissioner = commissioner;
        this.template = template;
    }
}

export default DetailsPage;