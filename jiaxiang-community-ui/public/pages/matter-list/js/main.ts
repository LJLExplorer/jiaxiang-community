import mattersInformation from "../../../files/matters-information.js";
import PageContainer from "../../../common-component/multiple-pages/page-container.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import DetailsPage from "../component/DetailsPage.js";
import SummaryPage from "../component/SummaryPage.js";

document.addEventListener("DOMContentLoaded", function () {
    const pageContainer: PageContainer = document.querySelector("#page-container");
    const detailsTemplate: HTMLTemplateElement = document.querySelector("#details-template");
    const summaryTemplate: HTMLTemplateElement = document.querySelector("#summary-template");


    let itemInAPage = 4;
    let currentPage = 1;

    let cell: NavigatorCell;

    const cEvent = new EventRegister();
    mattersInformation.forEach((matter, index) => {
        cEvent.regist("#" + matter.id, "click", (e) => {
            const detailsFragment = new DetailsPage(matter, detailsTemplate);
            pageContainer.show(detailsFragment);

            //在菜单中添加返回上一级
            cell = new NavigatorCell("返回上一级", function (ev) {
                pageContainer.show(new SummaryPage(mattersInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));

                navigatorLoader.removeCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });

            navigatorLoader.appendCell(cell);
            mainLoader.adapter.clear();
            mainLoader.adapter.display();
        })
    });
    cEvent.regist("#prev-arrow", "click", function () {
        if (currentPage <= 1) {
            return;
        }

        currentPage--;
        pageContainer.show(new SummaryPage(mattersInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    cEvent.regist("#next-arrow", "click", function () {
        if (currentPage >= Math.ceil(mattersInformation.length / itemInAPage)) {
            return;
        }

        currentPage++;
        pageContainer.show(new SummaryPage(mattersInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });

    const cInitializer = new DataInitializer();
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);

    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = Math.ceil(mattersInformation.length / itemInAPage);

        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = Math.ceil(mattersInformation.length / itemInAPage) + "";
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = mattersInformation.length + "";
    });


    pageContainer.show(new SummaryPage(mattersInformation, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
   /* const detailsFragment = new DetailsPage(mattersInformation[0], detailsTemplate);
    pageContainer.show(detailsFragment);*/
});
