class GridManagementDAO {
    public id: number;
    public community: string;
    public icon: string;
    public href: string;

    public meta: {
        title: string,
        profile: string
    }

    constructor(id?: string,community?: string, icon?: string, href?: string, meta?: {
        title: string,
        profile: string
    }) {
        this.id = id;
        this.community = community;
        this.icon = icon;
        this.href = href;
        this.meta = meta;

    }
}

export default GridManagementDAO;