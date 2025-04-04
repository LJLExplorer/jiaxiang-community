/*
* 图片轮播标准插件
* */
class SlideShow extends Object {
    image = null;
    hrefs = [];
    debug = false;
    index = 0;
    constructor(hrefs) {
        super();
        this.image = document.createElement("img");
        this.hrefs = hrefs;
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
        this.image.src = this.hrefs[this.index];
    }
    init(container = document.body) {
        container.appendChild(this.image);
        this.syncPictureIndex();
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
