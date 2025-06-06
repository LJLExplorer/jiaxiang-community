import ElementSelector from "./ElementSelector.js";


class DataInitializer {
    public list: Map<ElementSelector, Array<(element: Element) => any>> = new Map();

    public regist(target: ElementSelector, callback: (element: Element) => any): void {
        if (this.list.has(target)) {
            this.list.get(target).push(callback);
        } else {
            this.list.set(target, [callback]);
        }
    }
}

export default DataInitializer;