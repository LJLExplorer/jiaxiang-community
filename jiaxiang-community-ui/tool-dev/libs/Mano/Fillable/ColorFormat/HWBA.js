var __esDecorate = (this && this.__esDecorate) || function (ctor, descriptorIn, decorators, contextIn, initializers, extraInitializers) {
    function accept(f) { if (f !== void 0 && typeof f !== "function") throw new TypeError("Function expected"); return f; }
    var kind = contextIn.kind, key = kind === "getter" ? "get" : kind === "setter" ? "set" : "value";
    var target = !descriptorIn && ctor ? contextIn["static"] ? ctor : ctor.prototype : null;
    var descriptor = descriptorIn || (target ? Object.getOwnPropertyDescriptor(target, contextIn.name) : {});
    var _, done = false;
    for (var i = decorators.length - 1; i >= 0; i--) {
        var context = {};
        for (var p in contextIn) context[p] = p === "access" ? {} : contextIn[p];
        for (var p in contextIn.access) context.access[p] = contextIn.access[p];
        context.addInitializer = function (f) { if (done) throw new TypeError("Cannot add initializers after decoration has completed"); extraInitializers.push(accept(f || null)); };
        var result = (0, decorators[i])(kind === "accessor" ? { get: descriptor.get, set: descriptor.set } : descriptor[key], context);
        if (kind === "accessor") {
            if (result === void 0) continue;
            if (result === null || typeof result !== "object") throw new TypeError("Object expected");
            if (_ = accept(result.get)) descriptor.get = _;
            if (_ = accept(result.set)) descriptor.set = _;
            if (_ = accept(result.init)) initializers.unshift(_);
        }
        else if (_ = accept(result)) {
            if (kind === "field") initializers.unshift(_);
            else descriptor[key] = _;
        }
    }
    if (target) Object.defineProperty(target, contextIn.name, descriptor);
    done = true;
};
var __runInitializers = (this && this.__runInitializers) || function (thisArg, initializers, value) {
    var useValue = arguments.length > 2;
    for (var i = 0; i < initializers.length; i++) {
        value = useValue ? initializers[i].call(thisArg, value) : initializers[i].call(thisArg);
    }
    return useValue ? value : void 0;
};
import { NumberInRange } from "../../Unit/NumberInRange.js";
import { ColorBase } from "../ColorBase.js";
let HWBA = (() => {
    let _classSuper = ColorBase;
    let _H_decorators;
    let _H_initializers = [];
    let _H_extraInitializers = [];
    let _W_decorators;
    let _W_initializers = [];
    let _W_extraInitializers = [];
    let _B_decorators;
    let _B_initializers = [];
    let _B_extraInitializers = [];
    let _Alpha_decorators;
    let _Alpha_initializers = [];
    let _Alpha_extraInitializers = [];
    return class HWBA extends _classSuper {
        static {
            const _metadata = typeof Symbol === "function" && Symbol.metadata ? Object.create(_classSuper[Symbol.metadata] ?? null) : void 0;
            _H_decorators = [NumberInRange(0, 360)];
            _W_decorators = [NumberInRange(0, 100)];
            _B_decorators = [NumberInRange(0, 100)];
            _Alpha_decorators = [NumberInRange(0, 1)];
            __esDecorate(null, null, _H_decorators, { kind: "field", name: "H", static: false, private: false, access: { has: obj => "H" in obj, get: obj => obj.H, set: (obj, value) => { obj.H = value; } }, metadata: _metadata }, _H_initializers, _H_extraInitializers);
            __esDecorate(null, null, _W_decorators, { kind: "field", name: "W", static: false, private: false, access: { has: obj => "W" in obj, get: obj => obj.W, set: (obj, value) => { obj.W = value; } }, metadata: _metadata }, _W_initializers, _W_extraInitializers);
            __esDecorate(null, null, _B_decorators, { kind: "field", name: "B", static: false, private: false, access: { has: obj => "B" in obj, get: obj => obj.B, set: (obj, value) => { obj.B = value; } }, metadata: _metadata }, _B_initializers, _B_extraInitializers);
            __esDecorate(null, null, _Alpha_decorators, { kind: "field", name: "Alpha", static: false, private: false, access: { has: obj => "Alpha" in obj, get: obj => obj.Alpha, set: (obj, value) => { obj.Alpha = value; } }, metadata: _metadata }, _Alpha_initializers, _Alpha_extraInitializers);
            if (_metadata) Object.defineProperty(this, Symbol.metadata, { enumerable: true, configurable: true, writable: true, value: _metadata });
        }
        type = "hwb";
        H = __runInitializers(this, _H_initializers, void 0);
        W = (__runInitializers(this, _H_extraInitializers), __runInitializers(this, _W_initializers, void 0));
        B = (__runInitializers(this, _W_extraInitializers), __runInitializers(this, _B_initializers, void 0));
        Alpha = (__runInitializers(this, _B_extraInitializers), __runInitializers(this, _Alpha_initializers, void 0));
        toString() {
            return `hwba(${this.H}deg, ${this.W}%, ${this.B}%, ${this.Alpha})`;
        }
        constructor(H, W, B, Alpha = 1) {
            super();
            __runInitializers(this, _Alpha_extraInitializers);
            this.H = H;
            this.W = W;
            this.B = B;
            this.Alpha = Alpha;
        }
    };
})();
export { HWBA };
