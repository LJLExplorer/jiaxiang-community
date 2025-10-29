/*
* 用于给所有子页面最基本规范的接口
* */

interface IsSubPage {
    template: HTMLTemplateElement;

    render(): DocumentFragment | Promise<DocumentFragment>;

}

export default IsSubPage;
