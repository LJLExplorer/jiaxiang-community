import ElementSelector from "./ElementSelector.js";

type EventCB = (this: HTMLElement, ev: DocumentEventMap[keyof DocumentEventMap]) => any;

interface EventInfo {
    type: keyof DocumentEventMap;
    listener: EventCB;
}

/*用于注册事件的*/
class EventRegister {
    public eventList: Map<ElementSelector, EventInfo[]> = new Map();

    public regist<K extends keyof DocumentEventMap>
    (target: ElementSelector, type: K, listener: EventCB
    ): void {
        if(this.eventList.has(target)){
            this.eventList.get(target).push({type, listener});
        }else{
            this.eventList.set(target, [{type, listener}]);
        }
    }
}

export default EventRegister;