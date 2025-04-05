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
import { GraphicBase } from "../GraphicBase.js";
import { NonNegativeNumber } from "../../Unit/NonNegativeNumber.js";
import { FILL_TYPE } from "../FILL_TYPE.js";
import { Text } from "./Text.js";
import { Debugger } from "../../Canvas/DebugOptions.js";
import { ColorBase } from "../../Fillable/ColorBase.js";
import { Parttern } from "../../Fillable/Parttern.js";
import { GradientBase } from "../../Fillable/GradientBase.js";
import { LinearGradient } from "../../Fillable/LinearGradient.js";
import { RadialGradient } from "../../Fillable/RadialGradient.js";
import { ConicGradient } from "../../Fillable/ConicGradient.js";
import { FillableGradientError } from "../../Exception/Fillable.GradientError.js";
let Arc = (() => {
    let _classSuper = GraphicBase;
    let _radius_decorators;
    let _radius_initializers = [];
    let _radius_extraInitializers = [];
    return class Arc extends _classSuper {
        static {
            const _metadata = typeof Symbol === "function" && Symbol.metadata ? Object.create(_classSuper[Symbol.metadata] ?? null) : void 0;
            _radius_decorators = [NonNegativeNumber];
            __esDecorate(null, null, _radius_decorators, { kind: "field", name: "radius", static: false, private: false, access: { has: obj => "radius" in obj, get: obj => obj.radius, set: (obj, value) => { obj.radius = value; } }, metadata: _metadata }, _radius_initializers, _radius_extraInitializers);
            if (_metadata) Object.defineProperty(this, Symbol.metadata, { enumerable: true, configurable: true, writable: true, value: _metadata });
        }
        x;
        y;
        radius = __runInitializers(this, _radius_initializers, void 0);
        startAngle = __runInitializers(this, _radius_extraInitializers);
        endAngle;
        counterclockwise;
        set content(content) {
            super.content = content;
            let text = new Text(content, this.x, this.y, this.radius);
            text.textFormat = this.textFormat;
            text.textShadow = this.textShadow;
            text.textTransform = this.textTransform;
            text.font = this.font;
            text.color = this.color;
            this.appendChild(text);
        }
        get content() {
            return super.content;
        }
        updateBoundingRect() {
            let { a, b, c, d, e, f } = this.boxTransform;
            let x = this.x - this.radius;
            let y = this.y - this.radius;
            let width = this.radius * 2;
            let height = this.radius * 2;
            this.setBoundingClientRect({ x, y, width, height });
            this.style.cssText = `
        display:block;
        position:absolute;
        z-index:1;
        left:${x}px;
        top:${y}px;
        transform:matrix(${a},${b},${c},${d},${e},${f});
        transform-origin:-${x}px -${y}px;
        width:${width}px;
        height:${height}px;
        `;
            if (Debugger.graphicEdges)
                this.style.border = "green solid 1px";
        }
        #setStyles(crc) {
            crc.shadowBlur = this?.boxShadow?.blur || 0;
            crc.shadowColor = this?.boxShadow?.color?.toString() || "rgb(255,255,255)";
            crc.shadowOffsetX = this?.boxShadow?.offsetX || 0;
            crc.shadowOffsetY = this?.boxShadow?.offsetY || 0;
            crc.lineCap = this?.border?.lineCap || "square";
            crc.lineDashOffset = this?.border?.lineDash || 10;
            crc.lineJoin = this?.border?.lineJoin || "bevel";
            crc.lineWidth = this?.border?.lineWidth || 1;
            crc.setTransform(this.boxTransform || new DOMMatrix([1, 0, 0, 1, 0, 0]));
            if (this.backgroundColor instanceof ColorBase) {
                crc.fillStyle = this.backgroundColor.toString();
                crc.strokeStyle = this.backgroundColor.toString();
            }
            else if (this.backgroundColor instanceof Parttern) {
                if (!this.backgroundColor.image) {
                    crc.fillStyle = "rgb(0,0,0)";
                    crc.strokeStyle = "rgb(0,0,0)";
                }
                crc.fillStyle = crc.createPattern(this.backgroundColor.image, this.backgroundColor.repetition);
                crc.strokeStyle = crc.createPattern(this.backgroundColor.image, this.backgroundColor.repetition);
            }
            else if (this.backgroundColor instanceof GradientBase) {
                let gradient = null;
                if (this.backgroundColor instanceof LinearGradient) {
                    gradient = crc.createLinearGradient(this.backgroundColor.startX, this.backgroundColor.startY, this.backgroundColor.endX, this.backgroundColor.endY);
                }
                else if (this.backgroundColor instanceof RadialGradient) {
                    gradient = crc.createRadialGradient(this.backgroundColor.cx0, this.backgroundColor.cy0, this.backgroundColor.cr0, this.backgroundColor.cx1, this.backgroundColor.cy1, this.backgroundColor.cr1);
                }
                else if (this.backgroundColor instanceof ConicGradient) {
                    gradient = crc.createConicGradient(this.backgroundColor.startAngle, this.backgroundColor.x, this.backgroundColor.y);
                }
                if (!gradient) {
                    throw new FillableGradientError("渐变怎么能没有呢？");
                }
                this.backgroundColor.colorStops.forEach(({ offset, color }, i, a) => {
                    gradient.addColorStop(offset, color.toString());
                });
                crc.fillStyle = gradient;
                crc.strokeStyle = gradient;
            }
        }
        render(canvas) {
            let crc = super.render(canvas);
            crc.beginPath();
            this.#setStyles(crc);
            this.content = this.content || "";
            this.path = new Path2D();
            this.path.arc(this.x, this.y, this.radius, this.startAngle, this.endAngle, this.counterclockwise);
            this.fillType === FILL_TYPE.GRAPHIC_FILL ?
                crc.fill(this.path) :
                crc.stroke(this.path);
            crc.closePath();
            this.renderChildren(canvas);
            return null;
        }
        constructor(x, y, radius, startAngle, endAngle, counterclockwise) {
            super();
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.startAngle = startAngle;
            this.endAngle = endAngle;
            this.counterclockwise = counterclockwise;
        }
    };
})();
customElements.define("mano-arc", Arc);
export { Arc };
