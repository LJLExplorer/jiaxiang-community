const prefix = "http://localhost:8083";
// @ts-ignore
window.API_URLS = {
    "community_profile": () => prefix + "/api/jiahe/community_profile",
    "list_committees_members": (pageNum, pageSize) => {
        return `${prefix}/api/jiahe/list_committees_members?pageNum=${pageNum}&pageSize=${pageSize}`;
    },
    "personal_info": (id) => `${prefix}/api/jiahe/personal_info?id=${id}`,
    "community_honor": (pageNum, pageSize) => `${prefix}/api/jiahe/community_honor?pageNum=${pageNum}&pageSize=${pageSize}`,
    // "community_honor": (pageNum, pageSize) => `http://localhost:8083/api/jiahe/community_honor?pageNum=${pageNum}&pageSize=${pageSize}`,
    "list_serve_people": (pageNum, pageSize) => {
        return `${prefix}/api/jiahe/list_serve_people?pageNum=${pageNum}&pageSize=${pageSize}`;
    },
    "serve_people_info": (id) => `${prefix}/api/jiahe/serve_people_info?id=${id}`,
    "proof_documents": (pageNum, pageSize) => {
        return `${prefix}/api/jiahe/proof_documents?pageNum=${pageNum}&pageSize=${pageSize}`;
    },
    "proof_info": (id) => `${prefix}/api/jiahe/proof_info?id=${id}`,
    "list_matters": (pageNum, pageSize) => `${prefix}/api/jiahe/list_matters?pageNum=${pageNum}&pageSize=${pageSize}`,
    "grid_management": () => prefix + "/api/jiahe/grid_management",
    "list_community_activities": (pageNum, pageSize) => {
        return `${prefix}/api/jiahe/list_community_activities?pageNum=${pageNum}&pageSize=${pageSize}`;
    },
    "community_activity_detail": (id) => `${prefix}/api/jiahe/community_activity_detail?id=${id}`,
    'download': (url) => `${prefix}/api/jiahe/file/download?url=${url}`
};
// @ts-ignore
// export default window.API_URLS;
