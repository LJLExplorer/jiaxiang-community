import CommissionerEvent from "./EventRegister.js";

interface CanRegistryEvent {
    event: CommissionerEvent;

    registryEvent(ele: DocumentFragment): void;

}

export default CanRegistryEvent;