import MattersDAO from "./MattersDAO.js";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";
import MarkdownDriver from "../../../driver/markdown-driver.js";

/*详情页面*/
class DetailsPage implements IsSubPage {
    public matter: MattersDAO;
    public template: HTMLTemplateElement;


    public async render() {
        const documentFragmemt = document.createDocumentFragment();

        const titleSlot = document.createElement("span");
        const fileSlot = document.createElement("div");
/*
        titleSlot.setAttribute("slot", "title");
        fileSlot.setAttribute("slot", "file");*/

        try {
            let fileResponse = await fetch(this.matter.file)
            const fileContent = await fileResponse.text();
            fileSlot.innerHTML = MarkdownDriver.markdownToHtml(fileContent);
        } catch (error) {
            fileSlot.innerHTML = `加载失败，失败原因：${error.message}`;
        }

        titleSlot.innerText = this.matter.title;

        const root: DocumentFragment = this.template.content.cloneNode(true) as DocumentFragment;

        root.querySelector("slot[name='title']").replaceWith(titleSlot);
        root.querySelector("slot[name='file']").replaceWith(fileSlot);

        return root;
    }

    constructor(matter: MattersDAO, template: HTMLTemplateElement) {
        this.matter = matter;
        this.template = template;
    }
}

export default DetailsPage;