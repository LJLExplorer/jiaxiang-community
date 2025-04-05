import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("社区简介", function (ev) {
    console.log("点击社区简介");
});

navigatorLoader.appendCell(cell);

export default cell;