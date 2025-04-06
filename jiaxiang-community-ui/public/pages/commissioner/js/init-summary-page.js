"use strict";
//转移到files文件夹
const members = [
    { name: "于燕", role: "社区党委书记 居委会主任", duty: "管理社区所有小区", image: "img/liangwei-photo/yuyan.png" },
    {
        name: "郭亚芳",
        role: "社区书记助理 社区两委成员",
        duty: "管理红旗花园小区",
        image: "img/liangwei-photo/guoyafang.png"
    },
    { name: "郭振南", role: "一般社区工作人员", duty: "管理祥曾园小区", image: "img/liangwei-photo/guozhennan.png" },
    { name: "黄威", role: "一般社区工作人员", duty: "管理嘉和苑西区", image: "img/liangwei-photo/huangwei.png" },
    {
        name: "江婷婷",
        role: "一般社区工作人员",
        duty: "管理财苑小区、碧城华园小区",
        image: "img/liangwei-photo/jiangtingting.png"
    },
    { name: "蒋彦臣", role: "一般社区工作人员", duty: "管理金都花苑小区", image: "img/liangwei-photo/jiangyanchen.png" },
    { name: "孟如", role: "一般社区工作人员", duty: "管理国际商贸城小区", image: "img/liangwei-photo/mengru.png" },
    {
        name: "张传祥",
        role: "一般社区工作人员",
        duty: "管理红旗花园小区",
        image: "img/liangwei-photo/zhangchuanxiang.png"
    },
    { name: "赵劲松", role: "一般社区工作人员", duty: "管理博悦城小区", image: "img/liangwei-photo/zhaojinsong.png" },
];
document.addEventListener("DOMContentLoaded", function () {
    const itemsPerPage = 4;
    //维护页码
    let currentPage = 1;
    function renderMembers() {
        const start = (currentPage - 1) * itemsPerPage;
        const end = start + itemsPerPage;
        const pageMembers = members.slice(start, end);
        const container = document.getElementById("members-container");
        container.innerHTML = "";
        for (let i = 0; i < pageMembers.length; i += 2) {
            const row = document.createElement("div");
            row.className = "members-row";
            const memberBox1 = document.createElement("div");
            memberBox1.className = "member-box";
            renderMemberBox(memberBox1, pageMembers[i]);
            row.appendChild(memberBox1);
            if (i + 1 < pageMembers.length) {
                const memberBox2 = document.createElement("div");
                memberBox2.className = "member-box";
                renderMemberBox(memberBox2, pageMembers[i + 1]);
                row.appendChild(memberBox2);
            }
            container.appendChild(row);
        }
        document.getElementById("total-items").textContent = members.length + "";
        document.getElementById("current-page").textContent = currentPage + "";
        document.getElementById("total-pages").textContent = Math.ceil(members.length / itemsPerPage) + "";
    }
    // @ts-ignore
    function renderMemberBox(memberBox, member) {
        const img = document.createElement("img");
        img.src = member.image;
        const memberInfo = document.createElement("div");
        memberInfo.className = "member-info";
        const nameRole = document.createElement("div");
        nameRole.className = "member-name-role";
        nameRole.textContent = `${member.name} - ${member.role}`;
        const duty = document.createElement("div");
        duty.className = "member-duty";
        duty.textContent = member.duty;
        memberInfo.appendChild(nameRole);
        memberInfo.appendChild(duty);
        memberBox.appendChild(img);
        memberBox.appendChild(memberInfo);
        // 添加点击事件，跳转到成员详细信息页面
        memberBox.addEventListener("click", function () {
            window.location.href = `liangwei_detail.html?id=${member.name}`;
        });
    }
    function updatePagination() {
        const totalPages = Math.ceil(members.length / itemsPerPage);
        const prevArrow = document.getElementById("prev-arrow");
        const nextArrow = document.getElementById("next-arrow");
        // 更新箭头的状态
        prevArrow.classList.toggle("enabled", currentPage > 1);
        prevArrow.classList.toggle("disabled", currentPage === 1);
        nextArrow.classList.toggle("enabled", currentPage < totalPages);
        nextArrow.classList.toggle("disabled", currentPage === totalPages);
    }
    function prevPage() {
        if (currentPage > 1) {
            currentPage--;
            renderMembers();
            updatePagination();
        }
    }
    function nextPage() {
        const totalPages = Math.ceil(members.length / itemsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            renderMembers();
            updatePagination();
        }
    }
    renderMembers();
    updatePagination();
});
