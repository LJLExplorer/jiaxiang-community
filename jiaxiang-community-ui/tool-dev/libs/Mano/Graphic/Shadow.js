class Shadow {
    blur;
    color;
    offsetX;
    offsetY;
    constructor(init) {
        //将所有init中的值赋给this中
        Object.assign(this, init);
    }
}
export { Shadow };
