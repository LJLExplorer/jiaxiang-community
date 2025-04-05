import { GradientBase } from "./GradientBase.js";
class LinearGradient extends GradientBase {
    startX;
    startY;
    endX;
    endY;
    constructor(startX, startY, endX, endY) {
        super();
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
}
export { LinearGradient };
