import CommonPagination from "../../../common-component/common-pagination.js";
// 菜单
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
// 多页面
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import DetailsPage from "../component/DetailsPage.js";
import SummaryPage from "../component/SummaryPage.js";
import { getDetails, getList, getPages } from "./api-request.js";
/*
*    public id: string;
*    public name: string;
*    public content: string;
* */
document.addEventListener("DOMContentLoaded", async function () {
    const pageContainer = document.querySelector("#page-container");
    const detailsTemplate = document.querySelector("#details-template");
    const summaryTemplate = document.querySelector("#summary-template");
    let itemInAPage = 6;
    let currentPage = 1;
    const registEvent = function (list) {
        const cEvent = new EventRegister();
        let cell;
        list.forEach((element, index) => {
            cEvent.regist("#" + element.id, "click", async (e) => {
                const serveInfos = await getDetails(element.id);
                const detailsFragment = new DetailsPage(serveInfos, detailsTemplate);
                pageContainer.show(detailsFragment);
                cell = new NavigatorCell("返回上一级", function (ev) {
                    pageContainer.show(new SummaryPage(list, summaryTemplate, itemInAPage, 1, cEvent, cInitializer));
                    navigatorLoader.removeCell(cell);
                    mainLoader.adapter.clear();
                    mainLoader.adapter.display();
                });
                navigatorLoader.appendCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });
        });
        return cEvent;
    };
    const cInitializer = new DataInitializer();
    const pagesInfo = await getPages(itemInAPage);
    cInitializer.regist(".content", function (ele) {
        const pagination = new CommonPagination(currentPage, pagesInfo.pages, pagesInfo.total, itemInAPage);
        ele.appendChild(pagination);
        const cb = async function () {
            currentPage = this.currentPage;
            const page = await getList(currentPage, itemInAPage);
            pageContainer.show(new SummaryPage(page, summaryTemplate, itemInAPage, 1, registEvent(page), cInitializer));
        };
        pagination.addEventListener("pre", cb);
        pagination.addEventListener("next", cb);
    });
    const firstPage = await getList(1, itemInAPage);
    pageContainer.show(new SummaryPage(firstPage, summaryTemplate, itemInAPage, 1, registEvent(firstPage), cInitializer));
});
