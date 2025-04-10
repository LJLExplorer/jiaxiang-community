import MarkdownDriver from "../../../common-component/markdown-driver.js";
/*详情页面*/
class DetailsPage {
    matter;
    template;
    async render() {
        const documentFragmemt = document.createDocumentFragment();
        const titleSlot = document.createElement("span");
        const fileSlot = document.createElement("div");
        /*
                titleSlot.setAttribute("slot", "title");
                fileSlot.setAttribute("slot", "file");*/
        try {
            let fileResponse = await fetch(this.matter.file);
            const fileContent = await fileResponse.text();
            fileSlot.innerHTML = MarkdownDriver.markdownToHtml(fileContent);
        }
        catch (error) {
            fileSlot.innerHTML = `加载失败，失败原因：${error.message}`;
        }
        titleSlot.innerText = this.matter.title;
        const root = this.template.content.cloneNode(true);
        root.querySelector("slot[name='title']").replaceWith(titleSlot);
        root.querySelector("slot[name='file']").replaceWith(fileSlot);
        return root;
    }
    constructor(matter, template) {
        this.matter = matter;
        this.template = template;
    }
}
export default DetailsPage;
