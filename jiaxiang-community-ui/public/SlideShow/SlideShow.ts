/*
* 图片轮播标准插件
* */
class SlideShow extends Object {
    public image: HTMLElement;
    public hrefs: any[] = [];
    public debug = false;
    private index = 0;

    constructor(hrefs: any[], container: HTMLElement = document.body) {
        super();
        this.hrefs = hrefs;
        this.image = container;

        this.syncPictureIndex();
    }

    public print(...msg: any[]) {
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
        this.updateCallback(this.hrefs[this.index]);

        // this.image.style.backgroundImage = `url(${this.hrefs[this.index]})`;
    }

    public updateCallback(src: string) {

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