import apiUrls from "../../../config/api-urls.js";
import ServeDAO from "../Serve/ServeDAO.js";
const getPages = async function (itemInAPage) {
    const response = await fetch(apiUrls["list_serve_people"](1, itemInAPage));
    const listRes = await response.json();
    return {
        "currentPage": parseInt(listRes.data.currentPage),
        "size": parseInt(listRes.data.size),
        "total": listRes.data.total,
        "pages": listRes.data.pages,
    };
};
const getList = async function (currentPage, itemInAPage) {
    const response = await fetch(apiUrls["list_serve_people"](currentPage, itemInAPage));
    const listRes = await response.json();
    return listRes.data.records.map((value, index, array) => {
        return new ServeDAO(value.id, value.title, value.phone, value.openDaysAndTime, value.location, value.images);
    });
};
const getDetails = async function (id) {
    const response = await fetch(apiUrls["serve_people_info"](id));
    const res = await response.json();
    const value = res.data;
    return new ServeDAO(value.id, value.title, value.phone, value.openDaysAndTime, value.location, value.images, value.serviceIntro, [value.serviceContent], [value.serviceRules]);
};
export { getPages, getList, getDetails };
