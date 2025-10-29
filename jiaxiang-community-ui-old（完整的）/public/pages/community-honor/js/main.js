import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import SummaryPage from "../Honor/SummaryPage.js";
import CommonPagination from "../../../common-component/common-pagination.js";
import { getPages, getList } from "./api-request.js";
document.addEventListener("DOMContentLoaded", async function () {
    const pageContainer = document.querySelector("#page-container");
    const summaryTemplate = document.querySelector("#summary-template");
    let itemInAPage = 6;
    let currentPage = 1;
    /*
        id: string;
    content: string;
    */
    const cInitializer = new DataInitializer();
    const pagesInfo = await getPages(itemInAPage);
    cInitializer.regist(".content", function (ele) {
        const pagination = new CommonPagination(currentPage, pagesInfo.pages, pagesInfo.total, itemInAPage);
        ele.appendChild(pagination);
        const cb = async function () {
            currentPage = this.currentPage;
            const page = await getList(currentPage, itemInAPage);
            pageContainer.show(new SummaryPage(page, summaryTemplate, itemInAPage, 1, null, cInitializer));
        };
        pagination.addEventListener("pre", cb);
        pagination.addEventListener("next", cb);
    });
    const honors = await getList(currentPage, itemInAPage);
    pageContainer.show(new SummaryPage(honors, summaryTemplate, itemInAPage, currentPage, null, cInitializer));
});
