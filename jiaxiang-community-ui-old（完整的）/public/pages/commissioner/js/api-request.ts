import apiUrls from "../../../config/api-urls.js";
import CommissionerDAO from "../Commissioner/CommissionerDAO.js";

const getPages = async function (itemInAPage: number) {
    const response = await fetch(apiUrls["list_committees_members"](1, itemInAPage));
    const listRes = await response.json();

    return {
        "currentPage": parseInt(listRes.data.currentPage),
        "size": parseInt(listRes.data.size),
        "total": listRes.data.total,
        "pages": listRes.data.pages,
    }
};
const getList = async function (currentPage: number, itemInAPage: number): Promise<Omit<CommissionerDAO[], "dutyContent">> {
    const response = await fetch(apiUrls["list_committees_members"](currentPage, itemInAPage));
    const listRes = await response.json();

    return listRes.data.records.map((value, index, array) => {
        return new CommissionerDAO(value.id, value.name, value.position, value.duty, value.images);
    });
};

const getDetails = async function (id: string): Promise<CommissionerDAO> {
    const response = await fetch(apiUrls["personal_info"](id));
    const res = await response.json();

    const data = res.data;
    return new CommissionerDAO(data.id, data.name, data.position, data.duty, data.images, data.dutyContent);
};

export {getPages, getList, getDetails};