import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("事项清单", function (ev) {
    console.log("点击事项清单");
});

navigatorLoader.appendCell(cell);

export default cell;