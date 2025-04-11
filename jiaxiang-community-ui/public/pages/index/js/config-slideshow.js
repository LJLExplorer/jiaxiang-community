import SlideShow from "../Slideshow/SlideShow.js";
import hrefs from "../Slideshow/slideshow-information.js";
document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector("#slide-show-container");
    const page2 = document.querySelector("section.page:nth-of-type(1)");
    const slideshowInfoNo = document.querySelector("#slideshow-info-no");
    const slideshowInfoName = document.querySelector("#slideshow-info-name");
    const slideshowInfoDate = document.querySelector("#slideshow-info-date");
    let pic = new SlideShow(hrefs, container);
    pic.updateCallback = function (src) {
        document.startViewTransition(() => {
            this.image.style.backgroundImage = `
            linear-gradient(to top, transparent, white),
            linear-gradient(to bottom, transparent, white),
            linear-gradient(to left, transparent, white),
            url('${src.src}')`;
            slideshowInfoNo.innerText = "活动" + src.id + "：";
            slideshowInfoName.innerText = src.name;
            slideshowInfoDate.innerText = src.date;
        });
    };
    pic.syncPictureIndex();
    page2.onclick = function () {
        //用于当用户点击详情页面时记录当前正在播放哪个图片
        sessionStorage.setItem("slideshow-index", pic.index + "");
        pic.next();
    };
});
