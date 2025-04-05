import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("网格管理", function (ev) {
    console.log("点击网格管理");
});

navigatorLoader.appendCell(cell);

export default cell;