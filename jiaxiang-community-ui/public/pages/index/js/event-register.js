"use strict";
document.addEventListener("DOMContentLoaded", function () {
    const detailsBtn = document.querySelector("#community-details");
    const honorBtn = document.querySelector("#community-honor");
    const editBtn = document.querySelector("#community-edit");
    const slideshowView = document.querySelector("#slideshow-view");
    const searchBtn = document.querySelector("#search-button");
    detailsBtn.addEventListener("click", function () {
        // @ts-ignore
        location = "./community-details.html";
    });
    honorBtn.addEventListener("click", function () {
        // @ts-ignore
        location = "./community-honor.html";
    });
    editBtn.addEventListener("click", function () {
        const communityInfo = document.querySelector(".community-info p");
        const editButton = document.querySelector(".community-info .edit-button");
        if (editButton.textContent === "编辑") {
            // 变为可编辑状态
            const textArea = document.createElement("textarea");
            textArea.value = communityInfo.textContent + "";
            textArea.style.width = "100%";
            textArea.style.height = "150px";
            textArea.style.padding = "10px";
            textArea.style.fontSize = "16px";
            textArea.style.border = "1px solid #ccc";
            textArea.style.borderRadius = "5px";
            communityInfo.replaceWith(textArea);
            editButton.textContent = "保存";
        }
        else {
            // 保存修改
            const textArea = document.querySelector(".community-info textarea");
            const newParagraph = document.createElement("p");
            newParagraph.textContent = textArea.value;
            textArea.replaceWith(newParagraph);
            editButton.textContent = "编辑";
        }
    });
    slideshowView.addEventListener("click", function () {
        // 跳转到详情页
        window.location.href = `detail.html`;
    });
    searchBtn.addEventListener("click", function () {
        //这个代码没用，因为不知道找的是哪个
        const query = document.getElementById("search-input").value.toLowerCase();
        const captions = document.querySelectorAll(".caption");
        captions.forEach(caption => {
            const text = (caption.textContent + "").toLowerCase();
            if (text.includes(query)) {
                caption.parentElement.style.display = "block";
            }
            else {
                caption.parentElement.style.display = "none";
            }
        });
    });
});
