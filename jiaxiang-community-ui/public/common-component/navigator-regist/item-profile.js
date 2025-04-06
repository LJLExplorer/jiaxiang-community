import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../router.js";
const cell = new NavigatorCell("社区简介", function (ev) {
    location.href = router.profile;
});
navigatorLoader.appendCell(cell);
export default cell;
