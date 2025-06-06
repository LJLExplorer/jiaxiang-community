import router from "../../../config/router.js";
import {
    JiaxiangCommunityMapDriver,
    MapDriverReturnType
} from "../../../driver/jiaxiang-community-map-driver.js";
import {getList} from "./api-request.js";
import GridManagementDAO from "../component/GridManagementDAO";
import {ConstantObserver} from "../../../lib/ConstantObserver.js"

document.addEventListener("DOMContentLoaded", async function () {
    // 获取 modal 相关元素
    const dialog = document.querySelector('#favDialog') as HTMLDialogElement;
    const modalMessage = document.querySelector('#favDialog #modal-message') as HTMLElement;
    const titleEle = document.querySelector('#favDialog h2') as HTMLElement;
    const closeBtn = document.querySelector('.close');

    let gridManamentInfo: GridManagementDAO[]  = await getList();

    const svgContainer = document.getElementById("svg-container");

    const response = await fetch(router["jiaxiang-community-map"]);
    svgContainer.innerHTML = await response.text();

    const driver = new JiaxiangCommunityMapDriver(svgContainer.querySelector("svg"));
    driver.parse();

    /*interface MapDriverReturnType {
    *    name: string;
    *    svg: SVGElement;
    *    patternPath: Array<PathOperation>;
    *    edgePath: Array<PathOperation>
    *}
    */

    driver.items.forEach(function (value: MapDriverReturnType, index, array) {
        const clientRect = value.svg.getBoundingClientRect();

        value.svg.style.transformOrigin = `${clientRect.left + clientRect.width / 2}px ${clientRect.top + clientRect.height / 2}px`;

        value.svg.addEventListener("click", async function (ev) {
            // 阻止事件冒泡，避免影响其他元素
            ev.stopPropagation();

            const info = gridManamentInfo.find((item) => item.community === value.name);
            titleEle.innerText = info.meta.title;
            modalMessage.innerText = info.meta.profile;

            dialog.showModal();
        });
    });

    // 定义关闭 modal 的函数
    function closeModal() {
        dialog.close();
    }

    // 点击关闭按钮关闭 modal
    closeBtn.addEventListener('click', closeModal);

    // 点击 modal 外部区域也关闭 modal
    window.addEventListener('click', function (event) {
        if (event.target === dialog) {
            closeModal();
        }
    });
})