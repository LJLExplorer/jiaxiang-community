import DataInitializer from "./DataInitializer.js";
import CanRegistryEvent from "./CanRegistryEvent.js";

class CanInitData extends CanRegistryEvent{
    public initializer: DataInitializer;

    public operate(ele: DocumentFragment): void {
        this.initializer.list.forEach((callbacks, selector) => {
            const target = ele.querySelector(selector);
            if (target) {
                callbacks.forEach(callback => {
                    callback.call(ele, target);
                })
            }
        })
    }
}

export default CanInitData;