/*
* 用于给所有子页面最基本规范的接口
* */

class IsSubPage {
    template: HTMLTemplateElement;

    render(): DocumentFragment | Promise<DocumentFragment> {
        return new DocumentFragment();
    }
}

export default IsSubPage;
