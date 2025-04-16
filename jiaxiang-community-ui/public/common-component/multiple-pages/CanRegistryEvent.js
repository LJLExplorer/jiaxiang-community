import IsSubPage from "./IsSubPage.js";
class CanRegistryEvent extends IsSubPage {
    event;
    registryEvent(ele) {
        this.event.eventList.forEach((eventInfos, selector) => {
            const selectorEle = ele.querySelector(selector);
            if (selectorEle) {
                eventInfos.forEach((eventInfo) => {
                    selectorEle.addEventListener(eventInfo.type, eventInfo.listener);
                });
            }
        });
    }
}
export default CanRegistryEvent;
