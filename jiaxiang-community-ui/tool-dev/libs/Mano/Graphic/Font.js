class Font {
    fontKerning;
    fontStretch;
    fontVariantCaps;
    font;
    constructor(init) {
        //将所有init中的值赋给this中
        Object.assign(this, init);
    }
}
export { Font };
