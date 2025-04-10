document.addEventListener("DOMContentLoaded", function () {
    const textNode = document.createTextNode("获取到点击的图片索引值：" + sessionStorage.getItem("slideshow-index"));
    document.body.appendChild(textNode);
});
