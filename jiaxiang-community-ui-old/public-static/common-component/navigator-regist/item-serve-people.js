import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../router.js";
const cell = new NavigatorCell("为民服务", function (ev) {
    location.href = router["serve-people"];
});
navigatorLoader.appendCell(cell);
export default cell;
