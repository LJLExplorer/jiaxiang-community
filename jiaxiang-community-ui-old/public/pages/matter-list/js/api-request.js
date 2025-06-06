import apiUrls from "../../../config/api-urls.js";
import MattersDAO from "../component/MattersDAO.js";
/*

data格式：
"currentPage": pageNum,   // 当前页码
"size": pageSize,   // 每页记录数
"total": records.length,     // 总记录数
"pages": 2,   // 总页数
"records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)

*/
const getPages = async function (itemInAPage) {
    const response = await fetch(apiUrls["list_matters"](1, itemInAPage));
    const listRes = await response.json();
    return {
        "currentPage": parseInt(listRes.data.currentPage),
        "size": parseInt(listRes.data.size),
        "total": listRes.data.total,
        "pages": listRes.data.pages,
    };
};
const getList = async function (currentPage, itemInAPage) {
    const response = await fetch(apiUrls["list_matters"](currentPage, itemInAPage));
    const listRes = await response.json();
    return listRes.data.records.map((value, index, array) => {
        return new MattersDAO(value.id, value.title, value.content);
    });
};
export { getPages, getList };
