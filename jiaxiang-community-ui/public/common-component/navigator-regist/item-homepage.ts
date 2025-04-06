import navigatorLoader from "./main-loader.js";
import NavigatorCell from "../../Navigator/NavigatorCell.js";
import router from "../router.js";

const cell = new NavigatorCell("返回首页", function (ev) {
    // @ts-ignore
    location = router.homepage
});

navigatorLoader.appendCell(cell);

export default cell;