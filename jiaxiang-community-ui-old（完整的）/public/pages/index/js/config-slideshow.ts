import SlideShow from "../Slideshow/SlideShow.js";
import SlideshowInfo from "../Slideshow/SlideshowInfo.js";
import hrefs from "../Slideshow/slideshow-information.js"


document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector("#slide-show-container");
    const page2 = document.querySelector("section.page:nth-of-type(1)");

    const slideshowInfoNo = document.querySelector("#slideshow-info-no") as HTMLElement;
    const slideshowInfoName = document.querySelector("#slideshow-info-name") as HTMLElement;
    const slideshowInfoDate = document.querySelector("#slideshow-info-date") as HTMLElement;

    let pic = new SlideShow(hrefs, container as HTMLElement);
    pic.updateCallback = function (src: SlideshowInfo) {
        document.startViewTransition(() => {
            this.image.style.backgroundImage = `
            linear-gradient(to top, transparent, white),
            linear-gradient(to bottom, transparent, white),
            linear-gradient(to left, transparent, white),
            url('${src.src}')`;

            slideshowInfoNo.innerText = "活动" + src.id + "：";
            slideshowInfoName.innerText = src.name
            slideshowInfoDate.innerText = src.date
        });
    }

    pic.syncPictureIndex();

    (page2 as HTMLElement).onclick = function () {
        //用于当用户点击详情页面时记录当前正在播放哪个图片
        sessionStorage.setItem("slideshow-index", pic.index + "");

        pic.next();
    };
});