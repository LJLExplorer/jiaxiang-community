import CanRegistryEvent from "./CanRegistryEvent.js";
class CanInitData extends CanRegistryEvent {
    initializer;
    operate(ele) {
        this.initializer.list.forEach((callbacks, selector) => {
            const target = ele.querySelector(selector);
            if (target) {
                callbacks.forEach(callback => {
                    callback.call(ele, target);
                });
            }
        });
    }
}
export default CanInitData;
