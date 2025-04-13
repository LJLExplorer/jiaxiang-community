import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../../config/router.js";

const cell = new NavigatorCell("证据出具", function (ev) {
    location.href = router["proof"];
});

navigatorLoader.appendCell(cell);

export default cell;