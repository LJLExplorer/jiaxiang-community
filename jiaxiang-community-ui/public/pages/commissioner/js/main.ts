import CommissionerDetails from "../Commissioner/CommissionerDetails.js";
import PageContainer from "../../../common-component/multiple-pages/page-container.js";
import CommissionerList from "../Commissioner/CommissionerList.js";
import EventRegister from "../../../common-component/multiple-pages/EventRegister.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import DataInitializer from "../../../common-component/multiple-pages/DataInitializer.js";
import CommissionerDAO from "../Commissioner/CommissionerDAO.js";
import {getPages, getList, getDetails} from "./api-request.js";
import CommonPagination from "../../../common-component/common-pagination.js"

document.addEventListener("DOMContentLoaded", async function () {
    const pageContainer: PageContainer = document.querySelector("#page-container");
    const commissionerDetailsTemplate: HTMLTemplateElement = document.querySelector("#commissioner-details-template");
    const summaryTemplate: HTMLTemplateElement = document.querySelector("#commissioner-summary-template");

    let itemInAPage = 4;
    let currentPage = 1;


    const registEvent = function (list: CommissionerDAO[]) {
        const cEvent = new EventRegister();
        let cell: NavigatorCell;

        list.forEach((commissioner: CommissionerDAO, index) => {
            cEvent.regist("#commissioner" + commissioner.id,
                "click", async (e) => {

                    const commissionerInfos: CommissionerDAO = await getDetails(commissioner.id);

                    const detailsFragment = new CommissionerDetails(commissionerInfos, commissionerDetailsTemplate)
                    pageContainer.show(detailsFragment);

                    cell = new NavigatorCell("返回上一级", function (ev) {
                        pageContainer.show(new CommissionerList(list, summaryTemplate, itemInAPage, 1, cEvent, cInitializer));

                        navigatorLoader.removeCell(cell);
                        mainLoader.adapter.clear();
                        mainLoader.adapter.display();
                    });

                    navigatorLoader.appendCell(cell);
                    mainLoader.adapter.clear();
                    mainLoader.adapter.display();
                }
            )
        });

        return cEvent
    };

    const cInitializer = new DataInitializer();
    const pagesInfo = await getPages(itemInAPage);

    cInitializer.regist(".content", function (ele) {
        const pagination = new CommonPagination(currentPage, pagesInfo.pages, pagesInfo.total, itemInAPage);
        ele.appendChild(pagination);

        const cb = async function () {
            currentPage = this.currentPage;

            const page = await getList(currentPage, itemInAPage);
            pageContainer.show(new CommissionerList(page, summaryTemplate, itemInAPage, 1, registEvent(page), cInitializer));
        }

        pagination.addEventListener("pre", cb);
        pagination.addEventListener("next", cb);
    });


    const firstPage = await getList(1, itemInAPage);
    pageContainer.show(new CommissionerList(firstPage, summaryTemplate, itemInAPage, 1, registEvent(firstPage), cInitializer));

});
