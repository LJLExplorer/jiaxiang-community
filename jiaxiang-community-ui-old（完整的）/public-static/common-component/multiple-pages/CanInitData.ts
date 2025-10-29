import DataInitializer from "./DataInitializer.js";

interface CanInitData {
    initializer: DataInitializer;

    operate(ele: DocumentFragment): void;

}

export default CanInitData;