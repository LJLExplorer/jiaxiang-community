import PageContainer from "../../../common-component/multiple-pages/page-container.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import serveInformation from "../Serve/serve-information.js";
import DetailsPage from "../Serve/DetailsPage.js";
import SummaryPage from "../Serve/SummaryPage.js";

document.addEventListener("DOMContentLoaded", function () {
    const pageContainer: PageContainer = document.querySelector("#page-container");
    const commissionerDetailsTemplate: HTMLTemplateElement = document.querySelector("#commissioner-details-template");
    const commissionerSummaryTemplate: HTMLTemplateElement = document.querySelector("#commissioner-summary-template");


    let itemInAPage = 4;
    let currentPage = 1;

    let cell: NavigatorCell;

    const cEvent = new EventRegister();
    /*
    *     id: string;
    title: string;
    phone: string;
    time: string;
    address: string;
    image: string;
    description: string;
    content: any[];
    rules: any[];*/
    serveInformation.forEach((serve, index) => {
        cEvent.regist("#" + serve.id, "click", (e) => {
            const detailsFragment = new DetailsPage(serve, commissionerDetailsTemplate)
            pageContainer.show(detailsFragment);

            cell = new NavigatorCell("返回上一级", function (ev) {
                pageContainer.show(new SummaryPage(serveInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent,cInitializer));

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
        pageContainer.show(new SummaryPage(serveInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent,cInitializer));
    });
    cEvent.regist("#next-arrow", "click", function () {
        if (currentPage >= Math.ceil(serveInformation.length / itemInAPage)) {
            return;
        }

        currentPage++;
        pageContainer.show(new SummaryPage(serveInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent,cInitializer));
    });

    const cInitializer = new DataInitializer();
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);

    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = Math.ceil(serveInformation.length / itemInAPage);

        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = Math.ceil(serveInformation.length / itemInAPage) + "";
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = serveInformation.length + "";
    });



    pageContainer.show(new SummaryPage(serveInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent,cInitializer));

});
