import commissionerInformation from "../../../files/commissioner_information.js";
import CommissionerDetails from "../Commissioner/CommissionerDetails.js";
import PageContainer from "./page-container.js";
import CommissionerList from "../Commissioner/CommissionerList.js";
import CommissionerEvent from "../Commissioner/CommissionerEvent.js";
import navigatorLoader from "../../../common-component/navigator-regist/main-loader.js";
import NavigatorCell from "../../../Navigator/NavigatorCell.js";
import mainLoader from "../../../common-component/navigator-regist/main-loader.js";

document.addEventListener("DOMContentLoaded", function () {
    const pageContainer: PageContainer = document.querySelector("#page-container");
    const commissionerDetailsTemplate: HTMLTemplateElement = document.querySelector("#commissioner-details-template");
    const commissionerSummaryTemplate: HTMLTemplateElement = document.querySelector("#commissioner-summary-template");


    let itemInAPage = 4;
    let currentPage = 1;

    let cell: NavigatorCell;
    const cEvent = new CommissionerEvent();


    commissionerInformation.forEach((commissioner, index) => {
        cEvent.regist(commissioner, "click", (e) => {
            const detailsFragment = new CommissionerDetails(commissioner, commissionerDetailsTemplate)
            pageContainer.show(detailsFragment);

            cell = new NavigatorCell("返回上一级", function (ev) {
                changeToPage1();

                navigatorLoader.removeCell(cell);
                mainLoader.adapter.clear();
                mainLoader.adapter.display();
            });

            navigatorLoader.appendCell(cell);
            mainLoader.adapter.clear();
            mainLoader.adapter.display();
        })
    });


    let changeToPage1 = async function () {
        await pageContainer.show(new CommissionerList(commissionerInformation, commissionerSummaryTemplate, itemInAPage, currentPage, cEvent));

        const prevArrow = pageContainer.querySelector("#prev-arrow");
        const nextArrow = pageContainer.querySelector("#next-arrow");
        const pageNumber = pageContainer.querySelector("#current-page");
        const totalPage = pageContainer.querySelector("#total-pages");
        const totalItems = pageContainer.querySelector("#total-items");


        const totalPages = Math.ceil(commissionerInformation.length / itemInAPage);
        pageNumber.innerHTML = currentPage + "";
        totalPage.innerHTML = Math.ceil(commissionerInformation.length / itemInAPage) + "";
        totalItems.innerHTML = commissionerInformation.length + "";
        prevArrow.classList.toggle("enabled", currentPage > 1);
        prevArrow.classList.toggle("disabled", currentPage === 1);

        nextArrow.classList.toggle("enabled", currentPage < totalPages);
        nextArrow.classList.toggle("disabled", currentPage === totalPages);

        prevArrow.addEventListener("click", function () {
            if (currentPage <= 1) {
                return;
            }

            currentPage--;
            changeToPage1();
        });
        nextArrow.addEventListener("click", function () {
            if (currentPage >= Math.ceil(commissionerInformation.length / itemInAPage)){
                return;
            }

            currentPage++;
            changeToPage1();
        });
    }


    changeToPage1();
});
