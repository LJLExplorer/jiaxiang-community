// import commissionerInformation from "../../../files/commissioner-information.js";
import CommissionerDetails from "../Commissioner/CommissionerDetails.js";
import CommissionerList from "../Commissioner/CommissionerList.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import { getPages, getList, getDetails } from "./api-request.js";
document.addEventListener("DOMContentLoaded", async function () {
    const pageContainer = document.querySelector("#page-container");
    const commissionerDetailsTemplate = document.querySelector("#commissioner-details-template");
    const commissionerSummaryTemplate = document.querySelector("#commissioner-summary-template");
    let itemInAPage = 4;
    let currentPage = 1;
    //目前思路：
    /*
    * 1. 应该将注册事件封装成一个函数，每翻页一次调用一次，不可以一次注册所有事件√
    * 2. 获取数据应该写在main.ts中，当需要则获取，不应该在组件中获取√
    * 3. 要做一个调节返回对象和前端DAO的调节函数，好让前后端属性对齐。√
    * */
    const registEvent = function (list) {
        const cEvent = new EventRegister();
        let cell;
        list.forEach((commissioner, index) => {
            cEvent.regist("#commissioner" + commissioner.id, "click", async (e) => {
                const commissionerInfos = await getDetails(commissioner.id);
                const detailsFragment = new CommissionerDetails(commissionerInfos, commissionerDetailsTemplate);
                pageContainer.show(detailsFragment);
                cell = new NavigatorCell("返回上一级", function (ev) {
                    pageContainer.show(new CommissionerList(list, commissionerSummaryTemplate, itemInAPage, 1, cEvent, cInitializer));
                    navigatorLoader.removeCell(cell);
                    mainLoader.adapter.clear();
                    mainLoader.adapter.display();
                });
                navigatorLoader.appendCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });
        });
        cEvent.regist("#prev-arrow", "click", async function () {
            if (currentPage <= 1) {
                return;
            }
            currentPage--;
            const pre = await getList(currentPage, itemInAPage);
            pageContainer.show(new CommissionerList(pre, commissionerSummaryTemplate, itemInAPage, 1, cEvent, cInitializer));
        });
        cEvent.regist("#next-arrow", "click", async function () {
            if (currentPage >= pagesInfo.pages) {
                return;
            }
            currentPage++;
            const next = await getList(currentPage, itemInAPage);
            pageContainer.show(new CommissionerList(next, commissionerSummaryTemplate, itemInAPage, 1, cEvent, cInitializer));
        });
        return cEvent;
    };
    const cInitializer = new DataInitializer();
    const pagesInfo = await getPages(itemInAPage);
    console.log(pagesInfo);
    cInitializer.regist("#prev-arrow", function (ele) {
        ele.classList.toggle("enabled", currentPage > 1);
        ele.classList.toggle("disabled", currentPage === 1);
    });
    cInitializer.regist("#next-arrow", function (ele) {
        const totalPages = pagesInfo.pages;
        ele.classList.toggle("enabled", currentPage < totalPages);
        ele.classList.toggle("disabled", currentPage === totalPages);
    });
    cInitializer.regist("#current-page", function (ele) {
        ele.innerHTML = currentPage + "";
    });
    cInitializer.regist("#total-pages", function (ele) {
        ele.innerHTML = pagesInfo.pages;
    });
    cInitializer.regist("#total-items", function (ele) {
        ele.innerHTML = pagesInfo.total;
    });
    const firstPage = await getList(1, itemInAPage);
    console.log(firstPage);
    pageContainer.show(new CommissionerList(firstPage, commissionerSummaryTemplate, itemInAPage, 1, registEvent(firstPage), cInitializer));
});
