import router from "../../config/api-urls.js";
import GridManagementDAO from "./GridManagementDAO.js";

const getList = async function (): Promise<GridManagementDAO[]> {
    const response = await fetch(API_URLS.grid_management());
    const listRes = await response.json();

    return listRes.data.map((value, index, array) => {
        return new GridManagementDAO(value.id, value.community, value.icon, value.href, {
            title: value.meta.title,
            profile: value.meta.profile
        });
    });
};


export {getList};