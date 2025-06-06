import apiUrls from "../../../config/api-urls.js";

document.addEventListener("DOMContentLoaded", function () {
    const contentEle = document.querySelector(".main .text") as HTMLElement;
    const imageEle = document.querySelector(".main img") as HTMLImageElement;
    const titleEle = document.querySelector(".content .header h1") as HTMLImageElement;

    fetch(apiUrls["community_profile"]).then(res => res.json())
        .then(json => {
            console.log(json);
            contentEle.innerHTML = json.data.profile.split("\n").map(ele => `<p>${ele}</p>`).join("");
            imageEle.src = json.data.images;
            titleEle.innerText = json.data.title;
        })
})