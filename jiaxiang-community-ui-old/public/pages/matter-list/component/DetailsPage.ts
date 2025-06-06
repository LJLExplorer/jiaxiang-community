import MattersDAO from "./MattersDAO.js";
import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";

// md文件格式转换驱动，全局变量
declare const marked: any;

/*详情页面*/
class DetailsPage extends IsSubPage {
    public matter: MattersDAO;

    public async render() {
        const documentFragmemt = document.createDocumentFragment();

        const titleSlot = document.createElement("span");
        const fileSlot = document.createElement("div");

        fileSlot.innerHTML = marked.parse(this.matter.file);
        titleSlot.innerText = this.matter.title;

        const root: DocumentFragment = this.template.content.cloneNode(true) as DocumentFragment;

        root.querySelector("slot[name='title']").replaceWith(titleSlot);
        root.querySelector("slot[name='file']").replaceWith(fileSlot);

        return root;
    }

    constructor(matter: MattersDAO, template: HTMLTemplateElement) {
        super();

        this.matter = matter;
        this.template = template;
    }
}

export default DetailsPage;