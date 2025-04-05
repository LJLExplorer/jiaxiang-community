import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import itemProfile from "../../../common-component/navigator-regist/item-profile.js";
import itemGridManagement from "../../../common-component/navigator-regist/item-grid-management.js";
import itemCommissioner from "../../../common-component/navigator-regist/item-commissioner.js";
import itemServePeople from "../../../common-component/navigator-regist/item-serve-people.js";
import itemMatterList from "../../../common-component/navigator-regist/item-matter-list.js";
import itemProof from "../../../common-component/navigator-regist/item-proof.js";
import NavigatorAdapterIndex from "./navigator-adapter-index.js";
//用于让浏览器加载完成后执行
// @ts-ignore
(mainLoader, itemProfile, itemGridManagement, itemCommissioner, itemServePeople, itemMatterList, itemProof);
document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("navigator-container");
    mainLoader.adapter = new NavigatorAdapterIndex(container, mainLoader);
    mainLoader.adapter.display();
});
