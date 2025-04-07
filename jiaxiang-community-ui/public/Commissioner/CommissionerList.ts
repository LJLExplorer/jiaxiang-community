import CommissionerDAO from "./CommissionerDAO.js";
import CommissionerEvent from "./CommissionerEvent.js";
import SubPage from "../common-component/SubPage.js";


class CommissionerList implements SubPage {
    public list: CommissionerDAO[];
    public event: CommissionerEvent;
    public currentPage: number;
    public itemInAPage: number;
    public totalPage: number;

    public render() {
        /*
        * if(this.event.eventList[currentCommissioner]){
        *   //xxx循环注册事件
        * }
        *
        * */
    }
}

export default CommissionerList;