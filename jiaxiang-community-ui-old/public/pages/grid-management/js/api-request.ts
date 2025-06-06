import apiUrls from "../../../config/api-urls.js";
import GridManagementDAO from "../component/GridManagementDAO.js";

const getList = async function (): Promise<GridManagementDAO[]> {
    const response = await fetch(apiUrls["grid_management"]);
    const listRes = await response.json();

    return listRes.data.map((value, index, array) => {
        return new GridManagementDAO(value.community, value.icon, {
            title: value.meta.title,
            profile: value.meta.profile
        });
    });
};


export {getList};