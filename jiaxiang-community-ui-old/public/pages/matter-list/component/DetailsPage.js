import IsSubPage from "../../../common-component/multiple-pages/IsSubPage.js";
/*详情页面*/
class DetailsPage extends IsSubPage {
    matter;
    async render() {
        const documentFragmemt = document.createDocumentFragment();
        const titleSlot = document.createElement("span");
        const fileSlot = document.createElement("div");
        fileSlot.innerHTML = marked.parse(this.matter.file);
        titleSlot.innerText = this.matter.title;
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='title']").replaceWith(titleSlot);
        root.querySelector("slot[name='file']").replaceWith(fileSlot);
        return root;
    }
    constructor(matter, template) {
        super();
        this.matter = matter;
        this.template = template;
    }
}
export default DetailsPage;
