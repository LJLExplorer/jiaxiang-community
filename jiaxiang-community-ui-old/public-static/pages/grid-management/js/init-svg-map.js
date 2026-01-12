import router from "../../../common-component/router.js";
import { JiaxiangCommunityMapDriver } from "../../../common-component/jiaxiang-community-map-driver.js";
document.addEventListener("DOMContentLoaded", async function () {
    // 获取 modal 相关元素
    const modalContent = document.querySelector('.modal-content');
    const modalMessage = document.getElementById('modal-message');
    const closeBtn = document.querySelector('.close');
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
    driver.items.forEach(function (value, index, array) {
        const clientRect = value.svg.getBoundingClientRect();
        value.svg.style.transformOrigin = `${clientRect.left + clientRect.width / 2}px ${clientRect.top + clientRect.height / 2}px`;
        value.svg.addEventListener("click", function (ev) {
            // 阻止事件冒泡，避免影响其他元素
            ev.stopPropagation();
            // 将消息内容显示在 modal 中
            modalMessage.textContent = '没有消息';
            const transition = document.startViewTransition(() => {
                // 显示 modal
                modalContent.style.display = 'block';
                modalContent.style.top = `${ev.y}px`;
                modalContent.style.left = `${ev.x}px`;
            });
        });
    });
    // 定义关闭 modal 的函数
    function closeModal() {
        modalContent.style.display = 'none';
    }
    // 点击关闭按钮关闭 modal
    closeBtn.addEventListener('click', closeModal);
    // 点击 modal 外部区域也关闭 modal
    window.addEventListener('click', function (event) {
        if (event.target === modalContent) {
            closeModal();
        }
    });
    console.log(driver.items);
});
