import DataInitializer from "./DataInitializer.js";

interface CanInitData {
    initializer: DataInitializer;

    operate(ele: HTMLElement): void;

}

export default CanInitData;