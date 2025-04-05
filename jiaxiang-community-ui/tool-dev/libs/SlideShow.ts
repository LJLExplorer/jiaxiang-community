/*
* 图片轮播标准插件
* */
class SlideShow extends Object {
    public image = null;
    public hrefs = [];
    public debug = false;
    private index = 0;

    constructor(hrefs) {
        super();
        this.image = document.createElement("img");
        this.hrefs = hrefs;
    }

    public print(...msg) {
        if (!this.debug) return false;

        console.log(...msg);
    }

    public resetIndex() {
        this.index = 0;
    }

    public syncPictureIndex() {
        if (!(this.index in Object.keys(this.hrefs))) {
            this.resetIndex();
        }

        this.print(this.hrefs[this.index])
        this.image.src = this.hrefs[this.index];
    }

    public init(container: HTMLElement = document.body) {
        container.appendChild(this.image);
        this.syncPictureIndex();
    }

    public next() {
        this.index++;
        this.syncPictureIndex();
    }

    public pre() {
        this.index--
        this.syncPictureIndex();
    }

}

export default SlideShow;