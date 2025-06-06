class GridManagementDAO {
    public community: string;
    public icon: string;

    public meta: {
        title: string,
        profile: string
    }

    constructor(community?: string, icon?: string, meta?: {
        title: string,
        profile: string
    }) {
        this.community = community;
        this.icon = icon;
        this.meta = meta;
    }
}

export default GridManagementDAO;