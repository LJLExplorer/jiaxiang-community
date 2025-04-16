import EventRegister from "./EventRegister.js";
import IsSubPage from "./IsSubPage.js";

class CanRegistryEvent extends IsSubPage{
    public event: EventRegister;

    public registryEvent(ele: DocumentFragment) {
        this.event.eventList.forEach((eventInfos, selector) => {
            const selectorEle = ele.querySelector(selector);

            if (selectorEle) {
                eventInfos.forEach((eventInfo) => {
                    selectorEle.addEventListener(eventInfo.type, eventInfo.listener);
                })
            }
        })
    }

}

export default CanRegistryEvent;