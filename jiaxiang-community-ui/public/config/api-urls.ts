const prefix = "";

const API_URLS = {
    "community_profile": prefix + "/api/jiahe/community_profile",
    "list_committees_members": (pageNum: number, pageSize: number) => {
        return `${prefix}/api/jiahe/list_committees_members?pageNum=${pageNum}&pageSize=${pageSize}`
    },
    "personal_info": (id: string) => `${prefix}/api/jiahe/personal_info?id=${id}`,
    "community_honor": (pageNum: number, pageSize: number) => `${prefix}/api/jiahe/community_honor?pageNum=${pageNum}&pageSize=${pageSize}`,
}

export default API_URLS;