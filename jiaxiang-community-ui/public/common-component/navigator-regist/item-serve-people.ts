import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("为民服务", function (ev) {
    console.log("点击为民服务");
});

navigatorLoader.appendCell(cell);

export default cell;