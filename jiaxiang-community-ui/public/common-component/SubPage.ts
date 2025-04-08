/*
* 用于给所有子页面最基本规范的接口
* */

interface SubPage {
    template: HTMLTemplateElement;

    render(): DocumentFragment;
}

export default SubPage;
