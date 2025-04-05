var __runInitializers = (this && this.__runInitializers) || function (thisArg, initializers, value) {
    var useValue = arguments.length > 2;
    for (var i = 0; i < initializers.length; i++) {
        value = useValue ? initializers[i].call(thisArg, value) : initializers[i].call(thisArg);
    }
    return useValue ? value : void 0;
};
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
import { Mapping, RequestMapping } from "./Mapper.js";
const rand = Math.floor(Math.random() * 3);
let Test = (() => {
    let _instanceExtraInitializers = [];
    let _a_decorators;
    let _b_decorators;
    let _c_decorators;
    return class Test {
        static {
            const _metadata = typeof Symbol === "function" && Symbol.metadata ? Object.create(null) : void 0;
            _a_decorators = [Mapping(0, 0)];
            _b_decorators = [Mapping(0, 1)];
            _c_decorators = [Mapping(0, 2)];
            __esDecorate(this, null, _a_decorators, { kind: "method", name: "a", static: false, private: false, access: { has: obj => "a" in obj, get: obj => obj.a }, metadata: _metadata }, null, _instanceExtraInitializers);
            __esDecorate(this, null, _b_decorators, { kind: "method", name: "b", static: false, private: false, access: { has: obj => "b" in obj, get: obj => obj.b }, metadata: _metadata }, null, _instanceExtraInitializers);
            __esDecorate(this, null, _c_decorators, { kind: "method", name: "c", static: false, private: false, access: { has: obj => "c" in obj, get: obj => obj.c }, metadata: _metadata }, null, _instanceExtraInitializers);
            if (_metadata) Object.defineProperty(this, Symbol.metadata, { enumerable: true, configurable: true, writable: true, value: _metadata });
        }
        a() {
            console.log('a');
        }
        b() {
            console.log('b');
        }
        c() {
            console.log('c');
        }
        constructor() {
            __runInitializers(this, _instanceExtraInitializers);
        }
    };
})();
RequestMapping(0, rand);
