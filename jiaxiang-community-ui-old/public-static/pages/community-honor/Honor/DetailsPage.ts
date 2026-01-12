import HonorDAO from "./HonorDAO";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";

/*详情页面*/
class DetailsPage implements IsSubPage {
    public serve: HonorDAO;
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

        const root: HTMLElement = this.template.content.cloneNode(true) as HTMLElement;

        const imageSlot = document.createElement("img");
        imageSlot.setAttribute("slot", "image");
        imageSlot.src = this.serve.image;
        root.querySelector("slot[name='image']").replaceWith(imageSlot);

        const titleSlot = document.createElement("span");
        titleSlot.setAttribute("slot", "title");
        titleSlot.innerText = this.serve.title;
        root.querySelector("slot[name='title']").replaceWith(titleSlot);

        const phoneSlot = document.createElement("span");
        phoneSlot.setAttribute("slot", "phone");
        phoneSlot.innerText = this.serve.phone;
        root.querySelector("slot[name='phone']").replaceWith(phoneSlot);

        const timeSlot = document.createElement("span");
        timeSlot.setAttribute("slot", "time");
        timeSlot.innerText = this.serve.time;
        root.querySelector("slot[name='time']").replaceWith(timeSlot);

        const addressSlot = document.createElement("span");
        addressSlot.setAttribute("slot", "address");
        addressSlot.innerText = this.serve.address;
        root.querySelector("slot[name='address']").replaceWith(addressSlot);

        const descriptionSlot = document.createElement("span");
        descriptionSlot.setAttribute("slot", "description");
        descriptionSlot.innerText = this.serve.description;
        root.querySelector("slot[name='description']").replaceWith(descriptionSlot);

        const contentSlot = document.createElement("ul");
        contentSlot.setAttribute("slot", "content");
        contentSlot.innerHTML = `
            ${this.serve.content.map((content) => {
            return `<li>${content}</li>`;
        }).join("")}
        `;
        root.querySelector("slot[name='content']").replaceWith(contentSlot);

        const rulesSlot = document.createElement("ul");
        rulesSlot.setAttribute("slot", "rules");
        rulesSlot.innerHTML = `
            ${this.serve.rules.map((content) => {
            return `<li>${content}</li>`;
        }).join("")}
        `;
        root.querySelector("slot[name='rules']").replaceWith(rulesSlot);


        documentFragmemt.appendChild(root);
        return documentFragmemt;
    }

    constructor(serve: HonorDAO, template: HTMLTemplateElement) {
        this.serve = serve;
        this.template = template;
    }
}

export default DetailsPage;