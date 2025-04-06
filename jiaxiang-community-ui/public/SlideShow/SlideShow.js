/*
* 图片轮播标准插件
* */
class SlideShow extends Object {
    image;
    hrefs = [];
    debug = false;
    index = 0;
    constructor(hrefs, container = document.body) {
        super();
        this.hrefs = hrefs;
        this.image = container;
        this.syncPictureIndex();
    }
    print(...msg) {
        if (!this.debug)
            return false;
        console.log(...msg);
    }
    resetIndex() {
        this.index = 0;
    }
    syncPictureIndex() {
        if (!(this.index in Object.keys(this.hrefs))) {
            this.resetIndex();
        }
        this.print(this.hrefs[this.index]);
        this.updateCallback(this.hrefs[this.index]);
        // this.image.style.backgroundImage = `url(${this.hrefs[this.index]})`;
    }
    updateCallback(src) {
    }
    next() {
        this.index++;
        this.syncPictureIndex();
    }
    pre() {
        this.index--;
        this.syncPictureIndex();
    }
}
export default SlideShow;
