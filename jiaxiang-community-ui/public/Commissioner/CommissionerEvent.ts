import CommissionerDAO from "./CommissionerDAO.js";

class CommissionerEvent {
    public eventList: CommissionerDAO[];

    public regist<K extends keyof DocumentEventMap>
    (target: CommissionerDAO, type: K, listener: (this: HTMLElement, ev: DocumentEventMap[K]) => any
    ): void {

    }
}
export default CommissionerEvent;