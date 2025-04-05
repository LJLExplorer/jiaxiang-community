import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("两委成员", function (ev) {
    console.log("点击两委成员");
});

navigatorLoader.appendCell(cell);

export default cell;