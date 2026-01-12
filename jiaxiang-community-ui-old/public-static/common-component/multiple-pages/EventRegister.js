/*用于注册事件的*/
class EventRegister {
    eventList = new Map();
    regist(target, type, listener) {
        if (this.eventList.has(target)) {
            this.eventList.get(target).push({ type, listener });
        }
        else {
            this.eventList.set(target, [{ type, listener }]);
        }
    }
}
export default EventRegister;
