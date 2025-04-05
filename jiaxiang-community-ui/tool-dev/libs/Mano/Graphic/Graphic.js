import { Arc } from "./Shapes/Arc.js";
import { Arc2 } from "./Shapes/Arc2.js";
import { Ellipse } from "./Shapes/Ellipse.js";
import { Line } from "./Shapes/Line.js";
import { BezierCurve } from "./Shapes/BezierCurve.js";
import { Rect } from "./Shapes/Rect.js";
import { RoundRect } from "./Shapes/RoundRect.js";
import { Text } from "./Shapes/Text.js";
import { Image } from "./Shapes/Image.js";
import { CustomGraphic } from "./Shapes/CustomGraphic.js";
import { ContextChangeEvent } from "../Event/ContextChangeEvent.js";
import { Group } from "./Shapes/Group.js";
import { GraphicBase } from "./GraphicBase.js";
class Graphic extends HTMLElement {
    static Arc = Arc;
    static Arc2 = Arc2;
    static Ellipse = Ellipse;
    static Line = Line;
    static BezierCurve = BezierCurve;
    static Rect = Rect;
    static RoundRect = RoundRect;
    static Text = Text;
    static Group = Group;
    static Image = Image;
    static CustomGraphic = CustomGraphic;
    mano;
    appendChild(node) {
        super.appendChild(node);
        if (!(node instanceof GraphicBase))
            return null;
        //触发预备程序，在下一次屏幕刷新的时候更新
        let ev = new ContextChangeEvent("contextchange", {
            bubbles: true,
            cancelable: true,
        });
        ev.source = "graphic";
        this.mano?.canvas?.dispatchEvent(ev);
        return node;
    }
    removeChild(child) {
        super.removeChild(child);
        //触发预备程序，在下一次屏幕刷新的时候更新
        let ev = new ContextChangeEvent("contextchange", {
            bubbles: true,
            cancelable: true,
        });
        this.mano?.canvas?.dispatchEvent(ev);
        return child;
    }
    constructor() {
        super();
        this.style.position = "absolute";
        this.style.zIndex = "1";
    }
}
customElements.define("mano-graphic", Graphic);
export { Graphic };
