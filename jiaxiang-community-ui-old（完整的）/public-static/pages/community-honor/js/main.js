import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import honors from "../Honor/honor-information.js";
import SummaryPage from "../Honor/SummaryPage.js";
document.addEventListener("DOMContentLoaded", function () {
    const pageContainer = document.querySelector("#page-container");
    const detailsTemplate = document.querySelector("#details-template");
    const summaryTemplate = document.querySelector("#summary-template");
    let itemInAPage = 6;
    let currentPage = 1;
    let cell;
    const cEvent = new EventRegister();
    /*
        id: string;
    content: string;
    */
    //这里不需要跳转
    /*honors.forEach((serve, index) => {
        cEvent.regist("#" + serve.id, "click", (e) => {
            const detailsFragment = new DetailsPage(serve, detailsTemplate)
            pageContainer.show(detailsFragment);

            cell = new NavigatorCell("返回上一级", function (ev) {
                pageContainer.show(new SummaryPage(honors, summaryTemplate, itemInAPage, currentPage, cEvent,cInitializer));

                navigatorLoader.removeCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });

            navigatorLoader.appendCell(cell);
            mainLoader.adapter.clear();
            mainLoader.adapter.display();
        })
    });*/
    cEvent.regist("#prev-arrow", "click", function () {
        if (currentPage <= 1) {
            return;
        }
        currentPage--;
        pageContainer.show(new SummaryPage(honors, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    cEvent.regist("#next-arrow", "click", function () {
        if (currentPage >= Math.ceil(honors.length / itemInAPage)) {
            return;
        }
        currentPage++;
        pageContainer.show(new SummaryPage(honors, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
    });
    const cInitializer = new DataInitializer();
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);
    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = Math.ceil(honors.length / itemInAPage);
        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = Math.ceil(honors.length / itemInAPage) + "";
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = honors.length + "";
    });
    pageContainer.show(new SummaryPage(honors, summaryTemplate, itemInAPage, currentPage, cEvent, cInitializer));
});
