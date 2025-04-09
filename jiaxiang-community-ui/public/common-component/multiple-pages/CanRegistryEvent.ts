import CommissionerEvent from "./EventRegister.js";

interface CanRegistryEvent {
    event: CommissionerEvent;

    registryEvent(ele: HTMLElement): void;

}

export default CanRegistryEvent;