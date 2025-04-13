import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../router.js";

const cell = new NavigatorCell("事项清单", function (ev) {
    location.href = router["matter-list"];
});

navigatorLoader.appendCell(cell);

export default cell;