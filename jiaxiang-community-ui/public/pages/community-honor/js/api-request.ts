import apiUrls from "../../../config/api-urls.js";
import HonorDAO from "../Honor/HonorDAO.js";

/*

data格式：
"currentPage": pageNum,   // 当前页码
"size": pageSize,   // 每页记录数
"total": records.length,     // 总记录数
"pages": 2,   // 总页数
"records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)

*/
const getPages = async function (itemInAPage: number) {
    const response = await fetch(apiUrls["community_honor"](1, itemInAPage));
    const listRes = await response.json();

    return {
        "currentPage": parseInt(listRes.data.currentPage),
        "size": parseInt(listRes.data.size),
        "total": listRes.data.total,
        "pages": listRes.data.pages,
    }
};
const getList = async function (currentPage: number, itemInAPage: number): Promise<Omit<HonorDAO[], "dutyContent">> {
    const response = await fetch(apiUrls["community_honor"](currentPage, itemInAPage));
    const listRes = await response.json();

    return listRes.data.records.map((value, index, array) => {
        return new HonorDAO("honor" + index, value);
    });
};

export {getPages, getList};