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
import { ColorBase } from "../ColorBase.js";
import { NumberInRange } from "../../Unit/NumberInRange.js";
let RGBA = (() => {
    let _classSuper = ColorBase;
    let _R_decorators;
    let _R_initializers = [];
    let _R_extraInitializers = [];
    let _G_decorators;
    let _G_initializers = [];
    let _G_extraInitializers = [];
    let _B_decorators;
    let _B_initializers = [];
    let _B_extraInitializers = [];
    let _Alpha_decorators;
    let _Alpha_initializers = [];
    let _Alpha_extraInitializers = [];
    return class RGBA extends _classSuper {
        static {
            const _metadata = typeof Symbol === "function" && Symbol.metadata ? Object.create(_classSuper[Symbol.metadata] ?? null) : void 0;
            _R_decorators = [NumberInRange(0, 255)];
            _G_decorators = [NumberInRange(0, 255)];
            _B_decorators = [NumberInRange(0, 255)];
            _Alpha_decorators = [NumberInRange(0, 1)];
            __esDecorate(null, null, _R_decorators, { kind: "field", name: "R", static: false, private: false, access: { has: obj => "R" in obj, get: obj => obj.R, set: (obj, value) => { obj.R = value; } }, metadata: _metadata }, _R_initializers, _R_extraInitializers);
            __esDecorate(null, null, _G_decorators, { kind: "field", name: "G", static: false, private: false, access: { has: obj => "G" in obj, get: obj => obj.G, set: (obj, value) => { obj.G = value; } }, metadata: _metadata }, _G_initializers, _G_extraInitializers);
            __esDecorate(null, null, _B_decorators, { kind: "field", name: "B", static: false, private: false, access: { has: obj => "B" in obj, get: obj => obj.B, set: (obj, value) => { obj.B = value; } }, metadata: _metadata }, _B_initializers, _B_extraInitializers);
            __esDecorate(null, null, _Alpha_decorators, { kind: "field", name: "Alpha", static: false, private: false, access: { has: obj => "Alpha" in obj, get: obj => obj.Alpha, set: (obj, value) => { obj.Alpha = value; } }, metadata: _metadata }, _Alpha_initializers, _Alpha_extraInitializers);
            if (_metadata) Object.defineProperty(this, Symbol.metadata, { enumerable: true, configurable: true, writable: true, value: _metadata });
        }
        type = "rgb";
        R = __runInitializers(this, _R_initializers, void 0);
        G = (__runInitializers(this, _R_extraInitializers), __runInitializers(this, _G_initializers, void 0));
        B = (__runInitializers(this, _G_extraInitializers), __runInitializers(this, _B_initializers, void 0));
        Alpha = (__runInitializers(this, _B_extraInitializers), __runInitializers(this, _Alpha_initializers, void 0));
        toString() {
            return `rgba(${this.R},${this.G},${this.B},${this.Alpha})`;
        }
        constructor(R, G, B, Alpha = 1) {
            super();
            __runInitializers(this, _Alpha_extraInitializers);
            this.R = R;
            this.G = G;
            this.B = B;
            this.Alpha = Alpha;
        }
    };
})();
export { RGBA };
