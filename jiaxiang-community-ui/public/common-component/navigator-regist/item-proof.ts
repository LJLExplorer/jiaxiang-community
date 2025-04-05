import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";

const cell = new NavigatorCell("证据出具", function (ev) {
    console.log("点击证据出具");
});

navigatorLoader.appendCell(cell);

export default cell;