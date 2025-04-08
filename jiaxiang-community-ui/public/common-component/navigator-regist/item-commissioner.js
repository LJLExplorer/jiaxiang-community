import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../router.js";
const cell = new NavigatorCell("两委成员", function (ev) {
    location.href = router["commissioner"];
});
navigatorLoader.appendCell(cell);
export default cell;
