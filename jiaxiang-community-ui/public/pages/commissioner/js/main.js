import commissionerInformation from "../../../files/commissioner-information.js";
import CommissionerDetails from "../Commissioner/CommissionerDetails.js";
import CommissionerList from "../Commissioner/CommissionerList.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
document.addEventListener("DOMContentLoaded", function () {
    const pageContainer = document.querySelector("#page-container");
    const commissionerDetailsTemplate = document.querySelector("#commissioner-details-template");
    const commissionerSummaryTemplate = document.querySelector("#commissioner-summary-template");
    let itemInAPage = 4;
    let currentPage = 1;
    let cell;
    const cEvent = new EventRegister();
    commissionerInformation.forEach((commissioner, index) => {
        cEvent.regist("#" + commissioner.name, "click", (e) => {
            const detailsFragment = new CommissionerDetails(commissioner, commissionerDetailsTemplate);
            pageContainer.show(detailsFragment);
            cell = new NavigatorCell("返回上一级", function (ev) {
                pageContainer.show(new CommissionerList(commissionerInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
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
        pageContainer.show(new CommissionerList(commissionerInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    cEvent.regist("#next-arrow", "click", function () {
        if (currentPage >= Math.ceil(commissionerInformation.length / itemInAPage)) {
            return;
        }
        currentPage++;
        pageContainer.show(new CommissionerList(commissionerInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    const cInitializer = new DataInitializer();
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);
    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = Math.ceil(commissionerInformation.length / itemInAPage);
        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = Math.ceil(commissionerInformation.length / itemInAPage) + "";
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = commissionerInformation.length + "";
    });
    pageContainer.show(new CommissionerList(commissionerInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
});
