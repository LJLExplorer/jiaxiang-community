import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../../config/router.js";

const cell = new NavigatorCell("网格管理", function (ev) {
    location.href = router["grid-management"];
});

navigatorLoader.appendCell(cell);

export default cell;