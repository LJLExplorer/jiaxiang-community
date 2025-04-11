import router from "../../../common-component/router.js";

document.addEventListener("DOMContentLoaded", function () {
    const detailsBtn = document.querySelector("#community-details") as HTMLElement;
    const honorBtn = document.querySelector("#community-honor") as HTMLElement;
    const editBtn = document.querySelector("#community-edit") as HTMLElement;
    const slideshowView = document.querySelector("#slideshow-view") as HTMLElement;

    const buttonService = document.querySelector("#button-service") as HTMLElement;
    const buttonAffair = document.querySelector("#button-affair") as HTMLElement;
    const buttonList = document.querySelector("#button-list") as HTMLElement;

    buttonService.addEventListener("click", function () {
        // @ts-ignore
        location = router["serve-people"];
    });
    buttonAffair.addEventListener("click", function () {
        // @ts-ignore
        location = router["community-honor"];
    });
    buttonList.addEventListener("click", function () {
        // @ts-ignore
        location = router["commissioner"];
    });
    detailsBtn.addEventListener("click", function () {
        // @ts-ignore
        location = router["profile"];
    });
    honorBtn.addEventListener("click", function () {
        // @ts-ignore
        location = router["community-honor"];
    });
    editBtn.addEventListener("click", function () {
        const communityInfo = document.querySelector(".community-info p") as HTMLElement;
        const editButton = document.querySelector(".community-info .edit-button") as HTMLElement;

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
        } else {
            // 保存修改
            const textArea = document.querySelector(".community-info textarea") as HTMLTextAreaElement;
            const newParagraph = document.createElement("p");
            newParagraph.textContent = textArea.value;

            textArea.replaceWith(newParagraph);
            editButton.textContent = "编辑";
        }
    });
    slideshowView.addEventListener("click", function () {
        // 跳转到详情页
        window.location.href = router["community-activity"];


    });
})