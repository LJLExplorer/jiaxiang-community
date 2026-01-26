// import router from "../../jiaxiang/config/api-urls.js";
import { JiaxiangCommunityMapDriver } from "../plugins/jiaxiang-community-map-driver.js";
import { getList } from "./api-request.js";
// ffa
document.addEventListener("DOMContentLoaded", async function () {
    let gridManamentInfo = await getList();
    const svgContainer = document.getElementById("svg-container");
    svgContainer.innerHTML = await (await fetch("/index/img/嘉祥社区地图.svg")).text();
    const svg = svgContainer.querySelector("svg");
    const driver = new JiaxiangCommunityMapDriver(svg);
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
        // 计算g标签自身的中心点（基于其内容的边界框）
        function getGroupCenter(group) {
            const bbox = group.getBBox(); // 获取g标签内容的边界框（x,y为左上角坐标，width/height为尺寸）
            return {
                x: bbox.x + bbox.width / 2, // 中心x坐标（相对于svg画布）
                y: bbox.y + bbox.height / 2 // 中心y坐标（相对于svg画布）
            };
        }
        // 初始化：设置缩放基准为g标签自身中心
        const initialCenter = getGroupCenter(value.svg);
        value.svg.setAttribute('transform-origin', `${initialCenter.x}px ${initialCenter.y}px`);
        value.svg.addEventListener('mouseover', function () {
            svg.appendChild(this);
            this.setAttribute('filter', 'url(#hoverEffects)');
            // this.setAttribute('transform', 'scale(1.1)');
        });
        value.svg.addEventListener('mouseout', function () {
            // 移除滤镜和变换
            this.removeAttribute('filter');
            // this.removeAttribute('transform');
        });
        value.svg.addEventListener("click", async function (ev) {
            // 阻止事件冒泡，避免影响其他元素
            ev.stopPropagation();
            const info = gridManamentInfo.find((item) => item.community === value.name);
            // location = info.href;
            location.href = "/jiaxiang/home.html";
        });
    });
});
