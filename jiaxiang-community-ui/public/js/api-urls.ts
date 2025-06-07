const prefix = "";

const API_URLS = {
    "community_profile": () => prefix + "/api/jiahe/community_profile",
    "list_committees_members": (pageNum: number, pageSize: number) => {
        return `${prefix}/api/jiahe/list_committees_members?pageNum=${pageNum}&pageSize=${pageSize}`
    },
    "personal_info": (id: string) => `${prefix}/api/jiahe/personal_info?id=${id}`,
    "community_honor": (pageNum: number, pageSize: number) => `${prefix}/api/jiahe/community_honor?pageNum=${pageNum}&pageSize=${pageSize}`,

    "list_serve_people": (pageNum: number, pageSize: number) => {
        return `${prefix}/api/jiahe/list_serve_people?pageNum=${pageNum}&pageSize=${pageSize}`
    },
    "serve_people_info": (id: string) => `${prefix}/api/jiahe/serve_people_info?id=${id}`,

    "proof_documents": (pageNum: number, pageSize: number) => {
        return `${prefix}/api/jiahe/proof_documents?pageNum=${pageNum}&pageSize=${pageSize}`
    },
    "proof_info": (id: string) => `${prefix}/api/jiahe/proof_info?id=${id}`,
    "list_matters": (pageNum: number, pageSize: number) => `${prefix}/api/jiahe/list_matters?pageNum=${pageNum}&pageSize=${pageSize}`,

    "grid_management": () => "/api/jiahe/grid_management",

    "list_community_activities": (pageNum: number, pageSize: number) => {
        return `${prefix}/api/jiahe/list_community_activities?pageNum=${pageNum}&pageSize=${pageSize}`
    },
    "community_activity_detail": (id: string) => `${prefix}/api/jiahe/community_activity_detail?id=${id}`,

}

export default API_URLS;