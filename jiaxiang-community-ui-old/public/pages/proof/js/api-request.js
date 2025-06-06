import apiUrls from "../../../config/api-urls.js";
import ProofsDAO from "../component/ProofsDAO.js";
const getPages = async function (itemInAPage) {
    const response = await fetch(apiUrls["proof_documents"](1, itemInAPage));
    const listRes = await response.json();
    return {
        "currentPage": parseInt(listRes.data.currentPage),
        "size": parseInt(listRes.data.size),
        "total": listRes.data.total,
        "pages": listRes.data.pages,
    };
};
const getList = async function (currentPage, itemInAPage) {
    const response = await fetch(apiUrls["proof_documents"](currentPage, itemInAPage));
    const listRes = await response.json();
    return listRes.data.records.map((value, index, array) => {
        return new ProofsDAO(value.id, value.title);
    });
};
const getDetails = async function (id) {
    const response = await fetch(apiUrls["proof_info"](id));
    const res = await response.json();
    const value = res.data;
    return new ProofsDAO("", value.title, value.content);
};
export { getPages, getList, getDetails };
