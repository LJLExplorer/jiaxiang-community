import SlideShow from "../../../SlideShow/SlideShow.js";

document.addEventListener("DOMContentLoaded", function () {
    const container = document.querySelector("#slide-show-container");
    const page2 = document.querySelector("section.page:nth-of-type(2)");

    let hrefs: any[] = [
        "image/image1.png",
        "image/image2.png",
        "image/image3.png"
    ];

    let pic = new SlideShow(hrefs, container as HTMLElement);
    pic.updateCallback = function (src: string) {
        document.startViewTransition(() => {
            this.image.style.backgroundImage = `linear-gradient(to right, white, transparent 30%),
            linear-gradient(to left, white, transparent 30%),
            url('${src}')`;
        });
    }

    pic.syncPictureIndex();

    (page2 as HTMLElement).onclick = function () {
        sessionStorage.setItem("slideshow-index", pic.index + "");

        pic.next();
    };
});