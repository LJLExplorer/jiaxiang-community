class TextFormat {
    textAlign;
    textBaseline = "hanging";
    textRendering;
    wordSpacing;
    letterSpacing;
    direction;
    constructor(init) {
        //将所有init中的值赋给this中
        Object.assign(this, init);
    }
}
export { TextFormat };
