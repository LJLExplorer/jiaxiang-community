import { GradientBase } from "./GradientBase.js";
class ConicGradient extends GradientBase {
    startAngle;
    x;
    y;
    constructor(startAngle, x, y) {
        super();
        this.startAngle = startAngle;
        this.x = x;
        this.y = y;
    }
}
export { ConicGradient };
