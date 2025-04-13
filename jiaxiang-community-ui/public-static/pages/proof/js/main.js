import proofsInformation from "../../../files/proofs-information.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import DetailsPage from "../component/DetailsPage.js";
import SummaryPage from "../component/SummaryPage.js";
document.addEventListener("DOMContentLoaded", function () {
    const pageContainer = document.querySelector("#page-container");
    const detailsTemplate = document.querySelector("#details-template");
    const summaryTemplate = document.querySelector("#summary-template");
    let itemInAPage = 6;
    let currentPage = 1;
    let cell;
    const cEvent = new EventRegister();
    proofsInformation.forEach((proof, index) => {
        cEvent.regist("#" + proof.id, "click", (e) => {
            const detailsFragment = new DetailsPage(proof, detailsTemplate);
            pageContainer.show(detailsFragment);
            //在菜单中添加返回上一级
            cell = new NavigatorCell("返回上一级", function (ev) {
                pageContainer.show(new SummaryPage(proofsInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
                navigatorLoader.removeCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });
            navigatorLoader.appendCell(cell);
            mainLoader.adapter.clear();
            mainLoader.adapter.display();
        });
    });
    cEvent.regist("#prev-arrow", "click", function () {
        if (currentPage <= 1) {
            return;
        }
        currentPage--;
        pageContainer.show(new SummaryPage(proofsInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    cEvent.regist("#next-arrow", "click", function () {
        if (currentPage >= Math.ceil(proofsInformation.length / itemInAPage)) {
            return;
        }
        currentPage++;
        pageContainer.show(new SummaryPage(proofsInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    const cInitializer = new DataInitializer();
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);
    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = Math.ceil(proofsInformation.length / itemInAPage);
        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = Math.ceil(proofsInformation.length / itemInAPage) + "";
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = proofsInformation.length + "";
    });
    pageContainer.show(new SummaryPage(proofsInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    /* const detailsFragment = new DetailsPage(proofsInformation[0], detailsTemplate);
     pageContainer.show(detailsFragment);*/
});
