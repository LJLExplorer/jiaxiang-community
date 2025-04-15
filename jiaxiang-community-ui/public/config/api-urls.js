const prefix = "";
const API_URLS = {
    "community_profile": prefix + "/api/jiahe/community_profile",
    "list_committees_members": (pageNum, pageSize) => {
        return `${prefix}/api/jiahe/list_committees_members?pageNum=${pageNum}&pageSize=${pageSize}`;
    },
    "personal_info": (id) => `${prefix}/api/jiahe/personal_info?id=${id}`
};
export default API_URLS;
